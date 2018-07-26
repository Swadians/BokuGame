/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.util;

import com.ufpel.bokugame.base.CodigoTabuleiro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WeslenSchiavon
 */
public class TabPreEstadosUtil {

    public static List<short[]> getEstadoCancelaDerrotasBaixaPrioridade(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, adiversario, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, adiversario, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, codJogador, adiversario, CodigoTabuleiro.VAZIO});

        return lista;
    }

    public static List<short[]> getEstadoCancelaDerrotasMediaPrioridade(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{adiversario, adiversario, adiversario, codJogador, CodigoTabuleiro.VAZIO,});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, adiversario, adiversario, codJogador});

        lista.add(new short[]{adiversario, adiversario, codJogador, adiversario, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, adiversario, codJogador, adiversario});

        lista.add(new short[]{adiversario, codJogador, adiversario, adiversario, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, adiversario, codJogador, adiversario, adiversario});

        lista.add(new short[]{codJogador, adiversario, adiversario, adiversario, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, adiversario, adiversario, adiversario});

        return lista;
    }

    public static List<short[]> getEstadoCancelaDerrotasAltaPrioridade(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{codJogador, adiversario, adiversario, adiversario, adiversario});
        lista.add(new short[]{adiversario, codJogador, adiversario, adiversario, adiversario});
        lista.add(new short[]{adiversario, adiversario, codJogador, adiversario, adiversario});
        lista.add(new short[]{adiversario, adiversario, adiversario, codJogador, adiversario});
        lista.add(new short[]{adiversario, adiversario, adiversario, adiversario, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosNormais(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosRegulares(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        lista.add(new short[]{codJogador, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, codJogador,});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador,});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador,});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, codJogador,});

        return lista;
    }

    public static List<short[]> getEstadosBons(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        lista.add(new short[]{codJogador, codJogador, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{codJogador, codJogador, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{codJogador, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, codJogador});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, codJogador});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, CodigoTabuleiro.VAZIO, codJogador, codJogador});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, codJogador, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosOtimos(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, codJogador, codJogador, codJogador, CodigoTabuleiro.VAZIO});

        return lista;
    }

    public static List<short[]> getEstadosVitoria(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        lista.add(new short[]{codJogador, codJogador, codJogador, codJogador, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosSanduiches(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{codJogador, adiversario, adiversario, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosSanduichesOtimos(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{codJogador, adiversario, adiversario, codJogador, codJogador, codJogador, codJogador});
        lista.add(new short[]{codJogador, codJogador, codJogador, codJogador, adiversario, adiversario, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosSanduichesBons(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{codJogador, adiversario, adiversario, codJogador, codJogador, CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO});
        lista.add(new short[]{codJogador, adiversario, adiversario, codJogador, codJogador, CodigoTabuleiro.VAZIO, codJogador});
        lista.add(new short[]{codJogador, adiversario, adiversario, codJogador, codJogador, codJogador, CodigoTabuleiro.VAZIO});

        lista.add(new short[]{CodigoTabuleiro.VAZIO, CodigoTabuleiro.VAZIO, codJogador, codJogador, adiversario, adiversario, codJogador});
        lista.add(new short[]{codJogador, CodigoTabuleiro.VAZIO, codJogador, codJogador, adiversario, adiversario, codJogador});
        lista.add(new short[]{CodigoTabuleiro.VAZIO, codJogador, codJogador, codJogador, adiversario, adiversario, codJogador});

        return lista;
    }

    public static List<short[]> getEstadosSanduichesParaEscolha(short codJogador) {
        List<short[]> lista = new ArrayList<>();

        short adiversario = TabuleiroUtil.trocaJogador(codJogador);

        lista.add(new short[]{adiversario, codJogador, codJogador, codJogador, codJogador});
        lista.add(new short[]{codJogador, codJogador, codJogador, codJogador, adiversario});

        lista.add(new short[]{codJogador, codJogador, codJogador, adiversario});
        lista.add(new short[]{adiversario, codJogador, codJogador, codJogador});

        lista.add(new short[]{codJogador, codJogador, adiversario});
        lista.add(new short[]{adiversario, codJogador, codJogador});

        lista.add(new short[]{codJogador, adiversario, codJogador});

        return lista;
    }

    public static boolean contains(final short[] array, final short[] target) {
        // check that arrays are not null omitted
        if (target.length == 0) {
            return true;
        }
        outer:
        for (int i = 0; i < array.length - target.length + 1; i++) {
            for (int j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean contains(final short[] array, final short[] target, short posNecessaria) {
        // check that arrays are not null omitted
        if (target.length == 0) {
            return true;
        }

        outer:
        for (short i = 0; i < array.length - target.length + 1; i++) {
            for (short j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            short posMaxima = (short) (i + target.length - 1);
            if ((posNecessaria >= i) && (posNecessaria <= posMaxima)) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    public static boolean contains(final short[] array, final short[] target, short... posNecessaria) {
        // check that arrays are not null omitted
        if (target.length == 0) {
            return true;
        }

        outer:
        for (short i = 0; i < array.length - target.length + 1; i++) {
            for (short j = 0; j < target.length; j++) {
                if (array[i + j] != target[j]) {
                    continue outer;
                }
            }
            short posMaxima = (short) (i + target.length - 1);

            for (int j = 0; j < posNecessaria.length; j++) {
                if ((posNecessaria[j] >= i) && (posNecessaria[j] <= posMaxima)) {
                    return true;
                }
            }

            return false;

        }
        return false;
    }
}
