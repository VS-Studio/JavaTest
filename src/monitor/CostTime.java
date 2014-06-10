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
public class CostTime {
    private static long startTimestamp = System.currentTimeMillis();
    
    public static long startTimestamp()
    {
        return startTimestamp(false);
    }
    public static long startTimestamp(boolean end)
    {
        long ret = 0;
        if(end)
        {
            ret = System.currentTimeMillis() - startTimestamp;
        }else{
            ret = startTimestamp = System.currentTimeMillis();
        }
        System.out.println("time: " + ret);
        return ret;
    }
    
    
    
    public static void main(String[] args)
    {
        startTimestamp(true);
        havyCalc();
        startTimestamp(true);
        
    }
    
    
    public static void havyCalc()
    {
        for(int i=0;i<100000;i++)
        {
            double s = i * 0.5 + Math.atan(i);
            Math.sin(s);
        }
    }
}
