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
public class Reader extends Thread{
    private Buffer buff;
    
    public Reader(Buffer buff)
    {
        this.buff = buff;
    }
    @Override
    public void run()
    {
        try{
            buff.read();
        }catch(Exception e)
        {
            System.out.println("read interrupt");
        }
        System.out.println("read finished");
    }
}
