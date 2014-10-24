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
 * Classe DAO para Aluno.
 * @author rafaelpaiva
 */
public class AlunoDAO {
    
    Connection conexao;
    
    /**
     * Construtor default. Cria a conexão
     * @throws ClassNotFoundException Caso haja problema para encontrar o driver MySQL
     * @throws SQLException Excessão SQL na criação da conexão, gerada pela JDBC
     */
    public AlunoDAO() throws ClassNotFoundException, SQLException {
	conexao = new ConnectionFactory().conexao();
    }
    
    /**
     * Lista todos os alunos.
     * @return ArrayList dos alunos.
     * @throws SQLException Excessão do JDBC.
     */
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
    
    /**
     * Listar os alunos com nomes parecidos com o fornecido.
     * @param nome Nome para buscar.
     * @return ArrayList dos alunos encontrados.
     * @throws SQLException Excessão do JDBC. 
     */
    public List<Aluno> listar(String nome) throws SQLException {
	List<Aluno> lista = new ArrayList<>();
	PreparedStatement st = conexao.prepareStatement("SELECT * FROM Aluno WHERE Pessoa LIKE ?;");
	st.setString(1, '%'+nome+'%');
	ResultSet result = st.executeQuery();
	while (result.next()) {
	    lista.add(new Aluno(result.getInt("Codigo"), result.getString("Pessoa")));
	}
	st.close();
	return lista;
    }
    
    /**
     * Adiciona novo aluno na tabela.
     * @param novo Aluno a ser inserido na tabela.
     * @throws SQLException Excessão do JDBC.
     */
    public void adiciona(Aluno novo) throws SQLException {
	    PreparedStatement st = conexao.prepareStatement("INSERT INTO Aluno (Codigo, Pessoa) VALUES (?, ?);");
	    st.setInt(1, novo.getCodigo());
	    st.setString(2, novo.getNome());
	    st.execute();
	    st.close();
    }
    
    /**
     * Remove os alunos que estiverem na lista da tabela.
     * @param lista Lista de alunos a serem removidos. 
     * @throws SQLException Excessão do JDBC.
     */
    public void remove(List<Aluno> lista) throws SQLException {
	if (lista.isEmpty()) return;
	    StringBuilder sql = new StringBuilder("DELETE FROM Aluno WHERE Codigo IN (?");
	    for (int i = 1; i < lista.size(); i++)
		sql.append(",?");
	    sql.append(");");
	    PreparedStatement st = conexao.prepareStatement(sql.toString());
	    for (int i = 0; i < lista.size(); i++)
		st.setInt(i+1, lista.get(i).getCodigo());
	    st.execute();
	    st.close();
    }
    
    /**
     * Remove um aluno da tabela.
     * @param al Aluno a ser removido da tabela.
     * @throws SQLException Excessão do JDBC.
     */
    public void remove(Aluno al) throws SQLException {
	    PreparedStatement st = conexao.prepareStatement("DELETE FROM Aluno WHERE Codigo=?;");
	    st.setInt(1, al.getCodigo());
	    st.execute();
	    st.close();
    }
    
    /**
     * Atualiza o aluno na tabela.
     * @param al Aluno a ser atualizado.
     * @throws SQLException Excessão do JDBC.
     */
    public void atualiza(Aluno al) throws SQLException {
	    PreparedStatement st = conexao.prepareStatement("UPDATE FROM Aluno SET Pessoa=? WHERE Codigo=?;");
	    st.setString(1, al.getNome());
	    st.setInt(2, al.getCodigo());
	    st.execute();
	    st.close();
    }
    
    /**
     * Fecha a conexão. RECOMENDA-SE FAZER ISSO AO FIM DO PROGRAMA!
     * @throws SQLException Excessão gerada pelo JDBC.
     */
    public void fechar() throws SQLException {
	conexao.close();
    }
}
