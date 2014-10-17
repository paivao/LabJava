/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musica;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaelpaiva
 */
public class Musica {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws ClassNotFoundException {
	Class.forName("com.mysql.jdbc.Driver");
	try {
	    Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/musica", "muser", "1234");
	    Statement stmt = (Statement) con.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Estilo;");
	    while (rs.next()) {
		System.out.println("Estilo: " + rs.getString("nome"));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(Musica.class.getName()).log(Level.SEVERE, null, ex);
	    System.exit(1);
	}
    }
    
}
