package SembilanSepuluh14259.helpers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ssa
 */
public class DBConnect
{
    private static final String URL = "jdbc:sqlite:passwordstore.db";
    private static Connection instance;
    
    private DBConnect() {} // preventing instantiation
    
    public static Connection connect() throws SQLException
    {
        if (instance == null || instance.isClosed())
        {
            try
            {
                instance = DriverManager.getConnection(URL);
            }
            
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        return instance;
    }
}
