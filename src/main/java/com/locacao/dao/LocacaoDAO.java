package com.locacao.dao;

import com.locacao.model.Cliente;
import com.locacao.model.Locacao;
import com.locacao.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LocacaoDAO extends GenericDAO<Locacao> {
    
    public LocacaoDAO() {
        super(Locacao.class);
    }
    
    /**
     * Busca todas as locações de um cliente específico
     */
    public List<Locacao> findByCliente(Cliente cliente) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Locacao l WHERE l.cliente.id = :clienteId";
            Query<Locacao> query = session.createQuery(hql, Locacao.class);
            query.setParameter("clienteId", cliente.getId());
            List<Locacao> results = query.list();
            
            for (Locacao locacao : results) {
                org.hibernate.Hibernate.initialize(locacao);
                if (locacao.getVeiculo() != null) {
                    org.hibernate.Hibernate.initialize(locacao.getVeiculo());
                }
                if (locacao.getListaOcorrencias() != null) {
                    org.hibernate.Hibernate.initialize(locacao.getListaOcorrencias());
                }
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao buscar locações: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Busca locações por ID do cliente
     */
    public List<Locacao> findByClienteId(Long clienteId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Locacao l WHERE l.contratoLocacao.cliente.id = :clienteId";
            Query<Locacao> query = session.createQuery(hql, Locacao.class);
            query.setParameter("clienteId", clienteId);
            List<Locacao> results = query.list();
            
            // Inicializar coleções lazy
            for (Locacao locacao : results) {
                org.hibernate.Hibernate.initialize(locacao);
                if (locacao.getVeiculo() != null) {
                    org.hibernate.Hibernate.initialize(locacao.getVeiculo());
                }
                if (locacao.getListaOcorrencias() != null) {
                    org.hibernate.Hibernate.initialize(locacao.getListaOcorrencias());
                }
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao buscar locações por cliente ID: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Busca locações por veículo
     */
    public List<Locacao> findByVeiculo(Long veiculoId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Locacao l WHERE l.veiculo.id = :veiculoId";
            Query<Locacao> query = session.createQuery(hql, Locacao.class);
            query.setParameter("veiculoId", veiculoId);
            List<Locacao> results = query.list();
            
            // Inicializar coleções lazy
            for (Locacao locacao : results) {
                org.hibernate.Hibernate.initialize(locacao);
                if (locacao.getListaOcorrencias() != null) {
                    org.hibernate.Hibernate.initialize(locacao.getListaOcorrencias());
                }
            }
            
            return results;
        } catch (Exception e) {
            System.err.println("Erro ao buscar locações por veículo: " + e.getMessage());
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
