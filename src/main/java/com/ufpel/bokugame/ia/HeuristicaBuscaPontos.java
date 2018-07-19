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
public class HeuristicaBuscaPontos extends Heuristica {

    @Override
    public void calcAndSet(Nodo nodo) {
        short valorHeuristicoColunas = this.getValorHeuristicoColunas(nodo);
        short valorHeuristicoDiagonalPrincipal = this.getValorHeuristicoDiagonalPrincipal(nodo);
        short valorHeuristicoDiagonalSecundaria = this.getValorHeuristicoDiagonalSecundaria(nodo);

        short valorTotal = (short) (valorHeuristicoColunas
                + valorHeuristicoDiagonalPrincipal
                + valorHeuristicoDiagonalSecundaria);

        nodo.setValorHeuristico(valorTotal);
    }

    public short getValorHeuristicoColunas(Nodo nodo) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada();

        short countColunaPontos = 0;

        List<short[]> estadosNormais = TabPreEstadosUtil.getEstadosNormais(nodo.getJogador());
        List<short[]> estadosRegulares = TabPreEstadosUtil.getEstadosRegulares(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos += aplicaHeuristicas(estadosNormais, tabuleiro[jogada.coluna], this.pesoEstadoNormal);
        countColunaPontos += aplicaHeuristicas(estadosRegulares, tabuleiro[jogada.coluna], this.pesoEstadoRegular);
        countColunaPontos += aplicaHeuristicas(estadosBons, tabuleiro[jogada.coluna], this.pesoEstadoBom);
        countColunaPontos += aplicaHeuristicas(estadosOtimos, tabuleiro[jogada.coluna], this.pesoEstadoOtimo);
        countColunaPontos += aplicaHeuristicas(estadosVitoria, tabuleiro[jogada.coluna], this.pesoEstadoVitoria);

        return countColunaPontos;
    }

    public short getValorHeuristicoDiagonalPrincipal(Nodo nodo) {
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

        List<short[]> estadosNormais = TabPreEstadosUtil.getEstadosNormais(nodo.getJogador());
        List<short[]> estadosRegulares = TabPreEstadosUtil.getEstadosRegulares(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos += aplicaHeuristicas(estadosNormais, valores, this.pesoEstadoNormal);
        countColunaPontos += aplicaHeuristicas(estadosRegulares, valores, this.pesoEstadoRegular);
        countColunaPontos += aplicaHeuristicas(estadosBons, valores, this.pesoEstadoBom);
        countColunaPontos += aplicaHeuristicas(estadosOtimos, valores, this.pesoEstadoOtimo);
        countColunaPontos += aplicaHeuristicas(estadosVitoria, valores, this.pesoEstadoVitoria);

        return countColunaPontos;
    }

    public short getValorHeuristicoDiagonalSecundaria(Nodo nodo) {
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

        List<short[]> estadosNormais = TabPreEstadosUtil.getEstadosNormais(nodo.getJogador());
        List<short[]> estadosRegulares = TabPreEstadosUtil.getEstadosRegulares(nodo.getJogador());
        List<short[]> estadosBons = TabPreEstadosUtil.getEstadosBons(nodo.getJogador());
        List<short[]> estadosOtimos = TabPreEstadosUtil.getEstadosOtimos(nodo.getJogador());
        List<short[]> estadosVitoria = TabPreEstadosUtil.getEstadosVitoria(nodo.getJogador());

        countColunaPontos += aplicaHeuristicas(estadosNormais, valores, this.pesoEstadoNormal);
        countColunaPontos += aplicaHeuristicas(estadosRegulares, valores, this.pesoEstadoRegular);
        countColunaPontos += aplicaHeuristicas(estadosBons, valores, this.pesoEstadoBom);
        countColunaPontos += aplicaHeuristicas(estadosOtimos, valores, this.pesoEstadoOtimo);
        countColunaPontos += aplicaHeuristicas(estadosVitoria, valores, this.pesoEstadoVitoria);

        return countColunaPontos;
    }
}
