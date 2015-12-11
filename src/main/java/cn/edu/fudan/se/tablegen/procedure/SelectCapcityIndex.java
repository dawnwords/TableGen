package cn.edu.fudan.se.tablegen.procedure;

import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class SelectCapcityIndex implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `selectCapacityIndex`");
        statement.addBatch("CREATE PROCEDURE `selectCapacityIndex`(IN `course_id` INT)\n" +
                "BEGIN\n" +
                "\tSELECT cid,capacity FROM course USE INDEX(IDX_capacity) WHERE cid = course_id;\n" +
                "END");
    }
}
