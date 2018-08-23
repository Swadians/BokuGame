/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public String toString() {
        return "Cromossomo{" + "valorHeuristico=" + valorHeuristico + ", notas=" + Arrays.deepToString(this.notas.toArray(new Float[]{})) + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.notas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cromossomo other = (Cromossomo) obj;
        if (!Objects.equals(this.notas, other.notas)) {
            return false;
        }
        return true;
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
