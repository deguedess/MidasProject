package br.midas.util.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author Paulo Lopes
 */
public class Cifrador {

    private String textoNormal; //Texto a ser criptografado.
    private String textoCifrado;
    private final String ALGORITIMO = "MD5";

    public String critpoGrafa(String texto) {
        BasicTextEncryptor bte = new BasicTextEncryptor();

        bte.setPassword("123");
        return bte.encrypt(texto);
    }

    public String descripto(String texto) {
        BasicTextEncryptor bte = new BasicTextEncryptor();

        bte.setPassword("123");
        return bte.decrypt(texto);
    }

}
