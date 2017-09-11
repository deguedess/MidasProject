/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import br.emer.internaciolizacao.ResourceLabels;
import java.util.Locale;

/**
 *
 * @author NU92585
 */
public class InternacionalizaUtil {

    public void internacioalizaLabel(TextLabel... text) {

        Locale l = SessionUtil.getIdioma();

        for (int i = 0; i < text.length; i++) {
            TextLabel t = text[i];

            if (t.getLabel() != null) {
                t.getLabel().setText(ResourceLabels.getInfo(t.getLabelEnum(), l) + ":");
            }

//            if (t.getText() != null && t.getLabelEnum() != null) {
//                t.getText().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
//            }

            if (t.getColumn() != null) {
                t.getColumn().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }

            if (t.getCheck() != null) {
                t.getCheck().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }

            if (t.getButton() != null) {
                t.getButton().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }
            
            if (t.getTableC() != null){
                t.getTableC().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }

            if (t.getTab() != null) {
                t.getTab().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }

            if (t.getTitledPane() != null) {
                t.getTitledPane().setText(ResourceLabels.getInfo(t.getLabelEnum(), l));
            }
        }
    }

}
