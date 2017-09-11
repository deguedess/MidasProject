/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.midas.dao.entidades;

import br.midas.dao.enumerations.TipoTransacao;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;

/**
 *
 * @author NU92585
 */
@Entity
public class LogTransacoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "ID_LogTransacoes", sequenceName = "LogTransacao_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ID_LogTransacoes")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoTransacao tipoTransacao;

    @Column(name = "usuario", length = 100, nullable = false)
    private String usuario;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataEvento", nullable = true)
    private Date dataEvento;

    @Column(name = "classe", length = 100, nullable = false)
    private String classe;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "alteracoes", length = 500, nullable = false)
    private String alteracoes;

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAlteracoes() {
        return alteracoes;
    }

    public void setAlteracoes(String alteracoes) {
        this.alteracoes = alteracoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogTransacoes)) {
            return false;
        }
        LogTransacoes other = (LogTransacoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.infra.entidades.LogTransacoes[ id=" + id + " ]";
    }

}
