package cn.edu.fudan.se.tablegen.table;

import cn.edu.fudan.se.tablegen.core.Random;
import cn.edu.fudan.se.tablegen.core.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Course extends Table {

    private int teacherNum;

    public Course teacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
        return this;
    }

    @Override
    protected void dropTable(Statement statement) throws SQLException {
        statement.addBatch("DROP TABLE IF EXISTS `course`");
    }

    @Override
    protected void createTable(Statement statement) throws SQLException {
        statement.addBatch("CREATE TABLE `course` (\n" +
                "  `cid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `tid` INT(11) NOT NULL,\n" +
                "  `time` VARCHAR(20) NOT NULL,\n" +
                "  `location` VARCHAR(20) NOT NULL,\n" +
                "  `credit` INT(11) NOT NULL,\n" +
                "  `description` VARCHAR(1000) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`cid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }

    @Override
    protected void addForeignKeyAndIndex(Statement statement) throws SQLException {
        statement.addBatch("ALTER TABLE `course` ADD CONSTRAINT `FK_Teacher` FOREIGN KEY (`tid`) REFERENCES `teacher` (`tid`)");
    }

    @Override
    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO course (name, tid, time, location, credit, description) VALUES(?,?,?,?,?,?)");
    }

    @Override
    protected void randomElement(Random random, PreparedStatement statement, int i) throws SQLException {
        statement.setString(1, random.nextRandomName());
        statement.setInt(2, 1 + random.nextInt(teacherNum));
        statement.setString(3, random.nextRandomName());
        statement.setString(4, random.nextRandomName());
        statement.setInt(5, 1 + random.nextInt(5));
        statement.setString(6, random.nextRandomDescription());
    }


}
