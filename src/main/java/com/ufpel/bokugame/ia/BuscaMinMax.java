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

        boolean profundidadePar = (profundidade % 2) == 0;

        pilhaDeNodos.add(base);

        while (!pilhaDeNodos.empty()) {

            Nodo aProcurar = pilhaDeNodos.peek();

            if (!aProcurar.isVisitado()) {
                aProcurar.setVisitado();
                short codJogatorAtual = TrocaJogador(aProcurar.getJogador());

                heuristica.verificaESetaEstadoFinal(aProcurar);
                if (!aProcurar.isEstadoFinal() && TabuleiroUtil.Profundidade(aProcurar) < profundidade) {
                    pilhaDeNodos.addAll(TabuleiroUtil.geraJogadas(aProcurar, codJogatorAtual));
                } else {
                    heuristica.calcAndSet(aProcurar);

                    CalcMinMax(aProcurar, codJogador, pilhaDeNodos);
                }
            } else {

                if (profundidadePar) {
                    short tmp = aProcurar.getValorHeuristico();
                    heuristica.calcAndSet(aProcurar);

                    short novo = (short) (aProcurar.getValorHeuristico() + tmp);
                    aProcurar.setValorHeuristico(novo);

                    this.CalcMinMax(aProcurar, codJogador, pilhaDeNodos);

                } else {
                    this.CalcMinMax(aProcurar, codJogador, pilhaDeNodos);
                }
            }
        }
        return base;
    }

    private void CalcMinMax(Nodo aProcurar, short codJogador, Stack<Nodo> pilhaDeNodos) {
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

    private short TrocaJogador(short codJogatorAtual) {
        short tmp = (codJogatorAtual == CodigoTabuleiro.JOGADOR_A)
                ? CodigoTabuleiro.JOGADOR_B
                : CodigoTabuleiro.JOGADOR_A;
        return tmp;
    }
}
