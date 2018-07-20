/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.base.Tupla;
import com.ufpel.bokugame.util.TabPreEstadosUtil;
import java.util.List;

/**
 *
 * @author Weslen
 */
public class HeuristicaEscolhePosSanduiche {

    public Tupla getMelhorJogada(Nodo nodo, List<Tupla> jogadasPossiveis) {
        Tupla SanduicheColuna = this.verificaSanduicheColuna(nodo, jogadasPossiveis);

        if (SanduicheColuna != null) {
            return SanduicheColuna;
        }

        Tupla SanduicheDiagonalPrincipal = this.verificaSanduicheDiagonalPrincipal(nodo, jogadasPossiveis);

        if (SanduicheDiagonalPrincipal != null) {
            return SanduicheDiagonalPrincipal;
        }
        Tupla SanduicheDiagonalSecundaria = this.verificaSanduicheDiagonalSecundaria(nodo, jogadasPossiveis);

        if (SanduicheDiagonalSecundaria != null) {
            return SanduicheDiagonalSecundaria;
        }

        return jogadasPossiveis.get(0);
    }

    public Tupla verificaSanduicheColuna(Nodo nodo, List<Tupla> jogadasPossiveis) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();

        List<short[]> sanduiches = TabPreEstadosUtil.getEstadosSanduichesParaEscolha(nodo.getJogador());

        for (Tupla jogada : jogadasPossiveis) {
            for (short[] sanduiche : sanduiches) {
                if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], sanduiche, jogada.linha)) {
                    return jogada;
                }
            }
        }

        return null;
    }

    public Tupla verificaSanduicheDiagonalPrincipal(Nodo nodo, List<Tupla> jogadasPossiveis) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();

        List<short[]> sanduiches = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());

        short[] sequencia = new short[11];
        for (Tupla jogada : jogadasPossiveis) {
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

            for (short[] sanduiche : sanduiches) {
                if (TabPreEstadosUtil.contains(sequencia, sanduiche, pos)) {
                    return jogada;
                }
            }
        }

        return null;
    }

    public Tupla verificaSanduicheDiagonalSecundaria(Nodo nodo, List<Tupla> jogadasPossiveis) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();

        List<short[]> sanduiches = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());

        short[] sequencia = new short[11];
        for (Tupla jogada : jogadasPossiveis) {
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

            for (short[] sanduiche : sanduiches) {
                if (TabPreEstadosUtil.contains(sequencia, sanduiche, pos)) {
                    return jogada;
                }
            }
        }

        return null;
    }

}
