/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 *
 * @author rafaelpaiva
 */
public class HuffmanLeaf implements HuffmanNode{
    private final byte[] valor;
    HuffmanLeaf (byte[] valor) {this.valor = valor;}
    @Override
    public HuffmanNode PercorrerDescompactar(Boolean caminho) {
	return this;
    }

    @Override
    public int[] PercorrerCompactar(byte[] valor, int nivel) {
	int[] saida = null;
	if (!Huffman.Compara(valor, this.valor)) return saida;
	saida = new int[2];
	saida[0] = 0;
	saida[1] = nivel;
	return saida;
    }

    @Override
    public byte[] GetValor() {
	return this.valor;
    }
    
    @Override
    public void Imprimir(String caminho) {
	System.out.println(caminho + "\t\t" + valor.toString());
    }

    @Override
    public HuffmanNode GetEsq() {
	return null;
    }

    @Override
    public HuffmanNode GetDir() {
	return null;
    }
    
}
