package cn.edu.fudan.se.tablegen.table;


import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.core.Random;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import java.util.HashSet;
import java.util.List;

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
    protected void addIndex(MongoCollection<Document> table) {
        table.createIndex(Indexes.ascending("cid", "sid"));
        table.createIndex(Indexes.ascending("sid"));
    }

    @Override
    protected void randomElement(Random random, List<Document> toInsert, int i) {
        HashSet<Integer> alreadySelect = new HashSet<>();
        int courseCapacity = this.courseCapacity + random.nextInt(this.courseCapacity);
        int studentId = random.nextInt(studentNum) + 1;
        int courseId = i + 1;
        alreadySelect.add(studentId);
        Document document = new Document().append("cid", courseId).append("sid", studentId);
        for (int j = 1; j < courseCapacity; j++) {
            toInsert.add(document);
            do {
                studentId = random.nextInt(studentNum) + 1;
            } while (alreadySelect.contains(studentId));
            alreadySelect.add(studentId);
            document = new Document().append("cid", courseId).append("sid", studentId);
        }
    }
}
