/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author rafaelpaiva
 */
public class PalindromoBuilder implements Palindromo {
    private StringBuilder s;
    boolean caseSensitive;
    
    public PalindromoBuilder () {s = null; caseSensitive = false;}
    public PalindromoBuilder (boolean sense) {s = null; caseSensitive = sense;}
    public PalindromoBuilder (String s, boolean sense) {this.s = new StringBuilder(s); caseSensitive = sense;}
    public PalindromoBuilder (String s) {this.s = new StringBuilder(s); caseSensitive = false;}

    @Override
    public boolean Checar() {
	StringBuilder s2 = this.s.reverse();
	if (caseSensitive)
	{
	    if (s2.toString().equals(s.toString()))
		return true;
	}
	else
	    if (s2.toString().equalsIgnoreCase(s2.toString()))
		return true;
	return false;
    }

    @Override
    public boolean Checar(String s) {
	SetString(s);
	return Checar();
    }
    
    @Override
    public void SetString(String s) {
	this.s = new StringBuilder(s);
    }

    @Override
    public void SetCase(boolean sense) {
	caseSensitive = sense;
    }
}
