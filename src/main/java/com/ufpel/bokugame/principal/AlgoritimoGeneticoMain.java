/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.genetico.AlgoritimoGeneticoManager;
import com.ufpel.bokugame.genetico.Cromossomo;
import java.util.List;

/**
 *
 * @author Weslen
 */
public class AlgoritimoGeneticoMain {

    public static void main(String[] args) {

        String[] argumentos = {"127.0.0.1", "8080", "1"};
        AlgoritimoGeneticoManager agManager = new AlgoritimoGeneticoManager();

        List<Cromossomo> cromossomos = agManager.geraCromossomos(10, 1000);

        int valorHeuristico = agManager.executaJogo(argumentos, cromossomos.get(0).getNotas());

        System.out.println("Valor heurstico: " + valorHeuristico);

    }
}
