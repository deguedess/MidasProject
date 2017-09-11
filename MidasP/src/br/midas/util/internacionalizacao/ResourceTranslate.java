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
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceTranslate {

    public static final String BUNDLE_MENSAGENS = "messages";
    public static final String BUNDLE_LABELS = "labels";
    public static final String BUNDLE_SITUATIONS = "situations";

    public static String getMessage(String bundle, TranslateEnumMessages messageEnum, Locale locale, Object... args) {
        ResourceBundle resourceBundle;

        if (locale != null) {
            resourceBundle = ResourceBundle.getBundle(bundle, locale);
        } else {
            resourceBundle = ResourceBundle.getBundle(bundle);
        }

        String message = null;
        if (resourceBundle != null) {
            message = resourceBundle.getString(messageEnum.getProp());
            if (message != null && args.length > 0) {
                message = MessageFormat.format(message, args);
            }
        }
        return message;
    }

    public static String getMessageWithoutEnum(String bundle, String prop, Locale locale, Object... args) {
        ResourceBundle resourceBundle;

        if (locale != null) {
            resourceBundle = ResourceBundle.getBundle(bundle, locale);
        } else {
            resourceBundle = ResourceBundle.getBundle(bundle);
        }

        String message = null;
        if (resourceBundle != null) {
            message = resourceBundle.getString(prop);
            if (message != null && args.length > 0) {
                message = MessageFormat.format(message, args);
            }
        }
        return message;
    }

}
