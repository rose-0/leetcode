package BIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss=new ServerSocket(9999);
        while (true){
            Socket s=ss.accept();//监听9999端口号
            InputStream is=s.getInputStream();
            byte[]b=new byte[10];
            is.read(b);//读取发来的数据
            String clinetIP=s.getInetAddress().getHostAddress();
            System.out.println(clinetIP+"说："+new String(b).trim());
            OutputStream os=s.getOutputStream();
            os.write("没钱".getBytes());//返回响应的数据
            s.close();

        }
    }
}
