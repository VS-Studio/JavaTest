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
public class Writer extends Thread{
    private Buffer buff;
    
    public Writer(Buffer buff)
    {
        this.buff = buff;
    }
    
    @Override
    public void run()
    {
        buff.write();
    }
}
