/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ja.test;


import java.net.ServerSocket;
import java.net.Socket;

public class SocketProxy {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(8989);
		while (true) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				new SocketThread(socket).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

