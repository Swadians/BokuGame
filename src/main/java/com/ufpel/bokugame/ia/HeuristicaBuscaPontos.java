/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Jogada;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.base.Tupla;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class HeuristicaBuscaPontos extends Heuristica {

    @Override
    public void calcAndSet(Nodo nodo, List<Jogada> estados) {
        short valorHeuristicoColunas = this.getValorHeuristicoColunas(nodo, estados);
        short valorHeuristicoDiagonalPrincipal = this.getValorHeuristicoDiagonalPrincipal(nodo, estados);
        short valorHeuristicoDiagonalSecundaria = this.getValorHeuristicoDiagonalSecundaria(nodo, estados);

        short valorTotal = (short) (valorHeuristicoColunas
                + valorHeuristicoDiagonalPrincipal
                + valorHeuristicoDiagonalSecundaria);

        nodo.setValorHeuristico(valorTotal);
    }

    public short getValorHeuristicoColunas(Nodo nodo, List<Jogada> estados) {
        short[][] tabuleiro = nodo.getArrayTabuleiro();
        Tupla jogada = nodo.getJogada();

        short countColunaPontos = 0;

        for (Jogada estado : estados) {
            countColunaPontos += aplicaHeuristicas(estado.getJogada(), tabuleiro[jogada.coluna], estado.getNota());
        }

        return countColunaPontos;
    }

    public short getValorHeuristicoDiagonalPrincipal(Nodo nodo, List<Jogada> estados) {
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

        for (Jogada estado : estados) {
            countColunaPontos += aplicaHeuristicas(estado.getJogada(), valores, estado.getNota());
        }

        return countColunaPontos;

    }

    public short getValorHeuristicoDiagonalSecundaria(Nodo nodo, List<Jogada> estados) {
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

        for (Jogada estado : estados) {
            countColunaPontos += aplicaHeuristicas(estado.getJogada(), valores, estado.getNota());
        }

        return countColunaPontos;
    }
}
