/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.base.Tupla;
import com.ufpel.bokugame.ia.Busca;
import com.ufpel.bokugame.ia.BuscaProfundidadeParalela;
import com.ufpel.bokugame.ia.HeuristicaC;
import com.ufpel.bokugame.util.HttpUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
        Busca busca = new BuscaProfundidadeParalela();

        HttpUtil httpUtil = new HttpUtil("http://192.168.0.106:8080");

        int codJogadorAtual = CodigoTabuleiro.JOGADOR_A;
        while (codJogadorAtual != 0) {
            codJogadorAtual = httpUtil.getCodVezJogador();
            if (codJogadorAtual == CodigoTabuleiro.JOGADOR_A) {
                System.out.println("Jogando...");

                if (httpUtil.jogadaSanduiche()) {
                    long startTime = System.currentTimeMillis();

                    List<Tupla> movimentosPossiveis = httpUtil.getMovimentosPossiveis();
                    httpUtil.movePeca(CodigoTabuleiro.JOGADOR_A, movimentosPossiveis.get(0).coluna, movimentosPossiveis.get(0).linha);

                    long endTime = System.currentTimeMillis();

                    long duration = (endTime - startTime) / 1000;

                    System.out.println("Jogado em: " + duration + "s");
                } else {
                    Nodo raiz = new Nodo(httpUtil.getTabuleiroAtual());
                    raiz.setJogador(CodigoTabuleiro.JOGADOR_B);

                    long startTime = System.currentTimeMillis();

                    Nodo resp = busca.Busca(raiz, (short) 3, CodigoTabuleiro.JOGADOR_A, new HeuristicaC());

                    httpUtil.movePeca(CodigoTabuleiro.JOGADOR_A, resp.getJogada().coluna, resp.getJogada().linha);

                    long endTime = System.currentTimeMillis();

                    long duration = (endTime - startTime) / 1000;

                    System.out.println("Jogado em: " + duration + "s");
                }

            } else {
                Thread.sleep(1000);
            }
        }

    }
}
