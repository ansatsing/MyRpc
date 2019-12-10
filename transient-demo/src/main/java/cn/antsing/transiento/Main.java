package cn.antsing.transiento;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public  class Main {
    public static void main(String[] args) {
        testTransient();
    }


    public static class TransientTest implements Serializable {
        private static final long serialVersionUID = 233858934995755239L;
        private String name1;
        private String name2;
       // private transient Date date;

        public TransientTest(String name1,String name2){
            this.name1 = name1;
            this.name2 = name2;
        }
        @Override
        public String toString(){
            return String.format("TransientTest.toString(): name1=%s,name2=%s", name1,name2);
        }
        ///////////////////////////////  ///////////////
        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
            s.defaultWriteObject();
            s.writeObject(name1);
            s.writeObject(name2);
            System.out.println("序列化对象");
        }
        private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            name1=String.valueOf(s.readObject())+"1";
            name2=String.valueOf(s.readObject())+"1";
            System.out.println("反序列化对象");
        }
    }

    public static void testTransient(){
        String name1="常规属性",name2="transient修饰的属性";
        TransientTest test = new TransientTest(name1, name2);
        System.out.println("序列化前："+test.toString());
        ObjectOutputStream outStream;
        ObjectInputStream inStream;
        String filePath = "F:\\nonWork_project_dir\\MyRpc\\transient-demo\\src\\main\\resources\\TransientTest.obj";
        try {
            outStream = new ObjectOutputStream(new FileOutputStream(filePath));
            outStream.writeObject(test);

            inStream = new ObjectInputStream(new FileInputStream(filePath));
            TransientTest readObject = (TransientTest)inStream.readObject();
            System.out.println("序列化后："+readObject.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


