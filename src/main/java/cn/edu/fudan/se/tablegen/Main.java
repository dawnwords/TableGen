package cn.edu.fudan.se.tablegen;

import cn.edu.fudan.se.tablegen.core.DataGen;
import cn.edu.fudan.se.tablegen.core.Procedure;
import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.procedure.SelectCapcity;
import cn.edu.fudan.se.tablegen.procedure.SelectCapcityIndex;
import cn.edu.fudan.se.tablegen.procedure.SelectCapcityNewTable;
import cn.edu.fudan.se.tablegen.table.Capacity;
import cn.edu.fudan.se.tablegen.table.Course;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class Main {
    private static final int TEACHER_NUM = 30000;
    private static final int COURSE_NUM = 100000;
    private static final int COURSE_CAPACITY = 500;

    public static void main(String[] args) {
        new DataGen().dbHost("10.131.252.156").dbPort(3306).dbName("course").dbUser("root").dbPass("cloudfdse")
                .randomSeed(333222111)
                .procedures(new Procedure[]{
                        new SelectCapcity(),
                        new SelectCapcityIndex(),
                        new SelectCapcityNewTable()
                })
                .tables(new Table[]{
                        new Course().teacherNum(TEACHER_NUM).capacity(COURSE_CAPACITY).randomNum(COURSE_NUM),
                        new Capacity().capacity(COURSE_CAPACITY).randomNum(COURSE_NUM)
                }).gen();
    }
}