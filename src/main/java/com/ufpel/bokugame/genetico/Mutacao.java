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
public interface Mutacao {

    List<Cromossomo> muta(List<Cromossomo> cromossomos);
}
