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
import com.ufpel.bokugame.genetico.SelecaoPorRoleta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Weslen
 */
public class AlgoritimoGeneticoMain {

    // 10x dimenção do problema
    public static void main(String[] args) throws FileNotFoundException {
        int tamPopulacao = 100;
        int maiorNotaPossivel = 1000;

        PrintStream ps = new PrintStream(new File("Log.txt"));
        String[] argumentos = {args[0], args[1], args[2]};
        AlgoritimoGeneticoManager agManager = new AlgoritimoGeneticoManager();

        List<Cromossomo> populacao = agManager.geraCromossomos(tamPopulacao, maiorNotaPossivel);

        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("Populacao: " + i);
                List<Cromossomo> filhos = agManager.fazCruzamento(populacao, new CruzamentoPorMedia());
                filhos = agManager.aplicaMutacao(filhos, new MutacaoSomaAleatoria(0.1f, 10));

                populacao.addAll(filhos);

                populacao.forEach(cromossomo -> {
                    int valorHeuristico = agManager.executaJogo(argumentos, cromossomo.getNotas());

                    cromossomo.setValorHeuristico(valorHeuristico);
                });

                populacao = agManager.aplicaSelecao(populacao, new SelecaoPorRoleta(), tamPopulacao, 5);

                ps.println("Populacao: " + i);
                int tamNovaPopulacao = populacao.size() - 1;

                Collections.sort(populacao);
                for (int j = tamNovaPopulacao; j > tamNovaPopulacao - 10; j--) {
                    ps.println("            " + populacao.get(j).getValorHeuristico());
                }

                ps.println("#######################################################");
                ps.println();

                populacao.addAll(agManager.geraCromossomos(10, maiorNotaPossivel)); // adiciona um numero x de cromossomos a cada geração para manter a divercidade genetica
            } catch (Exception e) {
                ps.println();

                ps.println("erro: " + e);

                ps.println();
            }
        }

        System.out.println("Melhor valor:" + populacao.get(0));

    }
}
