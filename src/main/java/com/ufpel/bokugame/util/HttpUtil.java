/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.util;

import com.ufpel.bokugame.base.Tabuleiro;
import com.ufpel.bokugame.base.Tupla;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author WeslenSchiavon
 */
public class HttpUtil {

    private final String urlBase;
    private final String urlGetMovimentos;
    private final String urlMove;
    private final String urlJogador;
    private final String urlEstadoAtualTabuleiro;
    private final String urlPosUltimaJoga;

    public HttpUtil(String urlBase) {
        this.urlBase = urlBase;
        this.urlGetMovimentos = urlBase + "/movimentos";
        this.urlMove = urlBase + "/move?player=%d&coluna=%d&linha=%d";
        this.urlJogador = urlBase + "/jogador";
        this.urlEstadoAtualTabuleiro = urlBase + "/tabuleiro";
        this.urlPosUltimaJoga = urlBase + "/ultima_jogada";
    }

    private String getRawMovimentosPossiveis() {

        try {
            URL url = new URL(this.urlGetMovimentos);
            HttpURLConnection connection = makeRequest(url);

            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream()));

            String retorno = "";
            while (sc.hasNext()) {
                retorno += sc.next();
            }

            return retorno;
        } catch (IOException e) {
            return "";
        }
    }

    public Tupla getPosUltimaJogada() {
        try {
            URL url = new URL(this.urlPosUltimaJoga);
            HttpURLConnection connection = makeRequest(url);

            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream()));

            String retorno = "";
            while (sc.hasNext()) {
                retorno += sc.next();
            }

            String[] colunaLinha = retorno.trim().replaceAll("\\(|\\)", "").split(",");

            return new Tupla(Integer.parseInt(colunaLinha[0]), Integer.parseInt(colunaLinha[1]));
        } catch (IOException e) {
            return new Tupla(0, 0);
        }
    }

    private HttpURLConnection makeRequest(URL url) throws ProtocolException, IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }

    public List<Tupla> getMovimentosPossiveis() {
        String movimentos = this.getRawMovimentosPossiveis();

        String[] split = movimentos.replaceAll("\\)|\\(|\\[|\\]", " ").split(",");

        List<Tupla> tuplas = new ArrayList<>(split.length);
        for (int i = 0; i < split.length; i += 2) {
            tuplas.add(new Tupla(Integer.parseInt(split[i].trim()), Integer.parseInt(split[i + 1].trim())));
        }

        return tuplas;
    }

    private String getRawTabuleiroAtual() {
        try {
            URL url = new URL(this.urlEstadoAtualTabuleiro);
            HttpURLConnection connection = makeRequest(url);

            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream()));

            String retorno = "";
            while (sc.hasNext()) {
                retorno += sc.next();
            }

            return retorno;

        } catch (IOException e) {
            return "";
        }
    }

    public Tabuleiro getTabuleiroAtual() {
        String tabuleiroRaw = this.getRawTabuleiroAtual().trim();

        String replaceFirst = tabuleiroRaw.replaceAll("\\[", "");

        int posUltimaOcorrencia = replaceFirst.lastIndexOf("]");
        String tabuleiroFiltrado = replaceFirst.substring(0, posUltimaOcorrencia);

        String[] listaDeValores = tabuleiroFiltrado.split("] *,?");

        Tabuleiro tabuleiro = new Tabuleiro();
        for (int i = 0; i < listaDeValores.length; i++) {
            String[] valoresColunaAtual = listaDeValores[i].split(",");

            int[] temp = ArrayParsInt(valoresColunaAtual, i);
            tabuleiro.setValorColuna(i, temp);
        }

        return tabuleiro;

    }

    private int[] ArrayParsInt(String[] valoresColunaAtual, int i) throws NumberFormatException {
        int[] temp = new int[valoresColunaAtual.length];
        for (int j = 0; j < valoresColunaAtual.length; j++) {
            temp[j] = Integer.parseInt(valoresColunaAtual[j]);
        }
        return temp;
    }

    public boolean movePeca(int numJogador, int coluna, int linha) {

        try {
            String urlMoveFormatada = String.format(this.urlMove, numJogador, coluna, linha);
            URL url = new URL(urlMoveFormatada);
            HttpURLConnection connection = makeRequest(url);

            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream()));

            return !sc.nextLine().contains("-1");

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public int getCodVezJogador() {
        try {
            URL url = new URL(this.urlJogador);
            HttpURLConnection connection = makeRequest(url);

            Scanner sc = new Scanner(new InputStreamReader(connection.getInputStream()));

            String codJogador = sc.nextLine().trim();

            return Integer.parseInt(codJogador);
        } catch (IOException e) {
            return 0;
        }
    }

}
