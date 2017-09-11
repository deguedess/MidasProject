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
public enum MoedaEnum {

    Dolar,
    Euro,
    Peso,
    Real;
    
    public static List<MoedaEnum> getAll(){
        List<MoedaEnum> list = new ArrayList<>();
        
        list.add(Dolar);
        list.add(Euro);
        list.add(Peso);
        list.add(Real);
        
        return list;
    }
}
