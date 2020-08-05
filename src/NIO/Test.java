package NIO;

import zuoshen.输入输出练习.E;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
    //nio向本地文件写数据
    public static void write()throws Exception{
        String str="123";
        FileOutputStream fileOutputStream=new FileOutputStream("basic1.txt");
        FileChannel fc=fileOutputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
//        byteBuffer.flip();//https://blog.csdn.net/hbtj_1216/article/details/53129588
        fc.write(byteBuffer);
        fileOutputStream.close();
    }
    //nio从本地文件读数据
    public static void read()throws Exception{
        File file=new File("basic1.txt");
        FileInputStream fis=new FileInputStream(file);
        FileChannel fileChannel=fis.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate((int)file.length());
        fileChannel.read(buffer);
        System.out.println(new String(buffer.array()));

    }
    public static void main(String[] args) throws Exception {
//        write();
        read();
    }
}
