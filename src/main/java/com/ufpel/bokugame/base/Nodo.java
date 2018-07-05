/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.base;

/**
 *
 * @author WeslenSchiavon
 */
public class Nodo implements Cloneable, Comparable<Nodo> {

    private Tabuleiro tabuleiro;
    private short valorHeuristico;
    private short Jogador;
    private boolean estadoFinal;
    private Tupla jogada;
    private boolean visitado;
    public Nodo pai;
    public Nodo irmao;

    public Nodo() {
        this.tabuleiro = new Tabuleiro();
        this.jogada = new Tupla((short) -1, (short) -1);
        this.valorHeuristico = -1;
    }

    public Nodo(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.jogada = new Tupla((short) -1, (short) -1);
        this.valorHeuristico = -1;
    }

    public Nodo(Tabuleiro tabuleiro, Tupla jogada) {
        this.tabuleiro = tabuleiro;
        this.jogada = jogada;
        this.valorHeuristico = -1;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public short getValorHeuristico() {
        return valorHeuristico;
    }

    public void setValorHeuristico(short valorHeuristico) {
        this.valorHeuristico = valorHeuristico;
    }

    public short[][] getArrayTabuleiro() {
        return this.tabuleiro.getColunas();
    }

    public void setJogada(short coluna, short linha, short codJogador) {
        this.jogada.coluna = coluna;
        this.jogada.linha = linha;

        tabuleiro.setJogada(coluna, linha, codJogador);
    }

    public short getJogador() {
        return Jogador;
    }

    public void setJogador(short Jogador) {
        this.Jogador = Jogador;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal() {
        this.estadoFinal = true;
    }

    public Tupla getJogada() {
        return jogada;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado() {
        this.visitado = true;
    }

    @Override
    public Object clone() {

        Nodo nodo = new Nodo((Tabuleiro) tabuleiro.clone(), (Tupla) jogada.clone());
        nodo.setJogador(Jogador);
        nodo.setValorHeuristico(valorHeuristico);

        return nodo;
    }

    @Override
    public int compareTo(Nodo other) {
        if (this.valorHeuristico < other.valorHeuristico) {
            return 1;
        } else if (this.valorHeuristico > other.valorHeuristico) {
            return -1;
        }
        return 0;
    }

}
