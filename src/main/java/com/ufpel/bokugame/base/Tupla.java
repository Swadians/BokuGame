/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpel.bokugame.base;

/**
 *
 * @author WeslenSchiavon
 */
public class Tupla implements Cloneable {

    public short coluna;
    public short linha;

    public Tupla(short coluna, short linha) {
        this.coluna = coluna;
        this.linha = linha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.coluna;
        hash = 29 * hash + this.linha;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tupla other = (Tupla) obj;
        if (this.coluna != other.coluna) {
            return false;
        }
        if (this.linha != other.linha) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tupla{" + "coluna=" + coluna + ", linha=" + linha + '}';
    }

    @Override
    public Object clone() {

        return new Tupla(coluna, linha);
    }

    public Tupla proximaPosDiagonalPrincipal() {

        if (this.coluna < 5) {
            return new Tupla((short) (this.coluna + 1), (short) (this.linha + 1));
        } else {
            if (this.linha < 5) {
                if (this.coluna < CodigoTabuleiro.QTD_COLUNAS - 1) {
                    return new Tupla((short) (this.coluna + 1), (this.linha));
                } else {
                    return null;
                }
            } else {
                if (this.coluna < CodigoTabuleiro.QTD_COLUNAS - 1) {
                    short tamanhoMaximo = (short) (14 - this.linha);

                    if (this.coluna < tamanhoMaximo) {
                        return new Tupla((short) (this.coluna + 1), this.linha);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

            }
        }

    }

    public Tupla getPosInicialDiagonalPrincipal() {
        short valorLinha;
        short valorColuna;

        if (this.coluna < 6) {
            valorColuna = (this.coluna > this.linha) ? (short) (this.coluna - this.linha) : 0;
            valorLinha = (this.linha > this.coluna) ? (short) (this.linha - this.coluna) : 0;
        } else {
            valorColuna = (this.linha < 5) ? (short) (5 - this.linha) : 0;
            valorLinha = (this.linha > 5) ? (short) (this.linha - 5) : 0;
        }

        return new Tupla(valorColuna, valorLinha);

    }

    public Tupla proximaPosDiagonalSecundaria() {

        if (this.coluna < 5) {
            return new Tupla((short) (this.coluna + 1), this.linha);
        } else {
            if (this.linha < 5) {
                short tamanhoMaximo = (short) (this.linha + this.coluna);

                if ((this.coluna < tamanhoMaximo) && (this.coluna < (CodigoTabuleiro.QTD_COLUNAS - 1))) {
                    return new Tupla((short) (this.coluna + 1), (short) (this.linha - 1));
                } else {
                    return null;
                }
            } else {
                if (this.coluna < CodigoTabuleiro.QTD_COLUNAS - 1) {
                    return new Tupla((short) (this.coluna + 1), (short) (this.linha - 1));
                } else {
                    return null;
                }
            }
        }

    }

    public Tupla getPosInicialDiagonalSecundaria() {
        short valorColuna = (short) (this.linha - 4);
        short valorLinha = this.linha;

        if ((this.coluna - 5) > 0) {
            valorLinha = (short) (this.linha + this.coluna - 5);
            valorColuna = (short) (this.linha + this.coluna - 9);
        }

        if (valorColuna < 0) {
            valorColuna = 0;
        }

        return new Tupla(valorColuna, valorLinha);

    }

}
