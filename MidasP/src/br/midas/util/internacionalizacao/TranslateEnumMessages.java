/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.internacionalizacao;

/**
 *
 * @author NU92585
 */
public enum TranslateEnumMessages {

    /**
     * Mensagems
     */
    MAISDEUMITEMENCONTRADO("message.retorno.maisdeumitem"),//
    NENHUMITEMENCONTRADO("message.retorno.nenhumitemencontrado"),//
    
    ;//

    private final String prop;

    private TranslateEnumMessages(String prop) {
        this.prop = prop;
    }

    public String getProp() {
        return prop;
    }
}
