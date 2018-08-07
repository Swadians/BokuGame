/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.genetico.AlgoritimoGeneticoManager;
import com.ufpel.bokugame.genetico.Cromossomo;
import com.ufpel.bokugame.genetico.CruzamentoPorMedia;
import com.ufpel.bokugame.genetico.MutacaoSomaAleatoria;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Weslen
 */
public class AlgoritimoGeneticoMain {

    public static void main(String[] args) {

        String[] argumentos = {"127.0.0.1", "8080", "1"};
        AlgoritimoGeneticoManager agManager = new AlgoritimoGeneticoManager();

        List<Cromossomo> populacao = agManager.geraCromossomos(10, 1000);

        for (int i = 0; i < 10; i++) {
            List<Cromossomo> filhos = agManager.fazCruzamento(populacao, new CruzamentoPorMedia());
            filhos = agManager.aplicaMutacao(filhos, new MutacaoSomaAleatoria(0.1f, 10));

            populacao.addAll(filhos);
            for (Cromossomo cromossomo : populacao) {
                int valorHeuristico = agManager.executaJogo(argumentos, cromossomo.getNotas());

                cromossomo.setValorHeuristico(valorHeuristico);
            }

            Collections.sort(populacao);

            populacao = populacao.subList(0, 10);
        }

        System.out.println("Melhor valor:" + populacao.get(0));

    }
}
