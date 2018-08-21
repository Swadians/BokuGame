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
        int ultimaPos = cromossomos.size() - 1;

        int valorSorteado = rd.nextInt(cromossomos.get(ultimaPos).getValorHeuristico() + 1);

        for (Cromossomo cromossomo : cromossomos) {
            if (cromossomo.getValorHeuristico() >= valorSorteado) {
                return cromossomo;
            }
        }

        throw new RuntimeException("Nao foi possivel sortear elementos");
    }

}
