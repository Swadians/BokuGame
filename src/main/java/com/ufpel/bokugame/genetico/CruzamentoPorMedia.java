/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Weslen
 */
public class CruzamentoPorMedia implements Cruzamento {

    @Override
    public List<Cromossomo> cruza(List<Float> A, List<Float> B) {
        List<Float> filho = new ArrayList<>();

        for (int i = 0; i < A.size(); i++) {
            Float media = (A.get(i) + B.get(i)) / 2;
            filho.add(media);
        }
        ArrayList<Cromossomo> filhos = new ArrayList<>();
        filhos.add(new Cromossomo(filho));

        return filhos;
    }

}
