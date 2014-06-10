/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package str;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author Administrator
 */
public class StrMapFun {
    
    public static void main(String[] args) throws Exception
    {
        String fun = "hello";
        Params pars = new Params("name=scofier&age=22");
//        pars.add("name", "scofier");
//        pars.add("age", 12);
        Class mpfunc = null;
        Object mpobj = null;
        try {
            mpfunc = Class.forName("str.MapFunc");
            mpobj = mpfunc.newInstance();
            mpfunc.getMethod(fun, Params.class).invoke(mpobj, pars);
        } catch (Exception ex) {
            mpfunc.getMethod(fun, null).invoke(mpobj, null);
        }
    }
}


class MapFunc{
    
    public static void hello()
    {
        System.out.println("hello no param");
    }
    
    public static void hello(Params p)
    {
        
        System.out.println("hello Params : " + p.get("name"));
        System.out.println("hello Params size : " + p.size());
        
        HashMap pa = p.toMap();
        Iterator it = pa.entrySet().iterator();
        while(it.hasNext())
        {
            Entry en = (Entry)it.next();
            System.out.println("hello Params key: " + en.getKey() + ",value=" + en.getValue());
        }
        
        
    }
    
    public static void hello(String[] par)
    {
        System.out.println("hello");
    }
}

class Params
{
    private static HashMap params = new HashMap();
    
    public Params(){}
    public Params(String str)
    {
        //解析字符串
        String[] items = str.split("&");
        for(String item:items)
        {
            String[] kv = item.split("=");
            add(kv[0],kv[1]);
        }
    }
    
    public int size()
    {
        return params.size();
    }
    
    public void add(String key, Object val){
        params.put(key, val);
    }
    
    public Object get(String key)
    {
        return params.get(key);
    }
    
    public HashMap toMap()
    {
        return params;
    }
}