package cn.edu.fudan.se.tablegen.core;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public abstract class Table {

    private int randomNum;

    public Table randomNum(int randomNum) {
        this.randomNum = randomNum;
        return this;
    }

    public void resetTable(Statement statement) throws SQLException {
        long start = System.currentTimeMillis();
        System.out.print("drop Table." + getClass().getSimpleName());
        dropTable(statement);
        System.out.printf(": %.2fs used\n", (System.currentTimeMillis() - start) / 1000.0);

        start = System.currentTimeMillis();
        System.out.print("create Table." + getClass().getSimpleName());
        createTable(statement);
        System.out.printf(": %.2fs used\n", (System.currentTimeMillis() - start) / 1000.0);
    }

    public void insertRandomElement(Random random, Connection connection) throws SQLException {
        System.out.print("insert element for Table." + getClass().getSimpleName());
        long start = System.currentTimeMillis();
        connection.setAutoCommit(false);
        PreparedStatement ps = insertStatement(connection);

        final int batchSize = 10000;
        for (int i = 0; i < randomNum; i++) {
            randomElement(random, ps, i);
            ps.addBatch();

            if (i % batchSize == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
        }

        addForeignKeyAndIndex(ps);
        ps.executeBatch();
        connection.commit();
        System.out.printf(": %.2fs used\n", (System.currentTimeMillis() - start) / 1000.0);
    }

    protected void addForeignKeyAndIndex(Statement statement) throws SQLException {
    }

    protected abstract void dropTable(Statement statement) throws SQLException;

    protected abstract void createTable(Statement statement) throws SQLException;

    protected PreparedStatement insertStatement(Connection connection) throws SQLException {
        throw new IllegalStateException("insertStatement(Connection connection) Not Implemented");
    }

    protected abstract void randomElement(Random random, PreparedStatement statement, int i) throws SQLException;


}
