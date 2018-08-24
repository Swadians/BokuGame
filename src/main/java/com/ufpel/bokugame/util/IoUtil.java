/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author WeslenSchiavon
 */
public class IoUtil {

    public static List<String> getListOfPorts(String file) throws FileNotFoundException {
        List<String> listaDePortas = new ArrayList<>();
        Scanner sc = new Scanner(new File(file));
        
        while (sc.hasNext()) {
            listaDePortas.add(sc.nextLine().trim());
        }

        return listaDePortas;
    }

}
