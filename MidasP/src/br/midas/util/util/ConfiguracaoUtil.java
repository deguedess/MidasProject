/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.util.util;

import br.emer.extras.PropertiesUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author iuri
 */
public class ConfiguracaoUtil extends PropertiesUtil {

    private String nomeConexao;
    private String usuario;
    private String senha;
    private final String passCripto = "Cripto123!";
    private final Cifrador cif = new Cifrador();

    public ConfiguracaoUtil() {
        File fil = new File(System.getProperty("user.dir"));
        System.out.println(fil.getAbsolutePath());
        super.setPathSistem(fil.getAbsolutePath());
        //se nao existir ele cria a pasta
        if (!fil.exists()) {
            Boolean b = fil.mkdir();
        }
        try {
            setaInfo();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracaoUtil.class.getName()).log(Level.SEVERE, null, ex);
            FXUtil.alertaFX(Alert.AlertType.ERROR, "Error", ex.toString());
        }
    }

    private void setaInfo() throws Exception {
        FileInputStream fis;
        File in = new File(pathSistem + "/config.txt");

        if (in.exists()) {

            try {
                fis = new FileInputStream(in);
                props.load(fis);
                fis.close();

                this.setNomeConexao(cif.descripto(props.getProperty("nomeConexao")));
                this.setUsuario(cif.descripto(props.getProperty("usuario")));
                this.setSenha(cif.descripto(props.getProperty("senha")));

            } catch (IOException ex) {
                FXUtil.alertaFX(Alert.AlertType.ERROR, "Error", ex.toString());
            }

        } else {
            setArquivo("jdbc:postgresql://localhost:5432/infraX", "postgres", "123456");
        }
    }

    public void setArquivo(String nome, String user, String pass) throws Exception {
        FileOutputStream fos;
        File out = new File(pathSistem + "/config.txt");
        try {
            fos = new FileOutputStream(out);

            props.setProperty("nomeConexao", cif.critpoGrafa(nome));
            props.setProperty("usuario", cif.critpoGrafa(user));
            props.setProperty("senha", cif.critpoGrafa(pass));

            props.store(fos, "Configurações do Sistema:");
            fos.close();

            this.setNomeConexao(nome);
            this.setSenha(pass);
            this.setUsuario(user);
        } catch (IOException ex) {
            FXUtil.alertaFX(Alert.AlertType.ERROR, "Error", ex.toString());
        }
    }

    public String getNomeConexao() {
        return nomeConexao;
    }

    public void setNomeConexao(String nomeConexao) {
        this.nomeConexao = nomeConexao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
