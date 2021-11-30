/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.login;

import duyla.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author ANH DUY
 */
public class LoginDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Select username "
                        + "From member "
                        + "Where username = ? And password = ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 execute query
                rs = stm.executeQuery();
                //2.4 process data
                if (rs.next()) {
                    return true;
                }

            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
     public String getFullname(String username, String password)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Select lastname "
                        + "From member "
                        + "Where username = ? And password = ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 execute query
                rs = stm.executeQuery();
                //2.4 process data
                if (rs.next()) {
                    return rs.getNString("lastname");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    private List<LoginDTO> accountList;

    public List<LoginDTO> getAccountList() {
        return accountList;
    }

    public void searchLastName(String searchValue)
            throws SQLException, /*ClassNotFoundException*/ NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2.1 create sql string
                String sql = "Select username, password, lastname, role "
                        + "From member "
                        + "Where lastname Like ?";
                //2.2 create statement object and set parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3 execute query
                rs = stm.executeQuery();
                //2.4 process data
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("role");

                    LoginDTO dto = new LoginDTO(username, password, fullName, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end if account list is not allocated
                    this.accountList.add(dto);
                }//end while
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1.connect sql
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. query
                //2,1 creat SQL string
                String sql = "Delete from member "
                        + "Where username =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateAccount(String username, String password, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update member "
                        + "Set password = ?, role = ? "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createAccount(String username, String password, String fullname,
            boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert Into member(username, password, lastname, role) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

//    public LoginDTO getAccountByUsernameAndPssword(String username, String password) throws NamingException, SQLException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        LoginDTO dto = null;
//
//        try {
//            //1. connect to database
//            con = DBHelper.makeConnection();
//            if (con != null) {
//                //2. create SQL String
//                String sql = "SELECT fullname FROM member WHERE username = ? AND password = ?";
//                //3. Create statement
//                stm = con.prepareStatement(sql);
//                stm.setString(1, username);
//                stm.setString(2, password);
//                //4. Query data
//                rs = stm.executeQuery();
//                //5. Process data
//                if (rs.next()) {
//                    String fullname = rs.getString("fullname");
//                    dto = new LoginDTO(username, password, fullname, true);
//
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return dto;
//
//    }
}
