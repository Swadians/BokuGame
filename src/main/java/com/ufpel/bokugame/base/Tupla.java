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
public class Tupla {

    public int coluna;
    public int linha;

    public Tupla(int coluna, int linha) {
        this.coluna = coluna;
        this.linha = linha;
    }

    @Override
    public String toString() {
        return "Tupla{" + "coluna=" + coluna + ", linha=" + linha + '}';
    }

}
