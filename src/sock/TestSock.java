/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sock;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TestSock {
    
    
    public static void main(String[] args) throws Exception
    {
        socketTest();
    }
    
    public static void socketTest() throws Exception
    {
        String host = "www.scofier.com";
        Socket socket = new Socket(host, 80);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        //构造HTTP请求头
        String header = "GET http://"+host+"/ HTTP/1.0\r\n" +
                        //"Host: " + host + "\r\n" + 
                        "\r\n";
        
        out.write(header.getBytes());
        //正常的HTTP响应头第一行是这样的：HTTP/1.1 200 OK
        System.out.println("测试只读取一个字符: ");
        
        System.out.println("=>" + new String(readHeader(in, -1)));  
        
        int bb = in.read();
        //正常输出应该是： H
        System.out.println("" + (char)bb);
    }
    
    
    public static void TestUrl() throws Exception
    {
        URL url = new URL("http://www.scofier.com");
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        
        System.out.println("=>" + new String(readHeader(in, -1)));  
    }
    
   
    
    
    /**
     * 字节读取http头，字符串方式读取在合并编码的时候有问题
     *
     * @param is
     * @param length
     * @return
     */
    public static byte[] readHeader(InputStream is, int length) {
        // 此处读入请求数据并做相应的处理
        byte[] ret = null;
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        try {
            int len;
            ArrayList<String> al = new ArrayList<String>();
            
            while ((len = is.read()) != -1) {
                buff.write(len);
            }
            ret = buff.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
}


class Parts {
    String path, query, ref;

    Parts(String file) {
        int ind = file.indexOf('#');
        ref = ind < 0 ? null: file.substring(ind + 1);
        file = ind < 0 ? file: file.substring(0, ind);
        int q = file.lastIndexOf('?');
        if (q != -1) {
            query = file.substring(q+1);
            path = file.substring(0, q);
        } else {
            path = file;
        }
    }

    String getPath() {
        return path;
    }

    String getQuery() {
        return query;
    }

    String getRef() {
        return ref;
    }
}