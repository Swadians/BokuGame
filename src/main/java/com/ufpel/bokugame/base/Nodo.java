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
public class Nodo implements Cloneable {

    private Tabuleiro tabuleiro;
    private int valorHeuristico;
    public Nodo pai;

    public Nodo() {
        this.tabuleiro = new Tabuleiro();
    }

    public Nodo(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public int[][] getArrayTabuleiro() {
        return this.tabuleiro.getColunas();
    }

    public void setJogada(int coluna, int linha, int codJogador) {
        tabuleiro.setJogada(coluna, linha, codJogador);
    }

    @Override
    public Object clone() {
        return new Nodo((Tabuleiro) this.tabuleiro.clone());
    }

}
