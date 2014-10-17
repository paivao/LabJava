/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 * Classe para checar se uma string é palíndromo de forma iterativa.
 * Implementa a interface Palindromo.
 * @author rafaelpaiva
 */
public class PalindromoIterativo implements Palindromo{
    private String s;
    boolean caseSensitive;
    
    public PalindromoIterativo () {s = ""; caseSensitive = false;}
    public PalindromoIterativo (boolean sense) {s = ""; caseSensitive = sense;}
    public PalindromoIterativo (String s, boolean sense) {this.s = s; caseSensitive = sense;}
    public PalindromoIterativo (String s) {this.s = s; caseSensitive = false;}
    
    @Override
    public boolean Checar (String s)
    {
	SetString(s);
	return Checar();
    }
    @Override
    public boolean Checar ()
    {
	String S;
	if (caseSensitive)
	    S = s;
	else
	    S = s.toUpperCase();
	int x = 0, y = s.length()-1;
	while (x <= y)
	{
	    if (S.charAt(x) != S.charAt(y))
		return false;
	    x++; y--;
	}
	return true;
    }
    @Override
    public void SetString (String s)
    {
	this.s = s.trim();
    }

    @Override
    public void SetCase(boolean sense) {
	this.caseSensitive = sense;
    }
}
