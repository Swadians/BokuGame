/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.base.Tupla;
import com.ufpel.bokugame.ia.Busca;
import com.ufpel.bokugame.ia.BuscaProfundidadeParalela;
import com.ufpel.bokugame.ia.HeuristicaBuscaPontos;
import com.ufpel.bokugame.ia.HeuristicaEscolhePosSanduiche;
import com.ufpel.bokugame.util.HttpUtil;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author WeslenSchiavon
 */
public class BokuGame {

    public static boolean Executa(String[] args, List<Float> notas) throws MalformedURLException, IOException, InterruptedException {
        Busca busca = new BuscaProfundidadeParalela();

        HttpUtil httpUtil = new HttpUtil("http://" + args[0] + ":" + args[1]);

        short jogador = Short.parseShort(args[2]);
        short adversario = TabuleiroUtil.trocaJogador(jogador);
        int codJogadorAtual = jogador;
        while (codJogadorAtual != 0) {
            codJogadorAtual = httpUtil.getCodVezJogador();
            if (codJogadorAtual == jogador) {
                System.out.println("Jogando...");

                if (httpUtil.jogadaSanduiche()) {
                    long startTime = System.currentTimeMillis();
                    HeuristicaEscolhePosSanduiche escolheJogada = new HeuristicaEscolhePosSanduiche();
                    Nodo raiz = new Nodo(httpUtil.getTabuleiroAtual());
                    raiz.setJogador(jogador);

                    Tupla melhorJogada = escolheJogada.getMelhorJogada(raiz, httpUtil.getMovimentosPossiveis());
                    httpUtil.movePeca(jogador, melhorJogada.coluna, melhorJogada.linha);

                    long endTime = System.currentTimeMillis();

                    long duration = (endTime - startTime) / 1000;

                    System.out.println("Jogado em: " + duration + "s");
                } else {
                    Nodo raiz = new Nodo(httpUtil.getTabuleiroAtual());
                    raiz.setJogador(adversario);

                    long startTime = System.currentTimeMillis();

                    Nodo resp = busca.Busca(raiz, (short) 3, jogador, new HeuristicaBuscaPontos(), notas);

                    if (!httpUtil.movePeca(jogador, resp.getJogada().coluna, resp.getJogada().linha)) {
                        System.out.println("Jogada invalida!");
                        System.out.println("Jogadando Aleatorio...");

                        Random rd = new Random();
                        List<Tupla> movimentosPossiveis = httpUtil.getMovimentosPossiveis();

                        Tupla jogadaEscolhida = movimentosPossiveis.get(rd.nextInt(movimentosPossiveis.size()));
                        httpUtil.movePeca(jogador, jogadaEscolhida.coluna, jogadaEscolhida.linha);
                    }

                    long endTime = System.currentTimeMillis();

                    long duration = (endTime - startTime) / 1000;

                    System.out.println("Jogado em: " + duration + "s");
                }

            } else {
                Thread.sleep(100);
            }
        }

        return httpUtil.jogadorVenceu();
    }
}
