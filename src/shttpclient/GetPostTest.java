/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shttpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



/**
 *
 * @author Administrator
 */
public class GetPostTest {
    
    public static void main(String[] args) throws Exception
    {
        demo1();
    }
    
    public static void demo1() throws Exception
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://www.pconline.com.cn");
        CloseableHttpResponse response = httpclient.execute(httpget);
        
        System.out.println(new String(EntityUtils.toByteArray(response.getEntity()),"gb2312"));
        
    }
    
    
    public static void demo2() throws Exception
    {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.pconline.com.cn");
        CloseableHttpResponse response = httpclient.execute(httpget);
        
        System.out.println(new String(EntityUtils.toByteArray(response.getEntity()),"gb2312"));
        
    }
}
