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

    private short[][] colunas;

    public Tabuleiro() {
        this.colunas = new short[11][];
    }

    public Tabuleiro(short[][] tabuleiro) {
        this.colunas = tabuleiro;
    }

    public void setValorColuna(short numColuna, short[] valores) {

        this.colunas[numColuna] = valores;
    }

    public void setJogada(short coluna, short linha, short codJogador) {
        this.colunas[coluna][linha] = codJogador;
    }

    public short[][] getColunas() {
        return colunas;
    }

    @Override
    public Object clone() {
        short[][] colunasCopy = new short[11][];

        for (short i = 0; i < this.colunas.length; i++) {
            colunasCopy[i] = new short[this.colunas[i].length];
            System.arraycopy(this.colunas[i], 0, colunasCopy[i], 0, this.colunas[i].length);
        }

        return new Tabuleiro(colunasCopy);
    }

}
