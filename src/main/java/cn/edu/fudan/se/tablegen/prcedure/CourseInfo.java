package cn.edu.fudan.se.tablegen.prcedure;


import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/6.
 */
public class CourseInfo implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `courseInfo`");
        statement.addBatch("CREATE PROCEDURE `courseInfo`(IN `course_id` int)\n" +
                "BEGIN\n" +
                "\tSELECT course.cid,course.name,course.time,course.location,course.credit,teacher.name,course.remaining" +
                " FROM course INNER JOIN teacher ON course.tid = teacher.tid" +
                " WHERE course.cid=course_id;\n" +
                "END");
    }
}
