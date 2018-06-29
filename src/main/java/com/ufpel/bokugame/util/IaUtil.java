/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.util;

/**
 *
 * @author WeslenSchiavon
 */
public class IaUtil {

    private static int cargaMaximaPlies = 4;

    public static short calcBestPleis(int numFilhos) {
        int numPlies = 4;
        while (Math.pow(numPlies, numFilhos) <= Math.pow(cargaMaximaPlies, 78)) {
            numPlies++;
        }

        return (short) --numPlies;
    }

}
