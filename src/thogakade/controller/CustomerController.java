/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogakade.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import thogakade.db.DBConnection;
import thogakade.model.Customer;


/**
 *
 * @author student
 */
public class CustomerController {
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        String SQL="Insert into Customer Values(?,?,?,?)";
        PreparedStatement prepareStatement = connection.prepareStatement(SQL);
        prepareStatement.setObject(1, customer.getId());
        prepareStatement.setObject(2, customer.getName());
        prepareStatement.setObject(3, customer.getAddress());
        prepareStatement.setObject(4, customer.getSalary());
        return prepareStatement.executeUpdate()>0;
    }

     public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        String SQL="Update Customer set name=?, address=?, salary=? where id=?";
        PreparedStatement prepareStatement = connection.prepareStatement(SQL);
        prepareStatement.setObject(1, customer.getName());
        prepareStatement.setObject(2, customer.getAddress());
        prepareStatement.setObject(3, customer.getSalary());
        prepareStatement.setObject(4, customer.getId());
        return prepareStatement.executeUpdate()>0;
    } 
     
    public static boolean  deleteCustomer(String id) throws ClassNotFoundException, SQLException{
       Connection connection=DBConnection.getDBConnection().getConnection();
       Statement stm=connection.createStatement();
       String SQL="Delete from Customer where id='"+id+"'";
       return  stm.executeUpdate(SQL)>0;
    }
    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        Statement stm=connection.createStatement();
        String SQL="Select * from Customer where id='"+id+"'";
        ResultSet rst = stm.executeQuery(SQL);
       /* if(rst.next()){
            Customer customer=new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary"));
            return customer;
        }else{
            return null;
        }*/
        return rst.next()? new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary")) : null;
    }
    public static ArrayList<Customer>getAllCustomer() throws ClassNotFoundException, SQLException{
        Connection connection=DBConnection.getDBConnection().getConnection();
        Statement stm=connection.createStatement();
        ResultSet rst=stm.executeQuery("Select * From Customer");
        ArrayList<Customer>customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer=new Customer(rst.getString("id"),rst.getString("name"),rst.getString("address"),rst.getDouble("salary"));
            customerList.add(customer);
        }
        return customerList;
    }
}
