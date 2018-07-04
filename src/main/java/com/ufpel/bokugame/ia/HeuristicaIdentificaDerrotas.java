/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.base.Tupla;
import com.ufpel.bokugame.util.TabPreEstadosUtil;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class HeuristicaIdentificaDerrotas extends Heuristica {

    @Override
    public void calcAndSet(Nodo nodo) {
        short verificaInpedimentoDerrotaColuna = this.verificaInpedimentoDerrotaColuna(nodo);
        short verificaInpedimentoDerrotaDiagonalPrincipal = this.verificaInpedimentoDerrotaDiagonalPrincipal(nodo);
        short verificaInpedimentoDerrotaDiagonalSecundaria = this.verificaInpedimentoDerrotaDiagonalSecundaria(nodo);

        short valorTotal = (short) (verificaInpedimentoDerrotaColuna
                + verificaInpedimentoDerrotaDiagonalSecundaria
                + verificaInpedimentoDerrotaDiagonalPrincipal);

        nodo.setValorHeuristico(valorTotal);

    }

    public short verificaInpedimentoDerrotaColuna(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());

        for (short[] derrota : derrotas) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                return this.pesoCancelDerrota;
            }
        }

        return 0;
    }

    public short verificaInpedimentoDerrotaDiagonalPrincipal(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalPrincipal();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());

        short[] sequencia = new short[11];
        short contador = 0;
        short pos = 0;
        do {
            sequencia[contador] = tabuleiro[jogada.coluna][jogada.linha];

            if (nodo.getJogada().equals(jogada)) {
                pos = contador;
            }

            contador++;
            jogada = jogada.proximaPosDiagonalPrincipal();
        } while (jogada != null);

        for (short[] derrota : derrotas) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                return (short) (this.pesoCancelDerrota / TabuleiroUtil.Profundidade(nodo));
            }
        }

        return 0;
    }

    public short verificaInpedimentoDerrotaDiagonalSecundaria(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalSecundaria();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());

        short[] sequencia = new short[11];
        short contador = 0;
        short pos = 0;
        do {
            sequencia[contador] = tabuleiro[jogada.coluna][jogada.linha];

            if (nodo.getJogada().equals(jogada)) {
                pos = contador;
            }

            contador++;
            jogada = jogada.proximaPosDiagonalSecundaria();
        } while (jogada != null);

        for (short[] derrota : derrotas) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                return (short) (this.pesoCancelDerrota / TabuleiroUtil.Profundidade(nodo));
            }
        }

        return 0;
    }

}
