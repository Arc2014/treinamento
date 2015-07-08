package br.com.box.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.box.model.EntidadeComum;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T extends EntidadeComum> {

    private static final String QUERY_LISTAR_POR = "FROM %s o WHERE o.%s = :valor";

    @PersistenceContext(unitName = "BOX_DS")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(GenericDAO.class);

    public void salvar(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void atualizar(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public T buscar(Long id) {
        return (T) getEntityManager().find(getTypeClass(), id);
    }

    public T buscaDetach(Long id) {
        T object = (T) buscar(id);
        getEntityManager().detach(object);
        return object;
    }

    public void delete(T entity) {
        entity = getEntityManager().merge(entity);
        getEntityManager().remove(entity);
        getEntityManager().flush();
    }

    public void delete(Long id) {
        T object = buscar(id);
        delete(object);
    }

    public List<T> listarPor(String atributo, Object valor) {
        String sql = String.format(QUERY_LISTAR_POR, getTypeClass().getSimpleName(), atributo);
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("valor", valor);
        return query.getResultList();
    }

    public List<T> listarPorLike(String atributo, Object valor) {
        Query query = getEntityManager().createQuery(
                "SELECT o FROM " + getTypeClass().getSimpleName() + " o WHERE o."
                        + atributo + " like :valor");
        query.setParameter("valor", "%" + valor + "%");
        return query.getResultList();
    }

    public T buscarPor(String atributo, Object valor) {
        Query query = getEntityManager().createQuery(
                "SELECT o FROM " + getTypeClass().getSimpleName() + " o WHERE o."
                        + atributo + " = :valor");
        query.setParameter("valor", valor);
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List<T> listar() {
        return getEntityManager().createQuery(
                "SELECT o FROM " + getTypeClass().getSimpleName() + " o")
                .getResultList();
    }

    public T attach(T object) {
        return (T) getEntityManager().getReference(getTypeClass(),
                object.getId());
    }

    protected EntityManager getEntityManager() {
        if (em == null) {
            throw new IllegalStateException("getEntityManager() não foi setado antes do uso neste DAO");
        }
        return em;
    }

    private Class<?> getTypeClass() {
        return (Class<?>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T salvarERetornar(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        return entity;
    }

    public Query criarQuery(String query) {
        return getEntityManager().createQuery(query);
    }

}
