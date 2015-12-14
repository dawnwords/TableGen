package cn.edu.fudan.se.tablegen.core;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawnwords on 2015/12/15.
 */
public abstract class Table {
    private String tableName = getClass().getSimpleName().toLowerCase();
    private int randomNum;

    public Table randomNum(int randomNum) {
        this.randomNum = randomNum;
        return this;
    }

    public void resetTable(MongoDatabase database) {
        database.drop();
        database.createCollection(tableName);
    }

    public void insertRandomElement(Random random, MongoDatabase database) {
        System.out.print("insert element for Table." + getClass().getSimpleName());
        long start = System.currentTimeMillis();
        MongoCollection<Document> table = database.getCollection(tableName);

        final int batchSize = 1000;
        List<Document> toInsert = new ArrayList<>();
        for (int i = 0; i < randomNum; i++) {
            randomElement(random, toInsert, i);

            if (i % batchSize == 0) {
                table.insertMany(toInsert);
                toInsert.clear();
            }
        }
        table.insertMany(toInsert);

        addIndex(table);
        System.out.printf(": %.2fs used\n", (System.currentTimeMillis() - start) / 1000.0);
    }

    protected void addIndex(MongoCollection<Document> table) {
    }

    protected abstract void randomElement(Random random, List<Document> toInsert, int i);
}
