/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.dao.enumerations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NU92585
 */
public enum TipoArquivoFatura {

    FebrabanV2;

    public static List<TipoArquivoFatura> getAll() {
        List<TipoArquivoFatura> get = new ArrayList<>();
        get.add(FebrabanV2);

        return get;
    }

}
