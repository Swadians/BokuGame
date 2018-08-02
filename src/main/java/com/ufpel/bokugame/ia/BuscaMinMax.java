/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import com.ufpel.bokugame.base.Jogada;
import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabPreEstadosUtil;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaMinMax implements Busca {

    @Override
    public Nodo Busca(Nodo raiz, short profundidade, short codJogador, Heuristica heuristica, List<Float> notas) {
        Stack<Nodo> pilhaDeNodos = new Stack<>();
        TabPreEstadosUtil tab = new TabPreEstadosUtil();

        List<Jogada> jogadasA = tab.getJogadas(CodigoTabuleiro.JOGADOR_A, notas);
        List<Jogada> jogadasB = tab.getJogadas(CodigoTabuleiro.JOGADOR_B, notas);

        pilhaDeNodos.add(raiz);

        while (!pilhaDeNodos.empty()) {

            Nodo aProcurar = pilhaDeNodos.peek();

            if (!aProcurar.isVisitado()) {
                aProcurar.setVisitado();
                short codJogatorAtual = TabuleiroUtil.trocaJogador(aProcurar.getJogador());

                heuristica.verificaESetaEstadoFinal(aProcurar);
                if (!aProcurar.isEstadoFinal() && TabuleiroUtil.Profundidade(aProcurar) < profundidade) {
                    pilhaDeNodos.addAll(TabuleiroUtil.geraJogadas(aProcurar, codJogatorAtual));
                } else if (!aProcurar.isEstadoFinal()) {
                    List<Jogada> jogadas = (aProcurar.getJogador() == CodigoTabuleiro.JOGADOR_A) ? jogadasA : jogadasB;

                    heuristica.calcAndSet(aProcurar, jogadas);

                    this.podaAlphaBeta(aProcurar, codJogador, pilhaDeNodos);
                    this.calcMinMax(aProcurar, codJogador, pilhaDeNodos);
                }
            } else {
                this.podaAlphaBeta(aProcurar, codJogador, pilhaDeNodos);
                this.calcMinMax(aProcurar, codJogador, pilhaDeNodos);
            }
        }
        return raiz;
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
}
