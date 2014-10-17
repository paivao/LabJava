/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 * Classe para checar se uma string é palíndromo de forma recursiva.
 * Implementa a interface Palindromo.
 * @author rafaelpaiva
 */
public class PalindromoRecursivo implements Palindromo{
    private String s;
    private int x, y;
    private boolean caseSensitive;
    
    public PalindromoRecursivo () {s = ""; caseSensitive = false;}
    public PalindromoRecursivo (boolean sense) {s = ""; caseSensitive = sense;}
    public PalindromoRecursivo (String s, boolean sense) {caseSensitive = sense; SetString(s);}
    public PalindromoRecursivo (String s) {this.s = s; caseSensitive = false;}
    
    @Override
    public boolean Checar (String s)
    {
	SetString(s);
	return Checar();
    }
    @Override
    public boolean Checar ()
    {
	if (x > y)
	{
	    x = 0; y = s.length();
	    return true;
	}
	if (s.charAt(x) != s.charAt(y))
	    return false;
	x++; y--;
	return Checar();
    }
    @Override
    public void SetString (String s)
    {
	this.s = s.trim();
	if (!caseSensitive)
	    this.s = this.s.toUpperCase();
	x = 0;
	y = this.s.length()-1;
    }

    @Override
    public void SetCase(boolean sense) {
	this.caseSensitive = sense;
	if (!sense)
	    s = s.toUpperCase();
    }
}
