/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ja.test;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TestInterface {
    
    public static void main(String args[])
    {
        Posts posts = new Posts();
        posts.add("1111");
        posts.add("2222");
        posts.add("3333");
        
        posts.proc(new Proc(){

            @Override
            public String handle(ArrayList<String> s) {
                for(String ss: s)
                {
                    System.out.println(ss);
                }
                return "";
            }
        
        });
    }
    
}

class Posts{
    private static ArrayList<String> str = new ArrayList<String>();
    public void add(String s)
    {
        str.add(s);
    }
    
    public void proc(Proc p)
    {
        p.handle(str);
    }
}

interface Proc{
    public String handle(ArrayList<String> s);
}
