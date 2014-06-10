/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class ServiceApi {
    public static void main(String[] args)
    {
        try{
            PersonService personService = new PersonServiceImpl();
            LocateRegistry.createRegistry(6600);
            Naming.rebind(Utils.getConfig("api"), personService);
            System.out.println("Service Start!");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
