/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.util;

import com.ufpel.bokugame.base.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class TabuleiroUtil {

    public static List<Nodo> getJogadas(Nodo pai, int codJogador) {
        List<Nodo> nodos = new ArrayList<>();

        int[][] arrayTabuleiro = pai.getArrayTabuleiro();
        for (int i = 0; i < arrayTabuleiro.length; i++) {
            for (int j = 0; j < arrayTabuleiro[i].length; j++) {
                int valorAtual = pai.getArrayTabuleiro()[i][j];
                if (valorAtual == 0) {
                    Nodo novo = (Nodo) pai.clone();
                    novo.pai = pai;

                    novo.setJogada(i, j, codJogador);

                    nodos.add(novo);
                }
            }
        }
        return nodos;
    }

}
