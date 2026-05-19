package com.locacao.dao;

import com.locacao.model.Ocorrencia;
import com.locacao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OcorrenciaDAO extends GenericDAO<Ocorrencia> {
    
    public OcorrenciaDAO() {
        super(Ocorrencia.class);
    }
    
    /**
     * Excluir uma ocorrência específica por ID
     */
    public void deleteById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Ocorrencia ocorrencia = session.get(Ocorrencia.class, id);
            if (ocorrencia != null) {
                session.remove(ocorrencia);
                System.out.println("Ocorrência deletada com sucesso: ID " + id);
            } else {
                System.out.println("Ocorrência não encontrada com ID: " + id);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Erro ao deletar ocorrência: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar ocorrência", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Busca todas as ocorrências de uma locação específica
     */
    public List<Ocorrencia> findByLocacao(Long locacaoId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Ocorrencia o WHERE o.locacao.id = :locacaoId";
            Query<Ocorrencia> query = session.createQuery(hql, Ocorrencia.class);
            query.setParameter("locacaoId", locacaoId);
            List<Ocorrencia> results = query.list();
            
            // Inicializar coleções lazy
            for (Ocorrencia ocorrencia : results) {
                org.hibernate.Hibernate.initialize(ocorrencia);
                if (ocorrencia.getLocacao() != null) {
                    org.hibernate.Hibernate.initialize(ocorrencia.getLocacao());
                }
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao buscar ocorrências por locação: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
