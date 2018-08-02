/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public interface Busca {

    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica, List<Float> notas);

}
