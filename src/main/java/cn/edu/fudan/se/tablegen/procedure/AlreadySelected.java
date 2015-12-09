package cn.edu.fudan.se.tablegen.procedure;


import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/6.
 */
public class AlreadySelected implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `alreadySelected`");
        statement.addBatch("CREATE PROCEDURE `alreadySelected`(IN `student_id` int)\n" +
                "BEGIN\n" +
                "\tSELECT course.cid,course.name,course.time,course.location,course.credit,teacher.name FROM course, teacher, selection" +
                " WHERE course.tid=teacher.tid AND course.cid=selection.cid AND selection.sid=student_id LIMIT 10;\n" +
                "END");
    }
}
