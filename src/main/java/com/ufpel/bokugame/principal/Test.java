/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.ia.Busca;
import com.ufpel.bokugame.ia.BuscaProfundidadeParalela;
import com.ufpel.bokugame.ia.HeuristicaA;
import com.ufpel.bokugame.util.HttpUtil;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author WeslenSchiavon
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException, IOException {
        Busca busca = new BuscaProfundidadeParalela();

        HttpUtil httpUtil = new HttpUtil("http://192.168.0.102:8080");

        for (int i = 0; i < 10; i++) {
            System.out.println("Turno - " + i);
            Nodo raiz = new Nodo(httpUtil.getTabuleiroAtual());

            Nodo resp = busca.Busca(raiz, (short) 5, CodigoTabuleiro.JOGADOR_A, new HeuristicaA());

            httpUtil.movePeca(CodigoTabuleiro.JOGADOR_A, resp.getJogada().coluna, resp.getJogada().linha);

            //------------------------------------------------------------------------------------------------
            raiz = new Nodo(httpUtil.getTabuleiroAtual());

            resp = busca.Busca(raiz, (short) 5, CodigoTabuleiro.JOGADOR_B, new HeuristicaA());

            httpUtil.movePeca(CodigoTabuleiro.JOGADOR_B, resp.getJogada().coluna, resp.getJogada().linha);
        }

    }
}
