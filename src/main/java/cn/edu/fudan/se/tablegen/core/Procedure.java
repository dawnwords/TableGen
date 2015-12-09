package cn.edu.fudan.se.tablegen.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Dawnwords on 2015/12/6.
 */
public interface Procedure {
    void addProcedure(Statement statement) throws SQLException;
}
