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
public class Student extends Table {
    @Override
    protected void dropTable(Statement statement) throws SQLException {
        statement.addBatch("DROP TABLE IF EXISTS `student`");
    }

    @Override
    protected void createTable(Statement statement) throws SQLException {
        statement.addBatch("CREATE TABLE `student` (\n" +
                "  `sid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `gender` VARCHAR(1) NOT NULL,\n" +
                "  `gpa` DECIMAL(2,1) NOT NULL,\n" +
                "  `grade` INT(1) NOT NULL,\n" +
                "  PRIMARY KEY (`sid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }

    @Override
    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO student( name, gender, gpa, grade) VALUES (?,?,?,?)");
    }

    @Override
    protected void randomElement(Random random, PreparedStatement statement, int i) throws SQLException {
        statement.setString(1, random.nextRandomName());
        statement.setInt(2, random.nextInt(2));
        statement.setDouble(3, random.nextDouble(4));
        statement.setInt(4, random.nextInt(4) + 1);
    }
}
