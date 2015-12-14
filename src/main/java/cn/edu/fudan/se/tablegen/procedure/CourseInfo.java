package cn.edu.fudan.se.tablegen.procedure;


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
                "\tSELECT course.name,course.time,course.location,course.credit,teacher.name,course.capacity,(course.capacity - COUNT(selection.cid)) AS remaining" +
                " FROM course FORCE INDEX(IDX_course),teacher FORCE INDEX(IDX_teacher),selection" +
                " WHERE course.tid=teacher.tid AND selection.cid=course.cid AND course.cid=course_id GROUP BY selection.cid;\n" +
                "END");
    }
}
