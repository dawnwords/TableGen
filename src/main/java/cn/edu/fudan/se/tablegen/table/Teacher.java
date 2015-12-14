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
public class Teacher extends Table {

    @Override
    protected void dropTable(Statement statement) throws SQLException {
        statement.addBatch("DROP TABLE IF EXISTS `teacher`");
    }

    @Override
    protected void createTable(Statement statement) throws SQLException {
        statement.addBatch("CREATE TABLE `teacher` (\n" +
                "  `tid` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `intro` VARCHAR(1000) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`tid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }

    @Override
    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO teacher(name, intro) VALUES (?,?)");
    }

    @Override
    protected void addForeignKeyAndIndex(Statement statement) throws SQLException {
        statement.addBatch("ALTER TABLE `teacher` ADD INDEX `IDX_teacher` (`tid`, `name`)");
    }

    @Override
    protected void randomElement(Random random, PreparedStatement statement, int i) throws SQLException {
        statement.setString(1, random.nextRandomName());
        statement.setString(2, random.nextRandomDescription());
    }
}
