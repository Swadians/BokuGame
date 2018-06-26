/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.principal;

import com.ufpel.bokugame.base.Tupla;
import com.ufpel.bokugame.util.HttpUtil;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 *
 * @author WeslenSchiavon
 */
public class Test {

    public static void main(String[] args) throws MalformedURLException, IOException {

        HttpUtil httpUtil = new HttpUtil("http://192.168.0.102:8080");

        Tupla posUltimaJogada = httpUtil.getPosUltimaJogada();
    }
}
