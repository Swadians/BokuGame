/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import java.util.List;

/**
 *
 * @author Weslen
 */
public class Cromossomo implements Comparable<Cromossomo> {

    private final List<Float> notas;
    private int valorHeuristico;

    public Cromossomo(List<Float> notas) {
        this.notas = notas;
    }

    public int getValorHeuristico() {
        return valorHeuristico;
    }

    public void setValorHeuristico(int valorHeuristico) {
        this.valorHeuristico = valorHeuristico;
    }

    public List<Float> getNotas() {
        return notas;
    }

    @Override
    public int compareTo(Cromossomo other) {
        if (this.valorHeuristico > other.valorHeuristico) {
            return 1;
        } else if (this.valorHeuristico < other.valorHeuristico) {
            return -1;
        }
        return 0;
    }

}
