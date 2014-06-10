/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
@Description("this is a description anotation")
public class TestAno {
    @Name(originate="originate name", community="communiti name")
    private String getName;
    
    @Name(originate="originate name2", community="communiti name2")
    private String getName2;
    
    
    public static void main(String args[]) throws Exception
    {
        RunPor.run(TestAno.class);
    }
    
}


class RunPor{
    
    public static void run(Class cls) throws Exception
    {
        String CLASS_NAME = cls.getName();
        
        Class test = Class.forName(CLASS_NAME);
        
        boolean flag = test.isAnnotationPresent(Description.class);
        if(flag)
        {
            Description desc = (Description) test.getAnnotation(Description.class);
            System.out.println("desc: " + desc.value());
        }
        
        Method[] method = test.getMethods();
        Field[] field = test.getDeclaredFields();
        System.out.println("field-count: " + field.length);
        Set<Field> set = new HashSet<Field>();
        for(int i=0;i<field.length;i++)
        {
            boolean name_flag = field[i].isAnnotationPresent(Name.class);
            if(name_flag) set.add(field[i]);
        }
        
        for(Field f:set)
        {
            Name ff = f.getAnnotation(Name.class);
            System.out.println("name: " + ff.originate());
            System.out.println("comm: " + ff.community());
        }
    }
}