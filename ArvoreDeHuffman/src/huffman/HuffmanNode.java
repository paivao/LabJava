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
public interface HuffmanNode {
    /**
     * Faz uma varredura na arvore de Huffmann procurando o caracter correspondente
     * a sequência de bits que representam a sua forma compacta.
     * @param caminho Boolean que diz qual em qual filho será encontrado o caracter correspondente.
     * @param it Referencia ao nó filho que é retornado
     * @return O Byte correspondente.
     */
    HuffmanNode PercorrerDescompactar (Boolean caminho);
    
    /**
     * Faz uma varredura na árvore procurando a sequência binária correspondente ao valor procurado.
     * @param valor Valor que se deseja compactar.
     * @param nivel O nivel do no na árvore.
     * @return A seqência binária (o bit compactado).
     */
    int[] PercorrerCompactar (byte[] valor, int nivel);
    
    /**
     * Pegar o valor, se for uma folha.
     * @return O valor da folha, ou zero se não for folha.
     */
    byte[] GetValor();
    
    void Imprimir(String caminho);
    
    HuffmanNode GetEsq();
    HuffmanNode GetDir();
}
