/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe auxiliar que constroi a árvore de huffmann.
 * @author rafaelpaiva
 */
public class HuffmanBuilder {
    /**
     * Subclasse responsável para associar a um nó de huffmann a sua respectiva freqência.
     */
    private class HBC implements Comparable<HBC> {
	/**
	 * Frequência associada.
	 */
	public long freq;
	/**
	 * Nó de huffmann.
	 */
	public HuffmanNode no;
	/**
	 * Construtor default, sem nó e freqência zero.
	 */
	HBC () {this.freq = 0; this.no = null;}
	/**
	 * Construtor que cria a estrutura.
	 * @param freq Frequência relativa ao nó de huffmann.
	 * @param no Nó de huffmann.
	 */
	HBC (long freq, HuffmanNode no) {this.freq = freq; this.no = no;}

	@Override
	public int compareTo(HBC o) throws ClassCastException {
	    return (int)(this.freq - o.freq);
	}
    }
    
    private List<HBC> list;
    private int tam, tamEst;
    
    HuffmanBuilder (int tam) {list = null; this.tam = tam;}
    HuffmanBuilder (List<byte[]> dataList, List<Long> freqList, Boolean full) {
	tamEst = tam = 0;
	list = new ArrayList<>();
	for (int i = 0; i < freqList.size(); i++)
	{
	    if (!full && freqList.get(i)==0) continue;
	    HBC elem = new HBC(freqList.get(i), new HuffmanLeaf(dataList.get(i)));
	    list.add(elem);
	    tamEst += dataList.get(i).length;
	}
	Collections.sort(list);
    }
    /**
     * Método principal. Constrói a árvore de huffmann.
     * @return Nó de huffmann referente a raíz da árvore.
     */
    HuffmanNode ConstruirArvoreHuffmann() {
	int bittam = list.size();
	while (list.size() > 1)
	{
	    HBC least1 = list.remove(0), least2 = list.remove(0);
	    list.add(new HBC(least1.freq+least2.freq, new HuffmanInner(least1.no, least2.no)));
	    bittam++;
	}
	tam = tamEst + (int)((bittam-1)/8) + 1;
	System.out.println(list.get(0).freq);
	return list.get(0).no;
    }
    
    byte[] SalvarArvore() {
	if (tam == 0) return null;
	byte[] buf = new byte[tam+4];
	buf[0] = (byte)(tamEst>>>24); buf[1] = (byte)(tamEst>>>16); buf[2] = (byte)(tamEst>>>8); buf[3] = (byte)tamEst;
	int[] pos = new int[2];
	pos[0] = 31; pos[1] = tam-tamEst+4;
	PreencherEstrutura(pos, buf, list.get(0).no);
	return buf;
    }
    
    HuffmanNode LerArvore(byte[] buf) {
	tamEst = (((int)buf[0])<<24) + (((int)buf[1])<<16) + (((int)buf[2])<<8) + ((int)buf[3]);
	int[] pos = new int[2];
	pos[0] = 31; pos[1] = tam-tamEst+4;
	return MontarArvore(pos, buf);
    }
    
    private void PreencherEstrutura(int[] indices, byte[] buf, HuffmanNode no) {
	byte b; byte[] valor;
	indices[0]++;
	if (no.GetEsq() == null && no.GetDir() == null)
	{
	    b = (byte)(buf[indices[0]/8]|(1<<(7-(indices[0]%8))));
	    buf[indices[0]/8] = b;
	    valor = no.GetValor();
	    for (b = 0; b < valor.length; b++, indices[1]++)
		buf[indices[1]] = valor[b];
	    return;
	}
	PreencherEstrutura(indices, buf, no.GetEsq());
	PreencherEstrutura(indices, buf, no.GetDir());
    }
    private HuffmanNode MontarArvore(int[] pos, byte[] buf)
    {
	pos[0]++;
	if (((buf[pos[0]/8])&(1<<(7-(pos[0]%8)))) == 0) {
	    HuffmanNode esq = MontarArvore(pos, buf);
	    HuffmanNode dir = MontarArvore(pos, buf);
	    return new HuffmanInner (esq, dir);
	}
	byte[] data = new byte[Huffman.dataSize];
	for(int i = 0; i < Huffman.dataSize; i++, pos[1]++)
	    data[i] = buf[pos[1]];
	return new HuffmanLeaf(data);
    }
}
