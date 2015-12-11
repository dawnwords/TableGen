package cn.edu.fudan.se.tablegen.core;

import java.io.*;

/**
 * Created by Dawnwords on 2015/12/11.
 */
public class InputGen {
    private int threadNum, iterationNum;
    private String outputPath;

    public InputGen threadNum(int threadNum) {
        this.threadNum = threadNum;
        return this;
    }

    public InputGen iterationNum(int iterationNum) {
        this.iterationNum = iterationNum;
        return this;
    }

    public InputGen outputPath(String outputPath) {
        this.outputPath = outputPath;
        File outputFile = new File(outputPath);
        if (!outputFile.exists() || !outputFile.isDirectory()) {
            outputFile.mkdirs();
        }
        return this;
    }

    public void gen(Random random, int... randomRange) {
        for (int i = 0; i < threadNum; i++) {
            PrintWriter out = null;
            try {
                String fileName = outputPath + File.separator + (i + 1) + ".csv";
                out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
                for (int j = 0; j < iterationNum; j++) {
                    StringBuilder builder = new StringBuilder();
                    for (int parameter : randomRange) {
                        builder.append(1 + random.nextInt(parameter));
                        builder.append(',');
                    }
                    builder.setLength(builder.length() - 1);
                    out.println(builder.toString());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
