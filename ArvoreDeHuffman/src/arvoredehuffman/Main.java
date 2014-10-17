/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvoredehuffman;
import huffman.Huffman;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	Huffman huff = new Huffman();
	byte op;
	Scanner in = new Scanner(System.in);
	String fonte, origem, destino;
	
	System.out.println("\t\tÁrvore de Huffmann\n");
	do {
	    System.out.print("Digite a opção\n(1 cria, 2 compacta, 3 descompacta, 0 sai)\nEscolha: ");
	    op = in.nextByte();
	    try {
		switch(op) {
		    case 1:
			System.out.print("Digite o nome do arquivo para montar a arvore: ");
		        fonte = in.next();
		        //huff.InicializarHuffmann(fonte);
			//huff.Imprimir();
		    break;
		    case 2:
			if (!huff.ExisteArvore()) System.out.println("Não existe árvore de huffmann!");
		        System.out.print("Digite o nome do arquivo a ser compactado: ");
			origem = in.next();
		        System.out.print("Digite o nome do arquivo compactado: ");
			destino = in.next();
		        huff.Compactar(new File(origem), new File(destino));
		    break;
		    case 3:
			if (!huff.ExisteArvore()) System.out.println("Não existe árvore de huffmann!");
			System.out.print("Digite o nome do arquivo compactado: ");
		        origem = in.next();
			System.out.print("Digite o nome do arquivo a ser descompactado: ");
		        destino = in.next();
			huff.Descompactar(new File(origem), new File(destino));
		    break;
		}
	    } 
	    catch (FileNotFoundException fnf) {
		System.out.println(fnf.toString());
	    }
	    catch (IOException ioe) {
		System.out.println(ioe.toString());
	    }
	    catch (Exception e) {
		System.out.println("Aiaiaiaiai\n"+e.toString());
	    }
	} while (op != 0);
	System.out.println("BYE!");
    }
    
}
