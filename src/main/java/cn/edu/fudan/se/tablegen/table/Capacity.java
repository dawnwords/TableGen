package cn.edu.fudan.se.tablegen.table;


import cn.edu.fudan.se.tablegen.core.Random;
import cn.edu.fudan.se.tablegen.core.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/9.
 */
public class Capacity extends Table {

    private int capacity;

    public Capacity capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    @Override
    protected void dropTable(Statement statement) throws SQLException {
        statement.addBatch("DROP TABLE IF EXISTS `capacity`");
    }

    @Override
    protected void createTable(Statement statement) throws SQLException {
        statement.addBatch("CREATE TABLE `capacity` (\n" +
                "  `cid` INT(11) NOT NULL,\n" +
                "  `capacity` INT(11) NOT NULL,\n" +
                "  PRIMARY KEY (`cid`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8");
    }

    @Override
    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO capacity(cid, capacity) VALUES(?,?)");
    }

    @Override
    protected void randomElement(Random random, PreparedStatement statement, int i) throws SQLException {
        int cid = i + 1;
        statement.setInt(1, cid);
        statement.setInt(2, capacity + random.nextInt(capacity));
    }
}
