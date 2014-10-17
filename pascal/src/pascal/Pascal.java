/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pascal;

import java.util.Scanner;

/**
 *
 * @author rafaelpaiva
 */
public class Pascal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int tamanho;
        System.out.print("Digite o tamanho do triangulo de pascal: ");
        Scanner entrada = new Scanner(System.in);
        tamanho = entrada.nextInt();
        new TrianguloPascal(tamanho).ImprimirTriangulo();
    }
    
}
