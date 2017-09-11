/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.dao.jpa;

import br.midas.util.util.ConfiguracaoUtil;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author XX05940
 */
public class PersistenceInicial {

    private static EntityManagerFactory emf;
    private static EntityManagerFactory emfTIM;
    private static String conexao;
    private static ConfiguracaoUtil cu = null;

    public static Map getConf() {

        HashMap prop = new HashMap();

        //  prop.put("hibernate.connection.url", "jdbc:oracle:thin:@sacabirsdb01:1521:sistemas");jdbc:postgresql://localhost:5432/infraX
        prop.put("hibernate.connection.url", cu.getNomeConexao());

        prop.put("hibernate.connection.username", cu.getUsuario());

        prop.put("hibernate.connection.password", cu.getSenha());
        // prop.put("hibernate.connection.username", "INFRASYSTEMX");
        //  prop.put("hibernate.connection.password", "INFRASYSTEMX");
        conexao = "Testes";
        
        System.out.println(cu.getNomeConexao());

        return prop;
    }

    public static String getConexao() {
        return conexao;
    }

    public PersistenceInicial(ConfiguracaoUtil cu) {
        this.cu = cu;
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("InfraSysBasePU", getConf());
        }
    }

    public PersistenceInicial(boolean empty, ConfiguracaoUtil cu) {
        this.cu = cu;
        emf = Persistence.createEntityManagerFactory("InfraSysBasePU", getConf());
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static EntityManager getEntityManagerTIM() {
        return emfTIM.createEntityManager();
    }
}
//insert into acessousuario values (1, 1, 1, 1, 1, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 424)
