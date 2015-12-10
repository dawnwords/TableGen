package cn.edu.fudan.se.tablegen.procedure;

import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class SelectCapcity implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `selectCapacity`");
        statement.addBatch("CREATE PROCEDURE `selectCapacity`(IN `course_id` INT)\n" +
                "BEGIN\n" +
                "\tSELECT cid,capacity FROM course USE INDEX(`PRIMARY`) WHERE cid = course_id;\n" +
                "END");
    }
}
