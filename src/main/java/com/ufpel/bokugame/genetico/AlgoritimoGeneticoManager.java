/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.genetico;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.principal.BokuGame;
import com.ufpel.bokugame.util.HttpUtil;
import com.ufpel.bokugame.util.TabPreEstadosUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Weslen
 */
public class AlgoritimoGeneticoManager {

    /**
     * Executa o jogo e retorna um valor euristico do resultado daquela jogada
     *
     * @param args - endereço ip, porta que o servidor roda, codigo Jogador:
     * 127.0.0.1 8080 1
     * @param notas - lista de notas para cada cromossomo
     * @return
     */
    public int executaJogo(String[] args, List<Float> notas) {
        try {
            HttpUtil http = new HttpUtil("http://" + args[0] + ":" + args[1]);

            boolean venceu = BokuGame.Executa(args, notas);

            short numMovimentos = http.getNumMovimentos();

            http.reiniciaTabuleiro();
            if (venceu) {
                return 1000 - numMovimentos;
            } else {
                return numMovimentos;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro: " + e);
            return 0;
        }

    }

    /**
     * Gera uma geração de cromossomos
     *
     * @param tamPopulacao - tamanho total da geração
     * @param notaMaxima - nota maxima que cada cromossomo pode ter
     * @return
     */
    public List<Cromossomo> geraCromossomos(int tamPopulacao, int notaMaxima) {
        // int nota = random.nextInt(1000) * (random.nextBoolean() ? -1 : 1);
        List<Cromossomo> cromossomos = new ArrayList<>();
        Random random = new Random();

        int qtdJogadas = TabPreEstadosUtil.getEstadosPossiveos(CodigoTabuleiro.JOGADOR_A).size();

        for (int j = 0; j < tamPopulacao; j++) {
            List<Float> notas = new ArrayList<>();
            for (int i = 0; i < qtdJogadas; i++) {
                float nota = (float) random.nextInt(notaMaxima) * (random.nextBoolean() ? -1 : 1);

                notas.add(nota);
            }
            cromossomos.add(new Cromossomo(notas));
        }

        return cromossomos;
    }

    /**
     * Executa o cruzamento bazeado na função dada como parametro
     *
     * @param cromossomos lista de cromossomos para cruzamento
     * @param heuristica heuristica de cruzamento
     * @return List de cromossomos filhos
     */
    public List<Cromossomo> fazCruzamento(List<Cromossomo> cromossomos, Cruzamento heuristica) {
        List<Cromossomo> filhos = new ArrayList<>();
        int tamanhoMaximo = cromossomos.size();

        for (int i = 0; i + 1 < tamanhoMaximo; i += 2) {
            Cromossomo paiA = cromossomos.get(i);
            Cromossomo paiB = cromossomos.get(i + 1);

            filhos.addAll(heuristica.cruza(paiA.getNotas(), paiB.getNotas()));
        }

        return filhos;
    }

    /**
     * Aplica mutação nos cromossomos passados
     *
     * @param cromossomos lista de cromossomos a ser mutada
     * @param mutacao tipo de mutacao
     * @return lista de cromossomos
     */
    public List<Cromossomo> aplicaMutacao(List<Cromossomo> cromossomos, Mutacao mutacao) {
        List<Cromossomo> filhos = new ArrayList<>();

        cromossomos.forEach(cromossomo -> filhos.add(mutacao.muta(cromossomo)));
        return filhos;
    }

    /**
     * Seleciona geração futura
     *
     * @param cromossomos lista contendo a população atual
     * @param selecao algoritimo que seleciona a futura população bazeado em
     * algum criterio
     * @param tamPopulacao quantidade de cromossomos para a proxima geração
     * @return nova geração
     */
    public List<Cromossomo> aplicaSelecao(List<Cromossomo> cromossomos, Selecao selecao, int tamPopulacao) {
        List<Cromossomo> retorno = new ArrayList<>();

        Collections.sort(cromossomos);
        for (int i = 0; i < tamPopulacao; i++) {
            retorno.add(selecao.aplicaSelecao(cromossomos));
        }

        Cromossomo melhorCromossomo = cromossomos.get(cromossomos.size() - 1);
        if (!retorno.contains(melhorCromossomo)) {
            retorno.add(melhorCromossomo);
        }

        return retorno;
    }

    /**
     * Seleciona geração futura
     *
     * @param cromossomos lista contendo a população atual
     * @param selecao algoritimo que seleciona a futura população bazeado em
     * algum criterio
     * @param tamPopulacao quantidade de cromossomos para a proxima geração
     * @param qtdPaisNovaGeracao numero de pais para serem passados diretamente
     * para proxima populacao
     * @return
     */
    public List<Cromossomo> aplicaSelecao(List<Cromossomo> cromossomos, Selecao selecao, int tamPopulacao, int qtdPaisNovaGeracao) {
        List<Cromossomo> retorno = new ArrayList<>();

        Collections.sort(cromossomos);
        for (int i = 0; i < tamPopulacao; i++) {
            retorno.add(selecao.aplicaSelecao(cromossomos));
        }
        int ultimaPos = cromossomos.size() - 1;

        for (int i = ultimaPos; i > ultimaPos - qtdPaisNovaGeracao; i--) {
            if (!retorno.contains(cromossomos.get(i))) {
                retorno.add(cromossomos.get(i));
            }
        }

        return retorno;
    }

}
