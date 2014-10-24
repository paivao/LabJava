/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    
    Connection conexao;
    
    public AlunoDAO() throws ClassNotFoundException, SQLException {
	conexao = new ConnectionFactory().conexao();
    }
    
    public List<Aluno> listar() throws SQLException {
	List<Aluno> lista = new ArrayList<>();
	PreparedStatement st = conexao.prepareStatement("SELECT * FROM Aluno");
	ResultSet result = st.executeQuery();
	while (result.next()) {
	    lista.add(new Aluno(result.getInt("Codigo"), result.getString("Pessoa")));
	}
	st.close();
	return lista;
    }
    
    public List<Aluno> listar(String nome) throws SQLException {
	List<Aluno> lista = new ArrayList<>();
	PreparedStatement st = conexao.prepareStatement("SELECT * FROM Aluno WHERE Pessoa LIKE '%?%';");
	st.setString(1, nome);
	ResultSet result = st.executeQuery();
	while (result.next()) {
	    lista.add(new Aluno(result.getInt("Codigo"), result.getString("Pessoa")));
	}
	st.close();
	return lista;
    }
    
    public void adiciona(Aluno novo) {
	try {
	    PreparedStatement st = conexao.prepareStatement("INSERT INTO Alunos(Codigo, Pessoa) VALUES (?, ?);");
	    st.setInt(1, novo.getCodigo());
	    st.setString(2, novo.getNome());
	    st.execute();
	    st.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public void remove(List<Aluno> lista) {
	if (lista.isEmpty()) return;
	try {
	    StringBuilder sql = new StringBuilder("DELETE FROM Alunos WHERE Codigo IN (?");
	    for (int i = 1; i < lista.size(); i++)
		sql.append(",?");
	    sql.append(");");
	    PreparedStatement st = conexao.prepareStatement(sql.toString());
	    for (int i = 0; i < lista.size(); i++)
		st.setInt(i+1, lista.get(i).getCodigo());
	    st.execute();
	    st.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    public void remove(Aluno al) {
	try {
	    PreparedStatement st = conexao.prepareStatement("DELETE FROM Alunos WHERE Codigo=?;");
	    st.setInt(1, al.getCodigo());
	    st.execute();
	    st.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public void atualiza(Aluno al) {
	try {
	    PreparedStatement st = conexao.prepareStatement("UPDATE FROM Alunos SET Pessoa=? WHERE Codigo=?;");
	    st.setString(1, al.getNome());
	    st.setInt(2, al.getCodigo());
	    st.execute();
	    st.close();
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    public void fechar() throws SQLException {
	conexao.close();
    }
}
