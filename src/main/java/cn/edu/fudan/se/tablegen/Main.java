package cn.edu.fudan.se.tablegen;

import cn.edu.fudan.se.tablegen.core.DataGen;
import cn.edu.fudan.se.tablegen.core.Procedure;
import cn.edu.fudan.se.tablegen.core.Table;
import cn.edu.fudan.se.tablegen.procedure.AlreadySelected;
import cn.edu.fudan.se.tablegen.procedure.CourseInfo;
import cn.edu.fudan.se.tablegen.procedure.SelectCourse;
import cn.edu.fudan.se.tablegen.table.*;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class Main {
    private static final int TEACHER_NUM = 3000;
    private static final int COURSE_NUM = 10000;
    private static final int COURSE_CAPACITY = 50;
    private static final int STUDENT_NUM = 75000;

    public static void main(String[] args) {
        new DataGen().dbHost("10.131.252.156").dbPort(3306).dbName("course").dbUser("root").dbPass("cloudfdse")
                .randomSeed(333222111)
                .procedures(new Procedure[]{
                        new SelectCourse(),
                        new AlreadySelected(),
                        new CourseInfo()
                })
                .tables(new Table[]{
                        new Teacher().randomNum(TEACHER_NUM),
                        new Course().teacherNum(TEACHER_NUM).randomNum(COURSE_NUM),
                        new Capacity().capacity(COURSE_CAPACITY).randomNum(COURSE_NUM),
                        new Student().randomNum(STUDENT_NUM),
                        new Selection()/*.studentNum(STUDENT_NUM).courseCapacity(COURSE_CAPACITY).randomNum(COURSE_NUM)*/
                }).gen();
    }
}