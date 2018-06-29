/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.ia;

import com.ufpel.bokugame.base.Nodo;
import com.ufpel.bokugame.util.IaUtil;
import com.ufpel.bokugame.util.TabuleiroUtil;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class BuscaProfundidadeParalela implements Busca {

    @Override
    public Nodo Busca(Nodo base, short profundidade, short codJogador, Heuristica heuristica) {
        List<Nodo> jogadasPossiveis = TabuleiroUtil.geraJogadas(base, codJogador);

        Busca buscaProfundidadeIterativa = new BuscaMinMax();

        short bestPleis = IaUtil.calcBestPleis(jogadasPossiveis.size());
        System.out.println(bestPleis);
        jogadasPossiveis.parallelStream().forEach(nodo -> {
            buscaProfundidadeIterativa.Busca(nodo, bestPleis, codJogador, heuristica);
        });

        Collections.sort(jogadasPossiveis);

        return jogadasPossiveis.get(0);
    }

}
