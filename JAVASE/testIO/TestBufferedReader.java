package com.ll.com.ll.testIO;

import java.io.*;

public class TestBufferedReader {
    public static void main(String[] args) {
        new TestBufferedReader().testBufferedReader();
    }

    public void testBufferedReader() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            fis = new FileInputStream("G:" + File.separator + "LLLL" + File.separator + "Love.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
