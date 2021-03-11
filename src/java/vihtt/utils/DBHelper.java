/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.utils;
/**
 *
 * @author Thanh Vi
 */
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class DBHelper implements Serializable{
    public static Connection makeConnection()
        throws /*ClassNotFoundException*/SQLException, NamingException {
        //1,lấy context hiện hành
        Context context = new InitialContext();
        //2,get server contex
        Context tomcatContex = (Context)context.lookup("java:comp/env");
        //3,get datasource
        DataSource ds = (DataSource)tomcatContex.lookup("ThanhVi");
        //4.create con from ds
        Connection con = ds.getConnection();
        return con;
        }
}
