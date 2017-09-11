/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import javafx.scene.control.TextField;

/**
 *
 * @author NU92585
 */
public class StyleUtil {

    public static String setFieldVermelho() {
        return "-fx-focus-color: #d35244;\n"
                + "    -fx-faint-focus-color: #d3524422;\n"
                + "\n"
                + "    -fx-highlight-fill: -fx-accent;\n"
                + "    -fx-highlight-text-fill: white;\n"
                + "    -fx-background-color:\n"
                + "        -fx-focus-color,\n"
                + "        -fx-control-inner-background,\n"
                + "        -fx-faint-focus-color,\n"
                + "        linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);\n"
                + "    -fx-background-insets: -0.2, 1, -1.4, 3;\n"
                + "    -fx-background-radius: 3, 2, 4, 0;\n"
                + "    -fx-prompt-text-fill: transparent;";
    }

    public static String setFieldVerde() {
        return "-fx-focus-color: #6cd20b;\n"
                + "    -fx-faint-focus-color: #6cd20b22;\n"
                + "\n"
                + "    -fx-highlight-fill: -fx-accent;\n"
                + "    -fx-highlight-text-fill: white;\n"
                + "    -fx-background-color:\n"
                + "        -fx-focus-color,\n"
                + "        -fx-control-inner-background,\n"
                + "        -fx-faint-focus-color,\n"
                + "        linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);\n"
                + "    -fx-background-insets: -0.2, 1, -1.4, 3;\n"
                + "    -fx-background-radius: 3, 2, 4, 0;\n"
                + "    -fx-prompt-text-fill: transparent;";
    }

    public static String setDeafultText() {
//        return ".text-field {\n"
//                + "    -fx-background-color: #a9a9a9 , white , white;\n"
//                + "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\n"
//                + "}\n"
//                + "\n"
//                + ".text-field:focused {\n"
//                + "    -fx-background-color: #a9a9a9 , white , white;\n"
//                + "    -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;\n"
//                + "}";

        return "-fx-focus-color: #7ed3fd;\n"
                + "    -fx-faint-focus-color: #7ed3fd22;\n"
                + "\n"
                + "    -fx-highlight-fill: -fx-accent;\n"
                + "    -fx-highlight-text-fill: white;\n"
                + "    -fx-background-color:\n"
                + "        -fx-focus-color,\n"
                + "        -fx-control-inner-background,\n"
                + "        -fx-faint-focus-color,\n"
                + "        linear-gradient(from 0px 0px to 0px 5px, derive(-fx-control-inner-background, -9%), -fx-control-inner-background);\n"
                + "    -fx-background-insets: -0.2, 1, -1.4, 3;\n"
                + "    -fx-background-radius: 3, 2, 4, 0;\n"
                + "    -fx-prompt-text-fill: transparent;";
    }

}