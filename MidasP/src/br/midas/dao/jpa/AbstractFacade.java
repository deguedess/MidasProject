/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.

 SELECT *
 FROM user_sequences
 WHERE sequence_name = 'CENTROCUSTO_SEQ';


 */
package br.midas.dao.jpa;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
import br.emer.exceptions.PersistException;
import br.emer.internaciolizacao.MessageEnum;
import br.emer.internaciolizacao.ResourceMessages;
import br.midas.dao.entidades.LogTransacoes;
import br.midas.dao.enumerations.TipoTransacao;
import br.midas.util.util.SessionUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author NU92585
 * @param <T>
 */
public abstract class AbstractFacade<T> implements Serializable {

    protected EntityManager em;

    public AbstractFacade() {
    }

    protected EntityManager getEntityManager() {
        return PersistenceInicial.getEntityManager();
    }

    /**
     *
     * @param entity
     * @return Metodo que valida se o objeto ja foi inserido no banco TRUE caso
     * ja existe FALSE caso nao exista
     */
    public abstract boolean ObjectExists(T entity);

    public void create(T entity) throws PersistException {

        try {
            abreSessao();
            persistData(entity);
            executaCommit();
            fechaSessao();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            fechaSessao();

            throw new PersistException(ResourceMessages.getMessage(MessageEnum.ERROINSERCAO, SessionUtil.getIdioma()) + " - " + entity.getClass().getSimpleName(), e);
        }
    }

    public void edit(T entity, String log) throws PersistException {
        try {

            abreSessao();
            mergeData(entity, log);
            executaCommit();

            fechaSessao();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                fechaSessao();
            }

            throw new PersistException(ResourceMessages.getMessage(MessageEnum.ERROEDICAO, SessionUtil.getIdioma()) + " - " + entity.getClass().getSimpleName(), e);
        }
    }

    public void remove(T entity) throws PersistException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Object c = em.merge(entity);
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new PersistException(ResourceMessages.getMessage(MessageEnum.ERRODELETE, SessionUtil.getIdioma()) + " - " + entity.getClass().getSimpleName(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T find(Long id) {
        return getEntityManager().find(getTypeClass(), id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(getTypeClass()));
        return getEntityManager().createQuery(cq).getResultList();
    }

    protected List<T> findByCriteria(CriteriaQuery<T> criteriaQuery) {
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

//    public List<T> findByGeral(UtilSQL util) {
//        EntityManager em = getEntityManager();
//        try {
//            Query q = em.createQuery(util.getSql().toString());
//            q.setMaxResults(SessionUtil.getConfiguracoes().getLimiteResultados());
//            for (int i = 0; i < util.getParameters().size(); i++) {
//                UtilParameters get = util.getParameters().get(i);
//                q.setParameter(get.getNome(), get.getObjeto());
//            }
//            return q.getResultList();
//        } finally {
//            em.close();
//        }
//    }

//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(getTypeClass()));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0]);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
    public Integer count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(getTypeClass());
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private Class<T> getTypeClass() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }

    /**
     * Metodo que popula o objeto de Log de Transação
     *
     * @param tipo
     * @param entity
     * @param alteracoes
     * @return
     * @throws PersistException
     */
    private LogTransacoes preparaLog(TipoTransacao tipo, T entity, String alteracoes) throws PersistException {
        LogTransacoes log;

        if (tipo.equals(TipoTransacao.INSERÇÃO)) {
            log = new LogTransacoes();

            log.setAlteracoes("Inserção do Objeto: " + entity.toString());

        } else if (tipo.equals(TipoTransacao.EDIÇÃO)
                && alteracoes != null && !alteracoes.isEmpty()) {
            log = new LogTransacoes();

            log.setAlteracoes(entity.toString() + "\n" + alteracoes);

        } else {
            return null;
        }

        log.setClasse(entity.getClass().getName());
        log.setDataEvento(new Date());
        log.setTipoTransacao(tipo);
        log.setUsuario(SessionUtil.getNomeCompleto());

        return log;
    }

    protected void abreSessao() throws PersistException {
        em = getEntityManager();
        entityManagerIsValid();
        em.getTransaction().begin();
    }

    protected void executaCommit() throws PersistException {
        entityManagerIsOpen();
        em.getTransaction().commit();
    }

    protected void fechaSessao() {
        if (em != null) {
            em.close();
        }
    }

    protected void executaRollBack() {
        if (em != null) {
            em.getTransaction().rollback();
        }
    }

    /**
     *
     * @param entity - Entidade que ira salvar no banco CREATE
     * @throws br.emer.exceptions.PersistException
     */
    protected void persistData(T entity) throws PersistException {
        em.persist(entity);//persiste o Objeto

        LogTransacoes log = preparaLog(TipoTransacao.INSERÇÃO, entity, null);
        if (log != null) {
            em.persist(log);
        }
    }

    /**
     *
     * @param entity
     *
     * Métodos que merge o objeto no banco - EDIT
     * @param alt
     * @throws br.emer.exceptions.PersistException
     */
    protected void mergeData(T entity, String alt) throws PersistException {
        em.merge(entity);//edita o Objeto

        LogTransacoes log = preparaLog(TipoTransacao.EDIÇÃO, entity, alt);
        if (log != null) {
            em.persist(log);
        }
    }

    /**
     * @throws PersistException
     *
     * Metodo que valida se a Entity Manager não é nula
     */
    private void entityManagerIsValid() throws PersistException {
        if (em == null) {
            throw new PersistException(ResourceMessages.getMessage(MessageEnum.entityManager, SessionUtil.getIdioma()), null);
        }

        if (em.getTransaction() == null) {
            throw new PersistException(ResourceMessages.getMessage(MessageEnum.transaction, SessionUtil.getIdioma()), null);
        }
    }

    /**
     *
     * @throws PersistException
     *
     * Metodo que valida se a Sessao esta aberta
     */
    private void entityManagerIsOpen() throws PersistException {
        if (!em.getTransaction().isActive()) {
            throw new PersistException(ResourceMessages.getMessage(MessageEnum.sessaoFechada, SessionUtil.getIdioma()), null);
        }
    }

    protected List<Object> getInfo(Object obj, String nome, String sql) {
        List<Object> objs = new ArrayList<>();
        String sit = "";

        if (obj != null) {
            sit = sql;
        }
        objs.add(sit);
        objs.add(nome);
        objs.add(obj);
        objs.add(obj != null);

        return objs;
    }

}
