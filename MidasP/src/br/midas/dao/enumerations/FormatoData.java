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
public enum FormatoData {

    ddMMyyyyB("dd/MM/yyyy"),
    ddMMyyB("dd/MM/yy"),
    ddMMyyyyT("dd-MM-yyyy"),
    ddMMyyT("dd-MM-yy"),
    yyyyMMddT("yyyy-MM-dd"),
    yyyyMMddB("yyyy/MM/dd");

    private final String format;

    private FormatoData(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static List<FormatoData> getFormatoDataAll() {
        List<FormatoData> all = new ArrayList<>();

        all.add(ddMMyyyyB);
        all.add(ddMMyyB);
        all.add(ddMMyyyyT);
        all.add(ddMMyyT);
        all.add(yyyyMMddT);
        all.add(yyyyMMddB);

        return all;
    }

    @Override
    public String toString() {
        return this.getFormat();
    }

}
