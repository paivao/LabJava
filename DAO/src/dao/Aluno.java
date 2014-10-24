/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 * Classe que representa uma entidade aluno, unicamente com getters e setters.
 * @author rafaelpaiva
 */
public class Aluno {
    private int codigo;
    private String nome;
    
    /**
     * Construtor. Cria aluno com Código e Nome.
     * @param codigo Código do aluno.
     * @param nome Nome do aluno.
     */
    public Aluno (int codigo, String nome) {
	this.codigo = codigo;
	this.nome = nome;
    }
    
    /**
     * Construtor com apenas código. Recomenda-se usá-lo somente para simples conferência.
     * @param nome Nome do aluno.
     */
    public Aluno(String nome) {
	this.nome = nome;
    }
    
    /**
     * Pega o nome do aluno.
     * @return Nome do aluno.
     */
    public String getNome() {
	return nome;
    }
    
    /**
     * Altera o nome do aluno.
     * @param nome Novo nome do aluno.
     */
    public void setNome(String nome) {
	this.nome = nome;
    }
    
    /**
     * Pega o código do aluno.
     * @return Código do aluno.
     */
    public int getCodigo() {
	return codigo;
    }
    
    /**
     * Altera o código do aluno. Não recomenda-se fazer isso sempre, já que Código é PK.
     * @param cod Novo código.
     */
    public void setCodigo(int cod) {
	codigo = cod;
    }
}
