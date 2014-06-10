/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import org.junit.Test;

public class MultithreadJIoSocketTest
{

    public static void main(String[] args) throws Exception
    {
        new MultithreadJIoSocketTest().testMultithreadJIoSocket();
    }
    
    public void testMultithreadJIoSocket() throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(10002);
        Thread thread = new Thread(new Accptor(serverSocket));
        thread.start();
        
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }
    
    public class Accptor implements Runnable
    {
        private ServerSocket serverSocket;
        
        public Accptor(ServerSocket serverSocket)
        {
            this.serverSocket = serverSocket;
        }

        public void run()
        {
            while (true)
            {
                Socket socket = null;
                try
                {
                    socket = serverSocket.accept();
                    if(socket != null)
                    {
                        System.out.println("收到了socket：" + socket.getRemoteSocketAddress().toString());
                        Thread thread = new Thread(new Processor(socket));
                        thread.start();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public class Processor implements Runnable
    {
        private Socket socket;
        
        public Processor(Socket socket)
        {
            this.socket = socket;
        }
        
        @Override
        public void run()
        {
            try
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String readLine;
                while(true)
                {
                    readLine = in.readLine();
                    System.out.println("收到消息" + readLine);
                    if("end".equals(readLine))
                    {
                        break;
                    }
                    //客户端断开连接
                    socket.sendUrgentData(0xFF);
                    Thread.sleep(5000);
                }
            }
            catch (InterruptedException e)
            {
               e.printStackTrace();
            }
            catch (SocketException se)
            {
                System.out.println("客户端断开连接");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        
    }
}
