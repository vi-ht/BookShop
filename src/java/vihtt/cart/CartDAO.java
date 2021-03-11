/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.cart;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class CartDAO implements Serializable{
    public int insertCart (int Price, String name, String adress, String phone) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Insert Cart "
                                     + "Values(?,?,?,?)";     
            prm = con.prepareStatement(sql);
            prm.setInt(1, Price);
            prm.setString(2, name);
             prm.setString(3, adress);
              prm.setString(4, phone);
            int row =prm.executeUpdate();
            if(row>0){
                return getpos();}
                }//end if
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return 0;
     }
    
      public int getpos() throws SQLException, NamingException{
        Connection con = null;
        Statement sm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql = "SELECT CartID " +
                                         "FROM Cart " +
                                         "WHERE CartID >=ALL (SELECT  CartID " +
                                                                                    "FROM  Cart )";         
            sm =con.createStatement();
            rs = sm.executeQuery(sql);
                    if (rs.next()) {
                     int pos = rs.getInt("CartID");
                     return pos;
                    }
            }//end if connection is opened
        } finally {
            if (rs != null) {rs.close();}
            if (sm != null) {sm.close();}
            if (con != null) {con.close();}
        }
        return 0;
    }
}

