/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pascal;

/**
 * @author rafaelpaiva
 * Classe referente ao triangulo de pascal
 */
public class TrianguloPascal {
    private int tamanho, atual;
    private Integer[] nivel;
    /**
     * Construtor default (tamanho 0)
     */
    TrianguloPascal() {this.tamanho = 0;}
    /**
     * 
     * @param t : tamanho do triângulo
     */
    TrianguloPascal(int t) {this.tamanho = t; this.atual = 0; nivel = new Integer[t];}
    private void IncrementarNivel()
    {
        nivel[atual] = 1;
        for (int i = atual-1; i >= (atual+1)/2; i--)
        {
            nivel[i] += nivel[i-1];
        }
        for (int i = 1; i <= (atual+1)/2; i++)
        {
            nivel[i] = nivel[atual-i];
        }
        atual++;
    }
    /**
     * Função que imprime o Triangulo de Pascal
     */
    void ImprimirTriangulo()
    {
        for (int i = 0; i < tamanho; i++)
        {
            IncrementarNivel();
            for (int j = 0; j < atual; j++)
                System.out.print(nivel[j].toString() + ' ');
            System.out.println();
        }
        //ResetarTriangulo(tamanho);
    }
    /**
     * Muda o tamanho do triângulo
     * @param t : novo tamanho do triângulo.
     */
    void ResetarTriangulo(int t)
    {
        atual = 0;
        tamanho = t;
        nivel = null;
        nivel = new Integer[t];
    }
}
