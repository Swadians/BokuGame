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
 * @author WeslenSchiavon
 */
public class HeuristicaIdentificaJogadasPrioritarias extends Heuristica {

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
        short somador = 0;
        Tupla jogada = nodo.getJogada();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());
        List<short[]> derrotasPrioritarias = TabPreEstadosUtil.getEstadoCancelaDerrotasPrioritarios(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        for (short[] derrota : derrotas) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                somador += this.pesoCancelDerrota;
            }
        }
        for (short[] derrota : derrotasPrioritarias) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                somador += this.pesoCancelDerrotaPrioritarias;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, tabuleiro[jogada.coluna], this.pesoEstadoSanduicheOtimo, nodo.getJogada().linha);
        somador += aplicaHeuristicas(estadosVitoria, tabuleiro[jogada.coluna], this.pesoEstadoVitoria);

        return somador;
    }

    public short verificaInpedimentoDerrotaDiagonalPrincipal(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        short somador = 0;
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalPrincipal();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());
        List<short[]> derrotasPrioritaria = TabPreEstadosUtil.getEstadoCancelaDerrotasPrioritarios(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

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
                somador += this.pesoCancelDerrota;
            }
        }
        for (short[] derrota : derrotasPrioritaria) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCancelDerrotaPrioritarias;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, sequencia, this.pesoEstadoSanduicheOtimo, pos);
        somador += aplicaHeuristicas(estadosVitoria, sequencia, this.pesoEstadoVitoria);

        return somador;
    }

    public short verificaInpedimentoDerrotaDiagonalSecundaria(Nodo nodo) {
        short somador = 0;
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalSecundaria();

        List<short[]> derrotas = TabPreEstadosUtil.getEstadoCancelaDerrotas(nodo.getJogador());
        List<short[]> derrotasPrioritarias = TabPreEstadosUtil.getEstadoCancelaDerrotasPrioritarios(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

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
                somador += this.pesoCancelDerrota;
            }
        }
        for (short[] derrota : derrotasPrioritarias) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCancelDerrotaPrioritarias;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, sequencia, this.pesoEstadoSanduicheOtimo, pos);
        somador += aplicaHeuristicas(estadosVitoria, sequencia, this.pesoEstadoVitoria);

        return somador;
    }

}
