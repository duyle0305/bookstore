/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ANH DUY
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() 
        throws /*ClassNotFoundException*/NamingException, SQLException{
        
        Context currentContext = new InitialContext();
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("JohnyEnglish");
        Connection con = ds.getConnection();
        return con;
                
        //1.1 Load Drive
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2.1 Create url string
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=demo;"
//                + "instanceName=SA";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        
//        return con;
    }
}
