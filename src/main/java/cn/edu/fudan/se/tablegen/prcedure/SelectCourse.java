package cn.edu.fudan.se.tablegen.prcedure;


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
        statement.addBatch("CREATE PROCEDURE `selectCourse`(IN `student_id` INT,IN `course_id` INT)\n" +
                "BEGIN\n" +
                "\tSTART TRANSACTION;\n" +
                "\tIF ((SELECT EXISTS (SELECT 1 FROM course WHERE cid=course_id)) AND " +
                "(SELECT EXISTS (SELECT 1 FROM student WHERE sid=student_id)) AND " +
                "(SELECT NOT EXISTS (SELECT 1 FROM selection WHERE cid=course_id AND sid=student_id))) THEN\n" +
                "\t\tSELECT @Remaining:=remaining FROM course WHERE cid=course_id FOR UPDATE;\n" +
                "\t\tIF @Remaining > 0 THEN\n" +
                "\t\t\tINSERT INTO selection(cid,sid) VALUES (course_id, student_id);\n" +
                "\t\t\tUPDATE course SET remaining=@Remaining-1 WHERE cid=course_id;\n" +
                "\t\tEND IF;\n" +
                "\tEND IF;\n" +
                "\tCOMMIT;\n" +
                "END");
    }
}
