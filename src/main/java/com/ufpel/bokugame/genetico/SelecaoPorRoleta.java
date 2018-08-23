/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import java.util.List;
import java.util.Random;

/**
 *
 * @author WeslenSchiavon
 */
public class SelecaoPorRoleta implements Selecao {

    /**
     * Sorteia um elemento da lista ordenada passada por parametro por roleta
     * viciada.
     *
     * @param cromossomos lista ordenada
     * @return elemento sorteado
     */
    @Override
    public Cromossomo aplicaSelecao(List<Cromossomo> cromossomos) {
        Random rd = new Random();
        int valorMaximo = cromossomos.parallelStream().mapToInt(i -> i.getValorHeuristico()).reduce(0, Integer::sum);

        int valorSorteado = rd.nextInt(valorMaximo);

        int valorTotal = 0;
        for (Cromossomo cromossomo : cromossomos) {
            valorTotal += cromossomo.getValorHeuristico();
            if (valorTotal >= valorSorteado) {
                return cromossomo;
            }
        }

        throw new RuntimeException("Nao foi possivel sortear elementos");
    }

}
