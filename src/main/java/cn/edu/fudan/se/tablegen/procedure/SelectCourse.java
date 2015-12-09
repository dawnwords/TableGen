package cn.edu.fudan.se.tablegen.procedure;


import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/6.
 */
public class SelectCourse implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `selectCourse`");
        statement.addBatch("CREATE PROCEDURE `selectCourse`(IN `student_id` int,IN `course_id` int)\n" +
                "BEGIN\n" +
                "\tSTART TRANSACTION;\n" +
                "\tIF ((SELECT EXISTS (SELECT 1 FROM course WHERE cid=course_id)) AND " +
                "(SELECT EXISTS (SELECT 1 FROM student WHERE sid=student_id)) AND " +
                "(SELECT NOT EXISTS (SELECT 1 FROM selection WHERE cid=course_id AND sid=student_id))) THEN\n" +
                "\t\tSELECT @SelectNum:=COUNT(cid) FROM selection WHERE cid=course_id FOR UPDATE;\n" +
                "\t\tINSERT INTO selection(cid,sid) SELECT course_id,student_id FROM course WHERE cid=course_id AND @SelectNum < capacity;\n" +
                "\tEND IF;\n" +
                "\tCOMMIT;\n" +
                "END");
    }
}
