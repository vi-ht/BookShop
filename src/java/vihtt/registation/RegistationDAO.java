/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.registation;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vihtt.utils.DBHelper;
/**
 *
 * @author Thanh Vi
 */
public class RegistationDAO implements Serializable{
    public RegistationDTO checkLogin(String us, String pw)
            throws SQLException, NamingException{
                  Connection con = null;
                  PreparedStatement prm = null;
                  ResultSet rs = null;
                  try {
                           con = DBHelper.makeConnection();
                          if (con!=null) {
                                    String sql =  "Select username, password, lastname, Admin  "
                                                        + "from Registation "
                                                        + "Where username  = ? And password = ?";   
                          prm = con.prepareStatement(sql);
                          prm.setString(1, us);
                          prm.setString(2, pw);
                          rs = prm.executeQuery();
                                    if (rs.next()) {
                                    String username = rs.getString("username");
                                    String password = rs.getString("password");
                                    String lastname = rs.getString("lastname");
                                    boolean role = rs.getBoolean("Admin");
                                    RegistationDTO dto = new RegistationDTO(username, password, lastname, role);
                                    return dto;
                                    }//end if
                          }//end if connection is opened
                  } finally {
                      if (rs != null) {rs.close();}
                      if (prm != null) {prm.close();}
                      if (con != null) {con.close();}
                  }
                  return null;
    }
    
    private List<RegistationDTO> AccountList;

    public List<RegistationDTO> getAccountList() {
        return AccountList;
    }
     public void  searchLastName(String searchValue)
            throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Select username, password, lastname, Admin "
                                    + "From Registation "
                                    + "Where lastname  Like ? ";    
            prm = con.prepareStatement(sql);
            prm.setString(1, "%"+searchValue+"%");
            rs = prm.executeQuery();
                    while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("Admin");
                    RegistationDTO dto = new RegistationDTO(username, password, lastname, role);
                    if(this.AccountList==null){
                        this.AccountList = new ArrayList<RegistationDTO>();
                    }
                    this.AccountList.add(dto);
                    }
            }
        } finally {
            if (rs != null) {rs.close();}
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
       
    }
     
     public boolean deleteAccount (String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Delete "
                                    + "From Registation "
                                    + "Where username  = ? ";    
            prm = con.prepareStatement(sql);
            prm.setString(1, username);
            int row =prm.executeUpdate();
            if(row>0){return true;}
                }//end if
        } finally {
            if (prm != null) {prm.close();}
            if (con != null) {con.close();}
        }
        return false;
     }
     
     public boolean UpdateAccount (String username, String password, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Update Registation "
                                     + "set password = ?, Admin=? "
                                    + "Where username  = ? ";         
            prm = con.prepareStatement(sql);
            prm.setString(1, password);
            prm.setBoolean(2, role);
            prm.setString(3, username);
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
     
      public boolean InsertItem (String username, String password, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Update Registation "
                                     + "set password = ?, Admin=? "
                                    + "Where username  = ? "; 
            prm = con.prepareStatement(sql);
            prm.setString(1, password);
            prm.setBoolean(2, role);
            prm.setString(3, username);
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
      
      public boolean  createAccount(String usename, String pass, String fullname, boolean role)throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement prm = null;
        try {
            con = DBHelper.makeConnection();
            if (con!=null) {
                String sql =  "Insert into Registation(username, password, lastname, Admin) "
                                     + "values(?, ?, ?, ?) ";   
            prm = con.prepareStatement(sql);
            prm.setString(1, usename);
            prm.setString(2, pass);
            prm.setString(3, fullname);
            prm.setBoolean(4, role);
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
