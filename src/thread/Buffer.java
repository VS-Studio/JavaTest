/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Administrator
 */
public class Buffer {
    private ReentrantLock lock = new ReentrantLock();
    
    public void write()
    {
        lock.lock();
        try{
            long startTime = System.currentTimeMillis();
            System.out.println("start write buff data...");
            for(;;)
            {
                if(System.currentTimeMillis() - startTime > Integer.MAX_VALUE)
                break;
            }
            System.out.println("write finished.");
        }finally{
            lock.unlock();
        }
    }
    
    public void read() throws Exception
    {
        lock.lockInterruptibly();
        try
        {
            System.out.println("read buff data");
        }finally{
            lock.unlock();
        }
    }
    
}
