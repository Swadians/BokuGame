/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeParalela implements Busca {

    @Override
    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica, List<Float> notas) {
        Busca buscaProfundidadeIterativa = new BuscaMinMax();
        List<Nodo> jogadasPossiveis = TabuleiroUtil.geraJogadas(base, codJogador);

        jogadasPossiveis.parallelStream().forEach(nodo -> {
            buscaProfundidadeIterativa.Busca(nodo, profundidade, codJogador, heuristica, notas);
        });
        Nodo maiorHeuristica = this.getMaiorHeuristica(jogadasPossiveis);

        return maiorHeuristica;
    }

    private Nodo getMaiorHeuristica(List<Nodo> nodos) {

        Nodo maior = new Nodo();

        for (Nodo nodo : nodos) {
            if (maior.getValorHeuristico() < nodo.getValorHeuristico()) {
                maior = nodo;
            }
        }

        return maior;
    }

}
