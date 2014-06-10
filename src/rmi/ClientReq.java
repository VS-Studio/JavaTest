/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ClientReq {
    public static void main(String[] args)
    {
        try
        {
            PersonService personService = (PersonService)Naming.lookup(Utils.getConfig("api"));
            List<PersonEntity> personList = personService.getList();
            for(PersonEntity person: personList)
            {
                System.out.println("id: " + person.getId() + ", Age: "+ person.getAge() + ", Name: " + person.getName());
            }
        } catch (Exception ex) {
            Logger.getLogger(ClientReq.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
