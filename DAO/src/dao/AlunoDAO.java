/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class AlunoDAO {
    
    public List<Aluno> listar() throws ClassNotFoundException, SQLException {
	List<Aluno> lista = new ArrayList<>();
	ConnectionFactory fabrica = new ConnectionFactory();
	Statement st = fabrica.conexao().createStatement();
	ResultSet result = st.executeQuery("SELECT * FROM Aluno");
	while (result.next()) {
	    lista.add(new Aluno(result.getInt("Codigo"), result.getString("Pessoa")));
	}
	return lista;
    }
    
}
