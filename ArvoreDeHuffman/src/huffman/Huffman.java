/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafaelpaiva
 */
public class Huffman {

    private HuffmanNode raiz;
    private DataInputStream in;
    private DataOutputStream out;

    public static final int dataSize = 1;

    public Huffman() {
	raiz = null;
	in = null;
	out = null;
    }
    /*public Huffman (String fonte) throws IOException { raiz = null; in = null; out = null;
     InicializarHuffmann(fonte);}*/

    /**
     * Checa se existe árvore de huffmann.
     *
     * @return True se existe árvore de huffmann, false caso contrário.
     */
    public Boolean ExisteArvore() {
	return raiz != null;
    }

    /**
     * Cria a árvore de huffmann a partir de um arquivo fonte.
     *
     * @param fonte Nome do arquivo base.
     * @throws FileNotFoundException
     * @throws IOException
     */
    /*
     public void InicializarHuffmann(String fonte)
     {
     in = new RandomAccessFile(fonte, "r").getChannel();
	
     //Pegar a tabela de frequência
     int data;
     long[] frequencia = new long[256];
     for (int i = 0; i < 256; i++) frequencia[i] = 0;
     while ((data = in.read()) != -1)
     {
     frequencia[data]++;
     }
	
	
     //Fechar o file stream
     in.close();
	
     //Construir a árvore de huffmann
     raiz = (new HuffmanBuilder(frequencia, false)).ConstruirArvoreHuffmann();
     }*/
    /**
     * Compacta um arquivo.
     *
     * @param origem
     * @param destino
     * @throws java.io.FileNotFoundException
     * @throws IOException Problema na manipulação dos arquivos
     */
    public void Compactar(File origem, File destino) throws FileNotFoundException, IOException, IndexOutOfBoundsException {
	in = new DataInputStream(new FileInputStream(origem));
	out = new DataOutputStream(new FileOutputStream(destino));
	int dataRead;

	//Pegar a tabela de frequência
	List<byte[]> dataList = new ArrayList<>();
	List<Long> freqList = new ArrayList<>();
	byte[] data = new byte[dataSize];
	while ((dataRead = in.read(data)) != -1) {
	    int index;
	    if (dataRead < dataSize) {
		for (; dataRead < dataSize; dataRead++) {
		    data[dataRead] = 0;
		}
	    }
	    for (index = 0; index < dataList.size(); ++index) {
		if (Compara(data, dataList.get(index))) {
		    freqList.set(index, freqList.get(index) + 1L);
		    break;
		}
	    }
	    if (index == dataList.size()) {
		dataList.add(data);
		freqList.add(1L);
	    }
	    data = new byte[dataSize];
	}
	in.close();

	HuffmanBuilder build = new HuffmanBuilder(dataList, freqList, false);

	raiz = build.ConstruirArvoreHuffmann();
	data = build.SalvarArvore();
	
	out.writeInt(data.length);
	out.write(data);
	
	//Primeiramente, escrever o tamanho de bytes que serão compactados, para futura descompacatação
	long tamanho = origem.length(), tamanhoFinal = 12+data.length;
	out.writeLong(tamanho);
	
	System.out.println("Tamanho da arvore: "+data.length);

	//Agora ir compactando
	int[] caminho;
	byte ajuste = 0, passa = 0;
	
	in.close();
	in = new DataInputStream(new FileInputStream(origem));
	data = new byte[dataSize];
	while ((dataRead = in.read(data)) != -1) {
	    for (; dataRead < dataSize; ++dataRead) {
		data[dataRead] = 0;
	    }
	    caminho = raiz.PercorrerCompactar(data, 0);
	    //if (caminho == null) throw new Exception("Byte não existe na árvore de Huffman.");
	    //System.out.println(String.format("%2X",caminho[0])+"\t"+caminho[1]);

	    caminho[0] = (caminho[0] >>> ajuste) | ((int) passa << 24);
	    caminho[1] += ajuste;
	    ajuste = (byte) (caminho[1] / 8);
	    tamanhoFinal += ajuste;
	    caminho[1] %= 8;
	    byte i;
	    if (ajuste >= 4) {
		throw new IndexOutOfBoundsException("Arvore muito grande!");
	    }
	    for (i = 0; i < ajuste; i++) {
		out.write((caminho[0] >>> (24 - 8 * i)));
		//System.out.printf("%02X",buffer[i]);
	    }
	    passa = (byte) (caminho[0] >>> (24 - 8 * i));
	    ajuste = (byte) caminho[1];
	    caminho = null;
	}
	if (ajuste != 0) {
	    out.write(passa);
	}

	System.out.println("Foram compactados: "+tamanho+" bytes em "+tamanhoFinal+" bytes.");
	System.out.println("Uma diferença de: "+(tamanho-tamanhoFinal)+" bytes.");
	
	//E finalmente fechar os file streams
	in.close();
	out.close();
    }

    /**
     * Descompacta um arquivo.
     *
     * @param origem Nome do arquivo compactado.
     * @param destino Nome do arquivo a ser descompactado.
     * @throws IOException Se houver algum erro na manipulação dos arquivos.
     */
    public void Descompactar(File origem, File destino) throws IOException, Exception {
	//String rota = "";
	//Variaveis usadas
	long numBytes;
	byte pos; int elem;
	int tamArvore;

	//Abrir o buffer de leitura e o de escrita
	in = new DataInputStream(new FileInputStream(origem));
	out = new DataOutputStream(new FileOutputStream(destino));

	//Carregar a árvore
	tamArvore = in.readInt();
	
	byte[] bufAux = new byte[tamArvore];
	in.read(bufAux);
	
	System.out.println("Montando árvore... "+tamArvore);

	raiz = (new HuffmanBuilder(tamArvore-4)).LerArvore(bufAux);

	//Ler a quantidade de bytes a descompactar, esse é a primeira informação esperada no arquivo
	numBytes = in.readLong();
	
	HuffmanNode iterador, iterador2;
	System.out.println("Serão descompactados " + numBytes + " bytes.");
	iterador2 = raiz; iterador = null;
	
	while ((elem = in.read()) != -1) {
	    pos = 0;
	    //System.out.print("\n"+String.format("%2X", entrada));
	    while (pos < 8) {
		iterador = iterador2;
		iterador2 = iterador.PercorrerDescompactar(((elem >>> (7 - pos)) & 1) == 1);
		if (iterador2 == iterador) {
		    //System.out.print("\t"+rota+" "+iterador.GetValor());
		    if (numBytes > dataSize) {
			out.write(iterador2.GetValor(), 0, dataSize);
			numBytes -= dataSize;
		    } else {
			out.write(iterador2.GetValor(), 0, (int)numBytes);
			numBytes = 0;
			System.out.println("Acabaram-se os bytes 1");
			break;
		    }
		    iterador2 = raiz;
		    //rota = "";
		} else {
		    pos++;
		    /*if (iterador2 == iterador.GetEsq())
		     rota = rota+"L";
		     else
		     rota = rota+"R";*/
		}
	    }
	    if (numBytes == 0) {
		System.out.println("Acabaram-se os bytes 2");
		break;
	    }

	}
	//Fechar os file streams
	in.close();
	out.close();
    }

    /**
     * Imprime a árvore de Huffman.
     */
    public void Imprimir() {
	raiz.Imprimir("");
    }
    
    static Boolean Compara(byte[] esq, byte[] dir)
    {
	int i = esq.length, j;
	if (i != dir.length) return false;
	for (j = 0; j < i; j++)
	    if (esq[j] != dir[j]) return false;
	return true;
    }
}
