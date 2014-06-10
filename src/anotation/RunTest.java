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
public class RunTest {

    public static void main(String args[]) throws Exception {
        String CLASS_NAME = "anotation.TestAno";

        Class test = Class.forName(CLASS_NAME);

        boolean flag = test.isAnnotationPresent(Description.class);
        if (flag) {
            Description desc = (Description) test.getAnnotation(Description.class);
            System.out.println("desc: " + desc.value());
        }

        Method[] method = test.getMethods();
        Field[] field = test.getFields();
        System.out.println("field-count: " + field.length);
        Set<Field> set = new HashSet<Field>();
        for (int i = 0; i < field.length; i++) {
            boolean name_flag = field[i].isAnnotationPresent(Name.class);
            if (name_flag) {
                set.add(field[i]);
            }
        }

        for (Field f : set) {
            Name ff = f.getAnnotation(Name.class);
            System.out.println("name: " + ff.originate());
            System.out.println("comm: " + ff.community());
        }

//        Set<Method> set = new HashSet<Method>();
//        for(int i=0;i<method.length;i++)
//        {
//            boolean method_flag = method[i].isAnnotationPresent(Name.class);
//            if(method_flag) set.add(method[i]);
//        }
//        
//        for(Method m: set)
//        {
//            System.out.println("method: " +m.getName());
//            
//            Name name = m.getAnnotation(Name.class);
//            System.out.println("name: " + name.originate());
//            System.out.println("commu: " + name.community());
//        }
    }

    public static void testGetClassName() {
        // 方法1：通过SecurityManager的保护方法getClassContext()  
        String clazzName = new SecurityManager() {
            public String getClassName() {
                return getClassContext()[1].getName();
            }
        }.getClassName();
        System.out.println(clazzName);
        // 方法2：通过Throwable的方法getStackTrace()  
        String clazzName2 = new Throwable().getStackTrace()[1].getClassName();
        System.out.println(clazzName2);
        // 方法3：通过分析匿名类名称()  
        String clazzName3 = new Object() {
            public String getClassName() {
                String clazzName = this.getClass().getName();
                return clazzName.substring(0, clazzName.lastIndexOf('$'));
            }
        }.getClassName();
        System.out.println(clazzName3);
        //方法4：通过Thread的方法getStackTrace()  
        String clazzName4 = Thread.currentThread().getStackTrace()[2].getClassName();
        System.out.println(clazzName4);
    }
}
