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
        short verificaJogadaPrioritariaColuna = this.verificaJogadaPrioritariaColuna(nodo);
        short verificaJogadaPrioritariaDiagonalPrincipal = this.verificaJogadaPrioritariaDiagonalPrincipal(nodo);
        short verificaJogadaPrioritariaSecundaria = this.verificaJogadaPrioritariaDiagonalSecundaria(nodo);

        short valorTotal = (short) (verificaJogadaPrioritariaColuna
                + verificaJogadaPrioritariaSecundaria
                + verificaJogadaPrioritariaDiagonalPrincipal);

        nodo.setValorHeuristico(valorTotal);

    }

    public short verificaJogadaPrioritariaColuna(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        short somador = 0;
        Tupla jogada = nodo.getJogada();

        List<short[]> derrotasBaixaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasBaixaPrioridade(nodo.getJogador());
        List<short[]> derrotasMediaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasMediaPrioridade(nodo.getJogador());
        List<short[]> derrotasAltaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasAltaPrioridade(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        for (short[] derrota : derrotasBaixaPrioridade) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                somador += this.pesoCDBaixaPrioridade;
            }
        }

        for (short[] derrota : derrotasMediaPrioridade) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                somador += this.pesoCDMediaPrioridade;
            }
        }

        for (short[] derrota : derrotasAltaPrioridade) {
            if (TabPreEstadosUtil.contains(tabuleiro[jogada.coluna], derrota, nodo.getJogada().linha)) {
                somador += this.pesoCDAltaPrioridade;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, tabuleiro[jogada.coluna], this.pesoEstadoSanduicheOtimo, nodo.getJogada().linha);
        somador += aplicaHeuristicas(estadosBons, tabuleiro[jogada.coluna], this.pesoEstadoBom);
        somador += aplicaHeuristicas(estadosOtimos, tabuleiro[jogada.coluna], this.pesoEstadoOtimo);
        somador += aplicaHeuristicas(estadosVitoria, tabuleiro[jogada.coluna], this.pesoEstadoVitoria);

        return somador;
    }

    public short verificaJogadaPrioritariaDiagonalPrincipal(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        short somador = 0;
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalPrincipal();

        List<short[]> derrotasBaixaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasBaixaPrioridade(nodo.getJogador());
        List<short[]> derrotasMediaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasMediaPrioridade(nodo.getJogador());
        List<short[]> derrotasAltaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasAltaPrioridade(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
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

        for (short[] derrota : derrotasBaixaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDBaixaPrioridade;
            }
        }

        for (short[] derrota : derrotasMediaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDMediaPrioridade;
            }
        }
        for (short[] derrota : derrotasAltaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDAltaPrioridade;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, sequencia, this.pesoEstadoSanduicheOtimo, pos);
        somador += aplicaHeuristicas(estadosBons, sequencia, this.pesoEstadoBom);
        somador += aplicaHeuristicas(estadosOtimos, sequencia, this.pesoEstadoOtimo);
        somador += aplicaHeuristicas(estadosVitoria, sequencia, this.pesoEstadoVitoria);

        return somador;
    }

    public short verificaJogadaPrioritariaDiagonalSecundaria(Nodo nodo) {
        short somador = 0;
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada().getPosInicialDiagonalSecundaria();

        List<short[]> derrotasBaixaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasBaixaPrioridade(nodo.getJogador());
        List<short[]> derrotasMediaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasMediaPrioridade(nodo.getJogador());
        List<short[]> derrotasAltaPrioridade = TabPreEstadosUtil.getEstadoCancelaDerrotasAltaPrioridade(nodo.getJogador());
        List<short[]> estadosSanduiches = TabPreEstadosUtil.getEstadosSanduiches(nodo.getJogador());
        List<short[]> estadosSanduichesOtimos = TabPreEstadosUtil.getEstadosSanduichesOtimos(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
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

        for (short[] derrota : derrotasBaixaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDBaixaPrioridade;
            }
        }

        for (short[] derrota : derrotasMediaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDMediaPrioridade;
            }
        }
        for (short[] derrota : derrotasAltaPrioridade) {
            if (TabPreEstadosUtil.contains(sequencia, derrota, pos)) {
                somador += this.pesoCDAltaPrioridade;
            }
        }

        somador += aplicaHeuristicasSanduiche(estadosSanduiches, estadosSanduichesOtimos, sequencia, this.pesoEstadoSanduicheOtimo, pos);
        somador += aplicaHeuristicas(estadosBons, sequencia, this.pesoEstadoBom);
        somador += aplicaHeuristicas(estadosOtimos, sequencia, this.pesoEstadoOtimo);
        somador += aplicaHeuristicas(estadosVitoria, sequencia, this.pesoEstadoVitoria);

        return somador;
    }

}
