/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabuleiroUtil;

/**
 *
 * @author WeslenSchiavon
 */
public abstract class Heuristica {

    protected final short pesoVitoria = 100;

    public abstract void calcAndSet(Nodo nodo);

    public void verificaESetaEstadoFinal(Nodo nodo) {
        short valorHeuristico = 0;
        short[][] colunas = nodo.getArrayTabuleiro();

        for (short[] coluna : colunas) {
            short countPontosJogador_A = 0;
            short countPontosJogador_B = 0;
            for (int j = 0; j < coluna.length; j++) {
                if (CodigoTabuleiro.JOGADOR_A == coluna[j]) {
                    countPontosJogador_A++;
                } else if (CodigoTabuleiro.JOGADOR_B == coluna[j]) {
                    countPontosJogador_B++;
                }
            }

            if (countPontosJogador_A == 5) {
                nodo.setEstadoFinal();
                valorHeuristico = (short) (this.pesoVitoria / TabuleiroUtil.Profundidade(nodo));
            } else if (countPontosJogador_B == 5) {
                nodo.setEstadoFinal();
                valorHeuristico = (short) (this.pesoVitoria / TabuleiroUtil.Profundidade(nodo));
            }
        }
    }
}
