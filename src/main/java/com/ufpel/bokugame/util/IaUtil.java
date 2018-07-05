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

        if (numFilhos < 50) {
            numPlies++;
        } else if (numFilhos < 24) {
            numPlies += 2;
        } else if (numFilhos < 14) {
            numPlies += 3;
        } else if (numFilhos < 10) {
            numPlies += 4;
        } else if (numFilhos < 8) {
            numPlies += 5;
        } else if (numFilhos < 6) {
            numPlies += 6;
        } else if (numFilhos < 5) {
            numPlies += 7;
        }

        return (short) numPlies;
    }

}
