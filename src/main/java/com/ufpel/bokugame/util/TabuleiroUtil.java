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

    public static List<Nodo> geraJogadas(Nodo pai, short codJogador) {
        List<Nodo> nodos = new ArrayList<>();

        short[][] arrayTabuleiro = pai.getArrayTabuleiro();
        for (short i = 0; i < arrayTabuleiro.length; i++) {
            for (short j = 0; j < arrayTabuleiro[i].length; j++) {
                int valorAtual = pai.getArrayTabuleiro()[i][j];
                if (valorAtual == 0) {
                    Nodo novo = (Nodo) pai.clone();
                    novo.pai = pai;
                    novo.setJogador(codJogador);
                    novo.setJogada(i, j, codJogador);

                    nodos.add(novo);
                }
            }
        }
        return nodos;
    }

    public static Nodo getPrimeiraJogada(Nodo filho) {
        short count = 0;
        Nodo anterior = filho;
        while (filho.pai != null) {
            anterior = filho;
            filho = filho.pai;
            count++;
        }

        return anterior;
    }

    public static int Profundidade(Nodo base) {
        short count = 0;
        while (base != null) {
            base = base.pai;
            count++;
        }
        return count;
    }

}
