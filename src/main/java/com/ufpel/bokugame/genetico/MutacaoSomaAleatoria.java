/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Weslen
 */
public class MutacaoSomaAleatoria implements Mutacao {

    private final float probMutacao;
    private final float maxValueTosome;

    /**
     *
     * @param probMutacao probabilidade de mutação 1.0 é 100% ou seja todas
     * posições do cromossomo serao mutadas
     * @param maxValueTosome valor maximo a ser somado probabilisticamente
     */
    public MutacaoSomaAleatoria(float probMutacao, float maxValueTosome) {
        this.probMutacao = probMutacao;
        this.maxValueTosome = maxValueTosome;
    }

    @Override
    public Cromossomo muta(Cromossomo cromossomo) {
        Random rd = new Random();

        List<Float> notas = cromossomo.getNotas();

        for (int i = 0; i < notas.size(); i++) {
            if (rd.nextFloat() < this.probMutacao) {
                float novoValor = notas.get(i) + (rd.nextFloat() * this.maxValueTosome);
                notas.set(i, novoValor);
            }
        }

        return new Cromossomo(notas);
    }

}
