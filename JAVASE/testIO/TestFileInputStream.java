package com.ll.com.ll.testIO;

import java.io.*;

public class TestFileInputStream {
    public  static void main(String[] args) {
        TestFileInputStream ti = new TestFileInputStream();
        ti.testFileInputStream();
    }
    /***
     *
     * 输入流
     * 将文件中的数据读入到程序中
     */
    public void testFileInputStream() {
        //step1:建立管道
        File file = new File("G:" + File.separator + "LLLL" + File.separator + "Love.txt");
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int result = fis.read();
            while (result != -1) {
                System.out.print((char)result);
                result = fis.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
