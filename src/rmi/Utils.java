/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

/**
 *
 * @author Administrator
 */
public class Utils {
    private static Utils self = new Utils();
    
    public static String getConfig(String key) throws Exception
    {
        String api = "";
        Properties pp = new Properties();
        InputStream is = self.getClass().getClassLoader().getResourceAsStream("rmi/config.properties");
        pp.load(is);
        Iterator it = pp.entrySet().iterator();
        while(it.hasNext())
        {
            Entry en = (Entry)it.next();
            if(en.getKey().equals(key.toLowerCase()))
            {
                api = (String)en.getValue();
            }
        }
        System.out.println("api: " + api);
        return api;
    }
    
    public static void main(String[] args) throws Exception
    {
        getConfig("api");
    }
}
