package cn.edu.fudan.se.tablegen.table;


import cn.edu.fudan.se.tablegen.core.Random;
import cn.edu.fudan.se.tablegen.core.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Selection extends Table {
    private int courseCapacity, studentNum;

    public Selection courseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
        return this;
    }

    public Selection studentNum(int studentNum) {
        this.studentNum = studentNum;
        return this;
    }

    @Override
    protected void dropTable(Statement statement) throws SQLException {
        statement.addBatch("DROP TABLE IF EXISTS `selection`");
    }

    @Override
    protected void createTable(Statement statement) throws SQLException {
        statement.addBatch("CREATE TABLE `selection` (\n" +
                "  `cid` INT(11) NOT NULL,\n" +
                "  `sid` INT(11) NOT NULL,\n" +
                "  PRIMARY KEY (`cid`,`sid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }

    @Override
    protected void addForeignKeyAndIndex(Statement statement) throws SQLException {
        statement.addBatch("ALTER TABLE `selection` ADD CONSTRAINT `FK_Course` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`)");
        statement.addBatch("ALTER TABLE `selection` ADD CONSTRAINT `FK_Student` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)");
    }

    @Override
    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO selection(cid, sid) VALUES(?,?)");
    }

    @Override
    protected void randomElement(Random random, PreparedStatement statement, int i) throws SQLException {
        HashSet<Integer> alreadySelect = new HashSet<>();
        int courseCapacity = this.courseCapacity + random.nextInt(this.courseCapacity);
        int studentId = random.nextInt(studentNum) + 1;
        int courseId = i + 1;
        alreadySelect.add(studentId);
        statement.setInt(1, courseId);
        statement.setInt(2, studentId);
        for (int j = 1; j < courseCapacity; j++) {
            statement.addBatch();
            do {
                studentId = random.nextInt(studentNum) + 1;
            } while (alreadySelect.contains(studentId));
            alreadySelect.add(studentId);
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
        }
    }
}
