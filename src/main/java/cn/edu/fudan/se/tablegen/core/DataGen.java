package cn.edu.fudan.se.tablegen.core;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by Dawnwords on 2015/12/15.
 */
public class DataGen {
    private Table[] tables;
    private String dbHost, dbName;
    private int dbPort;
    private long randomSeed;

    public DataGen tables(Table[] tables) {
        this.tables = tables;
        return this;
    }

    public DataGen dbHost(String dbHost) {
        this.dbHost = dbHost;
        return this;
    }

    public DataGen dbName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public DataGen dbPort(int dbPort) {
        this.dbPort = dbPort;
        return this;
    }

    public DataGen randomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
        return this;
    }

    public void gen() {
        MongoClient client = new MongoClient(dbHost, dbPort);
        MongoDatabase db = client.getDatabase(dbName);

        Random random = new Random(randomSeed == 0 ? System.currentTimeMillis() : randomSeed);
        if (tables != null) {
            for (Table table : tables) {
                table.resetTable(db);
            }

            for (Table table : tables) {
                table.insertRandomElement(random, db);
            }
        }

        client.close();
    }
}
