/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe main. Testando o AlunoDAO
 * @author rafaelpaiva
 */
public class DAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	AlunoDAO dao = null;
	try {
	    dao = new AlunoDAO();
	    List<Aluno> lista = new ArrayList<>();
	    /*
	    dao.adiciona(new Aluno(12040, "Paiva"));
	    dao.adiciona(new Aluno(12016, "Matos"));
	    dao.adiciona(new Aluno(12032, "Mendes"));
	    */
	    lista = dao.listar();
	    System.out.println("Todos os alunos: ");
	    for (Aluno al: lista) {
		System.out.println(al.getCodigo()+" "+al.getNome());
	    }
	    lista = dao.listar("d");
	    if (!lista.isEmpty()) {
		    System.out.println("\nAgora só com d:");
		for (Aluno al: lista) {
		    System.out.println(al.getCodigo()+" "+al.getNome());
		}
	    } else {
		System.out.println("Nenhum aluno com d encontrado.");
	    }
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	} finally {
	    try {
		dao.fechar();
	    } catch (SQLException ex) {
		Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	
    }
    
}
