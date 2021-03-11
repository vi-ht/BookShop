/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.bill;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;

/**
 *
 * @author Thanh Vi
 */
public class BillDAO implements Serializable{
    public boolean insertItem (int cartID, String itemID, int Number) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Insert Bill "
                                     + "Values(?,?,?)";     
            prm = con.prepareStatement(sql);
            prm.setInt(1, cartID);
            prm.setString(2, itemID);
             prm.setInt(3, Number);
            int row =prm.executeUpdate();
            if(row>0){
                return true;}
                }//end if
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return false;
     }
}
