/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author NU92585
 */
public class SessionUtil {

    private static Locale idioma;
//    private static Idioma language;
//    private static Pais pais;
//    private static Configuracoes configuracoes;
//    private static Usuario usuario;
//    private static List<ResponsavelLocalidade> respos;
//
//    public static List<ResponsavelLocalidade> getRespos() {
//        return respos;
//    }
//
//    public static void setRespos(List<ResponsavelLocalidade> respos) {
//        SessionUtil.respos = respos;
//    }
//
//    public static Usuario getUsuario() {
//        if (usuario == null) {
//            usuario = new Usuario();
//            usuario.setNome("Nenhum Usuário Logado");
//            usuario.setSobrenome("");
//        }
//        return usuario;
//    }
//
//    public static Idioma getLanguage() {
//        return language;
//    }
//
//    public static void setLanguage(Idioma language) {
//        SessionUtil.language = language;
//    }
//
//    public static void setUsuario(Usuario usuario) {
//        SessionUtil.usuario = usuario;
//    }

    public static Locale getIdioma() {
        return idioma;
    }

    public static void setIdioma(Locale idioma) {
        SessionUtil.idioma = idioma;
    }

//    public static Pais getPais() {
//        return pais;
//    }
//
//    public static void setPais(Pais pais) {
//        SessionUtil.pais = pais;
//    }
//
//    public static void setPais(Long id) {
//        SessionUtil.pais = new PaisJpaController().find(id);
//    }
//
//    public static void setIdioma(Long id) {
//        Idioma idi = new IdiomaJpaController().find(id);
//        SessionUtil.idioma = new Locale(idi.getReferencia());
//        SessionUtil.language = idi;
//    }
//
//    public static Configuracoes getConfiguracoes() {
//        if (configuracoes == null) {
////            return configuracoes = new ConfiguracoesJpaController().findAll().get(0);//TODO
//        }
//        return configuracoes;
//    }
//
//    public static void setConfiguracoes(Configuracoes configuracoes) {
//        SessionUtil.configuracoes = configuracoes;
//    }

}
