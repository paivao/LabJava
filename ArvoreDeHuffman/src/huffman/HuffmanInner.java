/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 * Nó interno de huffmann. Este tem dois nós filhos, o esquerdo e o direito.
 * @author rafaelpaiva
 */
public class HuffmanInner implements HuffmanNode {
    HuffmanNode esq;
    HuffmanNode dir;
    
    HuffmanInner() {this.esq = null; this.dir = null;}
    HuffmanInner(HuffmanNode esq, HuffmanNode dir) {this.esq = esq; this.dir = dir;}
    
    @Override
    public HuffmanNode GetEsq ()
    {
        return this.esq;
    }
    @Override
    public HuffmanNode GetDir ()
    {
        return this.dir;
    }
    
    @Override
    public HuffmanNode PercorrerDescompactar(Boolean caminho) {
	if (caminho) return this.dir;
	return this.esq;
    }

    @Override
    public int[] PercorrerCompactar(byte[] valor, int nivel) {
	int[] Valor;
	Valor = this.esq.PercorrerCompactar(valor, nivel+1);
	if (Valor != null)
	{
	    return Valor;
	}
	Valor = this.dir.PercorrerCompactar(valor, nivel+1);
	if (Valor != null)
	{
	    Valor[0] |= 1<<(31-nivel);
	    return Valor;
	}
	return Valor;
    }

    @Override
    public byte[] GetValor() {
	return null;
    }
    
    @Override
    public void Imprimir(String caminho) {
	esq.Imprimir(caminho+'L');
	dir.Imprimir(caminho+'R');
    }
}
