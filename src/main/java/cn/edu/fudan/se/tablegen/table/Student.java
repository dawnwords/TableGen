package cn.edu.fudan.se.tablegen.table;


import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.core.Random;
import org.bson.Document;

import java.util.List;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Student extends Table {
    @Override
    protected void randomElement(Random random, List<Document> toInsert, int i) {
        toInsert.add(new Document()
                .append("_id", i + 1)
                .append("name", random.nextRandomName())
                .append("gender", random.nextInt(2))
                .append("gpa", random.nextDouble(4))
                .append("grade", random.nextInt(4) + 1)
        );
    }
}
