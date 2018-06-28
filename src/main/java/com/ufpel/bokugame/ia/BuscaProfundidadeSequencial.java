/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeSequencial implements Busca {

    @Override
    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica) {
        Stack<Nodo> pilhaDeNodos = new Stack<>();
        Nodo resposta = new Nodo();
        short codJogadorAnterior;

        pilhaDeNodos.add(base);

        base.setJogador(codJogador);
        while (!pilhaDeNodos.empty()) {

            Nodo aProcurar = pilhaDeNodos.pop();

            short codJogatorAtual = TrocaJogador(aProcurar.getJogador());

            heuristica.calcAndSet(aProcurar);

            if (aProcurar.getValorHeuristico() > resposta.getValorHeuristico()) {
                resposta = aProcurar;
            }

            if (!aProcurar.isEstadoFinal() && TabuleiroUtil.Profundidade(aProcurar) < profundidade) {
                List<Nodo> jogadas = TabuleiroUtil.geraJogadas(aProcurar, codJogatorAtual);
                jogadas.forEach(jogada -> {
                    if (jogada != null) {
                        pilhaDeNodos.add(jogada);
                    }
                });
            }
        }
        Nodo primeiraJogada = TabuleiroUtil.getPrimeiraJogada(resposta);
        primeiraJogada.setValorHeuristico(resposta.getValorHeuristico());

        return primeiraJogada;
    }

    private short TrocaJogador(short codJogatorAtual) {
        codJogatorAtual = (codJogatorAtual == CodigoTabuleiro.JOGADOR_A)
                ? CodigoTabuleiro.JOGADOR_B
                : CodigoTabuleiro.JOGADOR_A;
        return codJogatorAtual;
    }

}
