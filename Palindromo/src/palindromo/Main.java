/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package palindromo;
import core.*;
import java.util.Scanner;
/**
 *
 * @author rafaelpaiva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	Palindromo pal = null;
	Scanner entrada = new Scanner(System.in);
	System.out.println("Digite uma frase: ");
	String s = entrada.nextLine();
	System.out.print("Digite 1 para case sensitive, ou outra coisa para não: ");
	boolean cs = (entrada.nextByte() == (byte)1);
	System.out.print("Digite 1 para usar iterativo, 2 para recursivo, 3 para StringBuilder: ");
	int modo = entrada.nextInt();
	switch(modo)
	{
	    case 1:
		pal = new PalindromoIterativo(s, cs);
		break;
	    case 2:
		pal = new PalindromoRecursivo(s, cs);
		break;
	    case 3:
		pal = new PalindromoRecursivo(s, cs);
		break;
	}
	if (pal.Checar())
	    System.out.println(s + " é palíndromo!");
	else
	    System.out.println(s + " não é palíndromo!");
    }
    
}

