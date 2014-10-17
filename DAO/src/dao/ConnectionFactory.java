/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rafaelpaiva
 */
public class ConnectionFactory {
    private String database;
    private String user;
    private String password;
    private String server;
    private String tcpport;
    
    private Connection conexao;
    
    public ConnectionFactory () {
	this.setServer("localhost");
	this.setTcpport("3306");
	this.setDatabase("labjava");
	this.setUser("userlabjava");
	this.setPassword("userlabjava");
	
	conexao = null;
    }
    
    public Connection conexao() throws ClassNotFoundException, SQLException {
	if (conexao == null) {
	    Class.forName("com.mysql.jdbc.Driver");
	    conexao = (Connection) DriverManager.getConnection("jdbc:mysql://"+getServer()+":"
		+getTcpport()+"/"+getDatabase(), getUser(), getPassword());
	}
	return conexao;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
	return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
	this.database = database;
    }

    /**
     * @return the user
     */
    public String getUser() {
	return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
	this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * @return the server
     */
    public String getServer() {
	return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
	this.server = server;
    }

    /**
     * @return the tcpport
     */
    public String getTcpport() {
	return tcpport;
    }

    /**
     * @param tcpport the tcpport to set
     */
    public void setTcpport(String tcpport) {
	this.tcpport = tcpport;
    }
}
