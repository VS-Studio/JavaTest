/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PersonServiceImpl extends UnicastRemoteObject implements PersonService{
    
    public PersonServiceImpl() throws RemoteException{
        super();
    }
    
    @Override
    public List<PersonEntity> getList() throws RemoteException {
        System.out.println("Get Person Start!");
        List<PersonEntity> personList = new LinkedList<PersonEntity>();
        PersonEntity person1 = new PersonEntity();
        person1.setAge(12);
        person1.setName("lili");
        person1.setId(1);
        personList.add(person1);
        
        PersonEntity person2 = new PersonEntity();
        person2.setAge(23);
        person2.setId(2);
        person2.setName("kaka");
        personList.add(person2);
        
        return personList;
    }
    
}
