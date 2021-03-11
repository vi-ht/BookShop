/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.item;

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
public class ItemDAO implements Serializable{

        public ArrayList<ItemDTO> getAllBook() throws SQLException, NamingException{
                        ArrayList<ItemDTO> ItemList = new ArrayList<>();
                       Connection con = null;
                       Statement sm = null;
                       ResultSet rs = null;
                       String lastname = null;
                       try {
                           con = DBHelper.makeConnection();
                           if (con!=null) {
                               String sql =  "Select ItemID, Title, Price "
                                                   + "From Item ";  
                           sm =con.createStatement();
                           rs = sm.executeQuery(sql);
                                   while (rs.next()) {
                                   String title = rs.getString("Title");
                                   int price = rs.getInt("Price");
                                   String ID= rs.getString("ItemID");
                                   ItemDTO dto = new ItemDTO(ID, title, price,0);
                                   ItemList.add(dto);
                                   }
                           }//end if connection is opened
                       } finally {
                           if (rs != null) {rs.close();}
                           if (sm != null) {sm.close();}
                           if (con != null) {con.close();}
                       }
                       return ItemList;
    }
        
        public ItemDTO getBookByID(String ID,int num) throws SQLException, NamingException{
                  Connection con = null;
                  ResultSet rs = null;
                  PreparedStatement prm = null;
                  String lastname = null;
                  try {
                      con = DBHelper.makeConnection();
                      if (con!=null) {
                          String sql =  "Select ItemID, Title, Price "
                                              + "From Item "
                                              + "Where  ItemID = ? ";        
                      prm = con.prepareStatement(sql);
                      prm.setString(1, ID);
                      rs = prm.executeQuery();
                              if(rs.next()) {
                              String title = rs.getString("Title");
                              int price = rs.getInt("Price");
                              String itemID= rs.getString("ItemID");
                              ItemDTO dto = new ItemDTO(itemID, title, price,num);
                              return dto;
                              }
                      }//end if connection is opened
                  } finally {
                      if (rs != null) {rs.close();}
                      if (prm != null) {prm.close();}
                      if (con != null) {con.close();}
                  }
                  return null;
              }
}
