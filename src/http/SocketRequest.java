/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Administrator
 */
public class SocketRequest {
    
    public static void main(String args[])
    {
        get();
    }
    
    public static void get()
    {
        try {
			Socket s = new Socket("www.pconline.com.cn",80);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(),"GBK"));
			OutputStream out = s.getOutputStream();
			StringBuffer sb = new StringBuffer("GET /index.html HTTP/1.1\r\n");
			sb.append("User-Agent: Java/1.6.0_20\r\n");
			sb.append("Host: www.pconline.com.cn:80\r\n");
			sb.append("Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\n");
			sb.append("Connection: Close\r\n");
			sb.append("\r\n");
			out.write(sb.toString().getBytes());
			String tmp = "";
			while((tmp = br.readLine())!=null){
				System.out.println(tmp);
			}
			out.close();
			br.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
}
