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
public class Tabuleiro implements Cloneable {

    private int[][] colunas;

    public Tabuleiro() {
        this.colunas = new int[11][];
    }

    public Tabuleiro(int[][] tabuleiro) {
        this.colunas = tabuleiro;
    }

    public void setValorColuna(int numColuna, int[] valores) {

        this.colunas[numColuna] = valores;
    }

    public void setJogada(int coluna, int linha, int codJogador) {
        this.colunas[coluna][linha] = codJogador;
    }

    public int[][] getColunas() {
        return colunas;
    }

    @Override
    public Object clone() {
        int[][] colunasCopy = new int[11][];

        for (int i = 0; i < this.colunas.length; i++) {
            colunasCopy[i] = new int[this.colunas[i].length];
            System.arraycopy(this.colunas[i], 0, colunasCopy[i], 0, this.colunas[i].length);
        }

        return new Tabuleiro(colunasCopy);
    }

}
