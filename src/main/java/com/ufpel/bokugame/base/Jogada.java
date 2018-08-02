/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.base;

/**
 *
 * @author Weslen
 */
public class Jogada {

    private short[] jogada;
    private float nota;

    public Jogada(short[] jogada) {
        this.jogada = jogada;
        this.nota = 10;
    }

    public Jogada(short[] jogada, short nota) {
        this.jogada = jogada;
        this.nota = nota;
    }

    public short[] getJogada() {
        return jogada;
    }

    public void setJogada(short[] jogada) {
        this.jogada = jogada;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

}
