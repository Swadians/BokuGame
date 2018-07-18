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

    public static short calcBestPleis(int numFilhos) {
        short numPlies = 3;

        if (numFilhos < 30) {
            numPlies++;
        } else if (numFilhos < 10) {
            numPlies += 2;
        }

        return (short) numPlies;
    }

}
