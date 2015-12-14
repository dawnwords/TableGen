package cn.edu.fudan.se.tablegen.table;

import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.core.Random;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.util.List;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Course extends Table {

    private int teacherNum, capacity;

    public Course teacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
        return this;
    }

    public Course capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    @Override
    protected void addIndex(MongoCollection<Document> table) {
        table.createIndex(Indexes.ascending("tid"));
        table.createIndex(Indexes.ascending("_id", "capacity"));
    }

    @Override
    protected void randomElement(Random random, List<Document> toInsert, int i) {
        toInsert.add(new Document()
                .append("_id", i + 1)
                .append("name", random.nextRandomName())
                .append("tid", 1 + random.nextInt(teacherNum))
                .append("time", random.nextRandomName())
                .append("location", random.nextRandomName())
                .append("credit", 1 + random.nextInt(5))
                .append("capacity", capacity + random.nextInt(capacity))
                .append("description", random.nextRandomDescription()));
    }
}
