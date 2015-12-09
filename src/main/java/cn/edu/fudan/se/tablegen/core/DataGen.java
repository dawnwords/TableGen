package cn.edu.fudan.se.tablegen.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/6.
 */
public class DataGen {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Procedure[] procedures;
    private Table[] tables;
    private String dbHost, dbName, dbUser, dbPass;
    private int dbPort;
    private long randomSeed;

    private Connection connect() throws SQLException {
        if (dbHost == null || dbPort <= 0 || dbName == null || dbUser == null || dbPass == null) {
            throw new IllegalStateException("db parameters not set yet");
        }
        String dbUrl = String.format("jdbc:mysql://%s:%d/%s?useServerPrepStmts=false&rewriteBatchedStatements=true", dbHost, dbPort, dbName);
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    public DataGen procedures(Procedure[] procedures) {
        this.procedures = procedures;
        return this;
    }

    public DataGen tables(Table[] tables) {
        this.tables = tables;
        return this;
    }

    public DataGen dbHost(String dbHost) {
        this.dbHost = dbHost;
        return this;
    }

    public DataGen dbPort(int dbPort) {
        this.dbPort = dbPort;
        return this;
    }

    public DataGen dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public DataGen dbUser(String dbUser) {
        this.dbUser = dbUser;
        return this;
    }

    public DataGen dbPass(String dbPass) {
        this.dbPass = dbPass;
        return this;
    }

    public DataGen randomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
        return this;
    }

    public void gen() {
        Connection conn = null;

        Random random = new Random(randomSeed == 0 ? System.currentTimeMillis() : randomSeed);
        try {
            conn = connect();

            Statement statement = conn.createStatement();
            statement.addBatch("SET FOREIGN_KEY_CHECKS=0");

            if (tables != null) {
                for (Table table : tables) {
                    table.resetTable(statement);
                }
            }

            if (procedures != null) {
                for (Procedure procedure : procedures) {
                    procedure.addProcedure(statement);
                }
            }

            statement.addBatch("SET FOREIGN_KEY_CHECKS=1");
            statement.executeBatch();

            if (tables != null) {
                for (Table table : tables) {
                    table.insertRandomElement(random, conn);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
