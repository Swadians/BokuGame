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
 * @author WeslenSchiavon
 */
public class SelecaoPorTorneio implements Selecao {

    private final int tamTorneio;

    public SelecaoPorTorneio(int tamTorneio) {
        this.tamTorneio = tamTorneio;

    }

    @Override
    public Cromossomo aplicaSelecao(List<Cromossomo> cromossomos) {
        Random rd = new Random();

        int posSorteada = rd.nextInt(cromossomos.size() - tamTorneio);

        return cromossomos
                .subList(posSorteada, posSorteada + tamTorneio)
                .stream()
                .max(Cromossomo::compareTo).get();

    }

}
