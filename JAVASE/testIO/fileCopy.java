package com.ll.com.ll.testIO;

import java.io.*;

public class fileCopy {
    public static void main(String[] args) {
        new fileCopy().filecopy();
    }
    public void filecopy() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("G:" + File.separator + "LLLL" + File.separator + "Love.txt");
            File file = new File("G:" + File.separator + "LLLL" + File.separator + "LoveMe.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            int result = 0;
            while ((result = fis.read()) != -1) {
                fos.write(result);
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
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
