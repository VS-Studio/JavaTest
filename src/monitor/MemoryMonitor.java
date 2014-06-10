/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package monitor;

/**
 *
 * @author Administrator
 */
public class MemoryMonitor {
    
    public static void main(String[] args)
    {
        getMem();
    }
    
    public static void getMem()
    {
        
        Long maxMen = Runtime.getRuntime().maxMemory();
        Long totalMen = Runtime.getRuntime().totalMemory();
        Long freeMen = Runtime.getRuntime().freeMemory();
        
        
        System.out.println("max: "+maxMen+",total: "+totalMen+",free:"+freeMen+"");
        
    }
    
}
