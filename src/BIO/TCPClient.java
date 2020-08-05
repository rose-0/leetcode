package BIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        while (true){
            Socket s=new Socket("127.0.0.1",9999);
            //取出输出流发消息
            OutputStream os=s.getOutputStream();
            System.out.println("请输入");
            Scanner sc=new Scanner(System.in);
            String msg=sc.nextLine();
            os.write(msg.getBytes());
            //取出输入流发消息
            InputStream is=s.getInputStream();
            byte[]b=new byte[20];
            is.read(b);
            System.out.println("老板说："+new String(b).trim());
            s.close();
        }
    }
}
