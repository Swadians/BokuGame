/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.principal.BokuGame;
import com.ufpel.bokugame.util.HttpUtil;
import com.ufpel.bokugame.util.TabPreEstadosUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Weslen
 */
public class AlgoritimoGeneticoManager {

    public int executaJogo(String[] args, List<Float> notas) {
        try {
            HttpUtil http = new HttpUtil("http://" + args[0] + ":" + args[1]);

            boolean venceu = BokuGame.Executa(args, notas);

            short numMovimentos = http.getNumMovimentos();

            if (venceu) {
                return 1000 - numMovimentos;
            } else {
                return numMovimentos;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro: " + e);
            return 0;
        }

    }

    // int nota = random.nextInt(1000) * (random.nextBoolean() ? -1 : 1);
    public List<Cromossomo> geraCromossomos(int tamPopulacao, int notaMaxima) {
        List<Cromossomo> cromossomos = new ArrayList<>();
        Random random = new Random();

        int qtdJogadas = TabPreEstadosUtil.getEstadosPossiveos(CodigoTabuleiro.JOGADOR_A).size();

        for (int j = 0; j < tamPopulacao; j++) {
            List<Float> notas = new ArrayList<>();
            for (int i = 0; i < qtdJogadas; i++) {
                float nota = (float) random.nextInt(notaMaxima);

                notas.add(nota);
            }
            cromossomos.add(new Cromossomo(notas));
        }

        return cromossomos;
    }

}
