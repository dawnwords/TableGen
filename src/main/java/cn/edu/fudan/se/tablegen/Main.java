package cn.edu.fudan.se.tablegen;

import cn.edu.fudan.se.tablegen.core.InputGen;
import cn.edu.fudan.se.tablegen.core.Random;

/**
 * Created by Dawnwords on 2015/12/10.
 */
public class Main {
    public static void main(String[] args) {
        new InputGen()
                .outputPath("parameter")
                .threadNum(10)
                .iterationNum(100)
                .gen(new Random(12123123), 1000, 7500);
    }
}