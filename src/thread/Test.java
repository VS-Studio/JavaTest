/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thread;

/**
 *
 * @author Administrator
 */
public class Test {
    public static void main(String[] args)
    {
        Buffer buff = new Buffer();
        
        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);
        
        writer.start();
        reader.start();
        
        new Thread(new Runnable(){

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for(;;)
                {
                    if(System.currentTimeMillis() - start > 5000){
                        System.out.println("not wait, quit");
                        reader.interrupt();
                        break;
                    }
                }
            }
            
        }).start();
    }
}
