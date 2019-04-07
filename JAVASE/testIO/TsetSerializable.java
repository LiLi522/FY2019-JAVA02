package com.ll.com.ll.testIO;

import java.io.*;

/**
*testserializable() 为序列化函数
 * test() 为反序列化函数
 * 如果不想让对象中的某个属性不被序列化，则在属性前面加上transient关键字
* */

public class TsetSerializable {
    public static void main(String[] args) {
        new TsetSerializable().testserializable();
        new TsetSerializable().test();
    }

    public void test() {
        InputStream is = null;
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream("G:" + File.separator + "LLLL" + File.separator + "Lovel.txt");
            ois = new ObjectInputStream(is);
            Object o = ois.readObject();
            if (o instanceof Student) {
                Student s = (Student)o;
                System.out.println(o.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void testserializable() {
        Student s = new Student(1,"LILI",22);
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            os = new FileOutputStream("G:" + File.separator + "LLLL" + File.separator + "Lovel.txt");
            oos = new ObjectOutputStream(os);
            oos.writeObject(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
