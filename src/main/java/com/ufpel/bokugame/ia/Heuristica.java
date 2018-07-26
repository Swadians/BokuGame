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
public abstract class Heuristica {

    protected final short pesoEstadoNormal = 1;
    protected final short pesoEstadoRegular = 5;
    protected final short pesoEstadoBom = 15;
    protected final short pesoEstadoOtimo = 60;
    protected final short pesoEstadoSanduicheOtimo = 50;
    protected final short pesoCD = 20;
    protected final short pesoCDBaixaPrioridade = 1;
    protected final short pesoCDMediaPrioridade = 30;
    protected final short pesoCDAltaPrioridade = 100;
    protected final short pesoEstadoVitoria = Short.MAX_VALUE;

    public abstract void calcAndSet(Nodo nodo);

    public void verificaESetaEstadoFinal(Nodo nodo) {

        short valorHeuristicoColunas = this.verificaEstadoFinalColunas(nodo);
        short valorHeuristicoDiagonalPrincipal = this.verificaEstadoFinalDiagonalPrincipal(nodo);
        short valorHeuristicoDiagonalSecundaria = this.verificaEstadoFinalDiagonalSecundaria(nodo);

        short valorTotal = (short) (valorHeuristicoColunas
                + valorHeuristicoDiagonalPrincipal
                + valorHeuristicoDiagonalSecundaria);

        if (valorTotal > 0) {
            nodo.setValorHeuristico(valorTotal);
        }

    }

    private short verificaEstadoFinalColunas(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada();

        short countColunaPontos = 0;

        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos = aplicaHeuristicas(estadosVitoria, tabuleiro[jogada.coluna], this.pesoEstadoVitoria);

        if (countColunaPontos > 0) {
            nodo.setEstadoFinal();
            countColunaPontos = (short) (this.pesoEstadoVitoria / TabuleiroUtil.Profundidade(nodo));
        }

        return countColunaPontos;
    }

    private short verificaEstadoFinalDiagonalPrincipal(Nodo nodo) {
        short countColunaPontos = 0;
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = (Tupla) nodo.getJogada().getPosInicialDiagonalPrincipal().clone();

        short contador = 0;
        short[] valores = new short[11];

        do {
            valores[contador] = tabuleiro[jogada.coluna][jogada.linha];
            jogada = jogada.proximaPosDiagonalPrincipal();
            contador++;
        } while (jogada != null);

        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos = aplicaHeuristicas(estadosVitoria, valores, this.pesoEstadoVitoria);

        if (countColunaPontos > 0) {
            nodo.setEstadoFinal();
            countColunaPontos = (short) (this.pesoEstadoVitoria / TabuleiroUtil.Profundidade(nodo));
        }

        return countColunaPontos;
    }

    private short verificaEstadoFinalDiagonalSecundaria(Nodo nodo) {
        short countColunaPontos = 0;
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = (Tupla) nodo.getJogada().getPosInicialDiagonalSecundaria().clone();

        short contador = 0;
        short[] valores = new short[11];
        do {
            valores[contador] = tabuleiro[jogada.coluna][jogada.linha];
            jogada = jogada.proximaPosDiagonalSecundaria();
            contador++;
        } while (jogada != null);

        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos = aplicaHeuristicas(estadosVitoria, valores, this.pesoEstadoVitoria);

        if (countColunaPontos > 0) {
            nodo.setEstadoFinal();
            countColunaPontos = (short) (this.pesoEstadoVitoria / TabuleiroUtil.Profundidade(nodo));
        }

        return countColunaPontos;
    }

    protected short aplicaHeuristicas(List<short[]> estados, short[] tabuleiro, short peso) {
        short countColunaPontos = 0;
        for (short[] estado : estados) {
            if (TabPreEstadosUtil.contains(tabuleiro, estado)) {
                countColunaPontos += peso;
                // return peso;
            }
        }
        return countColunaPontos;
    }

    protected short aplicaHeuristicasSanduiche(List<short[]> tiposSauiche, List<short[]> estados, short[] tabuleiro, short peso, short posJogada) {
        for (short[] sanduiche : tiposSauiche) {
            if (TabPreEstadosUtil.contains(tabuleiro, sanduiche, posJogada)) {
                for (short[] estado : estados) {
                    if (TabPreEstadosUtil.contains(tabuleiro, estado, posJogada)) {
                        return peso;
                    }
                }
            }
        }
        return 0;
    }

}
