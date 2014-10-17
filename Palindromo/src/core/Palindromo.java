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
public interface Palindromo {
    /**
     * Método para checar se é um palíndromo ou não.
     * @return True se é palíndromo, False caso contrário.
     */
    public boolean Checar ();
    /**
     * Método para checar se é um palíndromo ou não.
     * @param s String para checar. Esta sobreescreverá a string armazenada na classe.
     * @return True se é palíndromo, False caso contrário.
     */
    public boolean Checar (String s);
    /**
     * Método para setar a string a ser checada.
     * @param s String armazenada na classe. Os espaços serão removidos.
     */
    public void SetString (String s);
    /**
     * Configura para trabalhar em case sensitive.
     * @param sense True se é case sensitive, False caso contrátrio.
     */
    public void SetCase (boolean sense);
}
