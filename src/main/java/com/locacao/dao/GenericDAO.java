package com.locacao.dao;

import com.locacao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GenericDAO<T> {
    
    private final Class<T> entityClass;
    
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    // Criar/Inserir
    public void save(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            System.out.println("Entidade salva com sucesso: " + entity.getClass().getSimpleName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erro ao salvar entidade: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar entidade", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    // Atualizar
    public void update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            System.out.println("Entidade atualizada com sucesso: " + entity.getClass().getSimpleName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erro ao atualizar entidade: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar entidade", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    // Deletar
    public void delete(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            // Reattach entity to session if detached
            T mergedEntity = session.merge(entity);
            session.remove(mergedEntity);
            transaction.commit();
            System.out.println("Entidade deletada com sucesso: " + entity.getClass().getSimpleName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erro ao deletar entidade: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar entidade", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    // Buscar por ID
    public T findById(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            T entity = session.get(entityClass, id);
            // Inicializar coleções lazy se necessário
            if (entity != null) {
                org.hibernate.Hibernate.initialize(entity);
            }
            return entity;
        } catch (Exception e) {
            System.err.println("Erro ao buscar entidade por ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    // Listar todos
    public List<T> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<T> query = session.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
            List<T> results = query.list();
            // Inicializar coleções lazy
            for (T entity : results) {
                org.hibernate.Hibernate.initialize(entity);
            }
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao listar entidades: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    // Executar query customizada
    public List<T> executeQuery(String hql) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<T> query = session.createQuery(hql, entityClass);
            List<T> results = query.list();
            // Inicializar coleções lazy
            for (T entity : results) {
                org.hibernate.Hibernate.initialize(entity);
            }
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao executar query: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
