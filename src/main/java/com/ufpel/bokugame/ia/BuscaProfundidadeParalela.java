/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeParalela implements Busca {

    @Override
    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica) {
        List<Nodo> jogadasPossiveis = TabuleiroUtil.geraJogadas(base, codJogador);
        Queue<Nodo> respostas = new PriorityBlockingQueue<>();

        Busca buscaProfundidadeIterativa = new BuscaProfundidadeSequencial();

        jogadasPossiveis.parallelStream().forEach(nodo -> {
            respostas.add(buscaProfundidadeIterativa.Busca(nodo, profundidade, codJogador, heuristica));
        });

        return respostas.poll();
    }

}
