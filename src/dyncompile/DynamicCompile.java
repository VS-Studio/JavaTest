/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dyncompile;

import java.io.InputStream;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author Administrator
 */
public class DynamicCompile {

    public static void main(String[] args) throws Exception{
        
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = javaCompiler.run(null, null, null,"src/dyncompile/Hello.java");

        if (result == 0) {
            System.out.println("compile success");
        }
        
        Runtime rt = Runtime.getRuntime();
        Process pc = rt.exec("java -cp ./src dyncompile.Hello");
        InputStream is = pc.getInputStream();
        byte[] buf = new byte[1024];
        is.read(buf);
        
        System.out.println(new String(buf));
    }
}
