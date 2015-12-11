package cn.edu.fudan.se.tablegen.procedure;

import cn.edu.fudan.se.tablegen.core.Procedure;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class SelectCapcityNewTable implements Procedure {
    @Override
    public void addProcedure(Statement statement) throws SQLException {
        statement.addBatch("DROP PROCEDURE IF EXISTS `selectCapacityNewTable`");
        statement.addBatch("CREATE PROCEDURE `selectCapacityNewTable`(IN `course_id` INT)\n" +
                "BEGIN\n" +
                "\tSELECT cid,capacity FROM capacity WHERE cid = course_id;\n" +
                "END");
    }
}
