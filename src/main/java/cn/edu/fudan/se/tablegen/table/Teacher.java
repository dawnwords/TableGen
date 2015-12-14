package cn.edu.fudan.se.tablegen.table;


import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.core.Random;
import org.bson.Document;

import java.util.List;

/**
 * Created by Dawnwords on 2015/12/5.
 */
public class Teacher extends Table {

    @Override
    protected void randomElement(Random random, List<Document> toInsert, int i) {
        toInsert.add(new Document()
                .append("_id", i + 1)
                .append("name", random.nextRandomName())
                .append("intro", random.nextRandomDescription())
        );
    }
}
