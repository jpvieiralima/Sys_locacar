package com.locacao.util;

import com.locacao.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Criar configuração do Hibernate
            Configuration configuration = new Configuration();
            
            // Carregar propriedades do arquivo hibernate.properties
            configuration.configure();
            
            // Adicionar classes anotadas
            configuration.addAnnotatedClass(Endereco.class);
            configuration.addAnnotatedClass(Contato.class);
            configuration.addAnnotatedClass(Usuario.class);
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(Funcionario.class);
            configuration.addAnnotatedClass(Marca.class);
            configuration.addAnnotatedClass(TipoCarro.class);
            configuration.addAnnotatedClass(Modelo.class);
            configuration.addAnnotatedClass(Veiculo.class);
            configuration.addAnnotatedClass(Categoria.class);
            configuration.addAnnotatedClass(Manutencao.class);
            configuration.addAnnotatedClass(Ocorrencia.class);
            configuration.addAnnotatedClass(Locacao.class);
            configuration.addAnnotatedClass(ContratoLocacao.class);
            configuration.addAnnotatedClass(Pagamento.class);
            
            // Criar ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            
            // Criar SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            
            System.out.println("SessionFactory criada com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro ao criar SessionFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
