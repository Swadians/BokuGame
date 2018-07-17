/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.Stack;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaMinMax implements Busca {

    @Override
    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica) {
        Stack<Nodo> pilhaDeNodos = new Stack<>();

        pilhaDeNodos.add(base);

        while (!pilhaDeNodos.empty()) {

            Nodo aProcurar = pilhaDeNodos.peek();

            if (!aProcurar.isVisitado()) {
                aProcurar.setVisitado();
                short codJogatorAtual = trocaJogador(aProcurar.getJogador());

                heuristica.verificaESetaEstadoFinal(aProcurar);
                if (!aProcurar.isEstadoFinal() && TabuleiroUtil.Profundidade(aProcurar) < profundidade) {
                    pilhaDeNodos.addAll(TabuleiroUtil.geraJogadas(aProcurar, codJogatorAtual));
                } else if (!aProcurar.isEstadoFinal()) {
                    heuristica.calcAndSet(aProcurar);

                    this.podaAlphaBeta(aProcurar, codJogador, pilhaDeNodos);
                    this.calcMinMax(aProcurar, codJogador, pilhaDeNodos);
                }
            } else {
                this.podaAlphaBeta(aProcurar, codJogador, pilhaDeNodos);
                this.calcMinMax(aProcurar, codJogador, pilhaDeNodos);
            }
        }
        return base;
    }

    private void calcMinMax(Nodo aProcurar, short codJogador, Stack<Nodo> pilhaDeNodos) {
        if (aProcurar.getJogador() == codJogador) {
            short valorHeuristicoPai = aProcurar.pai.getValorHeuristico();
            short valorHeuristicoFilho = aProcurar.getValorHeuristico();
            if ((valorHeuristicoPai == -1) || (valorHeuristicoPai < valorHeuristicoFilho)) {
                aProcurar.pai.setValorHeuristico(valorHeuristicoFilho);
            }
            pilhaDeNodos.pop();
        } else {
            short valorHeuristicoPai = aProcurar.pai.getValorHeuristico();
            short valorHeuristicoFilho = aProcurar.getValorHeuristico();
            if ((valorHeuristicoPai == -1) || (valorHeuristicoPai > valorHeuristicoFilho)) {
                aProcurar.pai.setValorHeuristico(valorHeuristicoFilho);
            }
            pilhaDeNodos.pop();
        }
    }

    private boolean podaAlphaBeta(Nodo nodo, short codJogador, Stack<Nodo> pilhaDeNodos) {
        if ((nodo.pai == null) || (nodo.pai.irmao == null)) {
            return false;
        }

        if (nodo.getJogador() == codJogador) {
            short valorHeuristico = nodo.getValorHeuristico();
            if (nodo.pai.irmao.getValorHeuristico() < valorHeuristico) {
                removeTodosFilhos(nodo.pai, pilhaDeNodos);
                return true;
            }
        } else {
            short valorHeuristico = nodo.getValorHeuristico();
            if (nodo.pai.irmao.getValorHeuristico() > valorHeuristico) {
                removeTodosFilhos(nodo.pai, pilhaDeNodos);
                return true;
            }
        }
        return false;
    }

    private void removeTodosFilhos(Nodo pai, Stack<Nodo> pilhaDeNodos) {
        while (pilhaDeNodos.peek().pai == pai) {
            pilhaDeNodos.pop();
        }
    }

    private short trocaJogador(short codJogatorAtual) {
        short tmp = (codJogatorAtual == CodigoTabuleiro.JOGADOR_A)
                ? CodigoTabuleiro.JOGADOR_B
                : CodigoTabuleiro.JOGADOR_A;
        return tmp;
    }
}
