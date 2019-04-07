package com.ll.com.ll.testIO;

import java.io.*;

public class TestFileOutputStream {
    public static void main(String[] args) {
        TestFileOutputStream t = new TestFileOutputStream();
        t.testOutput();
    }
    /**
     * 输出流
     * 将程序中的数据写在文件当中
     * */
    public void testOutput() {
        String str = "Hello LiLi";
        File file = new File("G:" + File.separator + "LLLL" + File.separator + "Love.txt");
        OutputStream ops = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
                ops = new FileOutputStream(file);
                ops.write(str.getBytes());
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ops != null) {
                    ops.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
