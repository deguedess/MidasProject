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
public enum TipoTransacao {

    INSERÇÃO,
    EDIÇÃO,
    AUTOMATICO,
    ;
    
    public static List<TipoTransacao> getList(){
        List<TipoTransacao> tss = new ArrayList<>();
        
        tss.add(INSERÇÃO);
        tss.add(EDIÇÃO);
        tss.add(AUTOMATICO);
        
        return tss;
    }

}
