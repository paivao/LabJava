/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author rafaelpaiva
 */
public class Aluno {
    private int codigo;
    private String nome;
    
    public Aluno (int codigo, String nome) {
	this.codigo = codigo;
	this.nome = nome;
    }
    
    public Aluno(String nome) {
	this.nome = nome;
    }
    
    public String getNome() {
	return nome;
    }
    
    public void setNome(String nome) {
	this.nome = nome;
    }
    
    public int getCodigo() {
	return codigo;
    }
    
    public void setCodigo(int cod) {
	codigo = cod;
    }
}
