package com.locacao;

import com.locacao.dao.*;
import com.locacao.model.*;
import com.locacao.util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Classe com exemplos de todas as operações CRUD solicitadas
 */
public class ExemploOperacoes {
    
    public static void main(String[] args) {
        try {
            // Inicializar Hibernate
            System.out.println("=== Inicializando Sistema ===");
            HibernateUtil.getSessionFactory();
            
            // Criar DAOs
            GenericDAO<Marca> marcaDAO = new GenericDAO<>(Marca.class);
            GenericDAO<Modelo> modeloDAO = new GenericDAO<>(Modelo.class);
            GenericDAO<Veiculo> veiculoDAO = new GenericDAO<>(Veiculo.class);
            LocacaoDAO locacaoDAO = new LocacaoDAO();
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
            GenericDAO<Cliente> clienteDAO = new GenericDAO<>(Cliente.class);
            GenericDAO<Contato> contatoDAO = new GenericDAO<>(Contato.class);
            GenericDAO<Endereco> enderecoDAO = new GenericDAO<>(Endereco.class);
            GenericDAO<ContratoLocacao> contratoDAO = new GenericDAO<>(ContratoLocacao.class);
            GenericDAO<Funcionario> funcionarioDAO = new GenericDAO<>(Funcionario.class);
            
            // ========================================
            // OPERAÇÃO 1: Inserir uma nova Marca com seus Modelos
            // ========================================
            System.out.println("\n=== OPERAÇÃO 1: Inserir Marca com Modelos ===");
            
            Marca toyota = new Marca("Toyota");
            
            Modelo corolla = new Modelo("Corolla", new Date(), 10);
            Modelo hilux = new Modelo("Hilux", new Date(), 5);
            
            toyota.addModelo(corolla);
            toyota.addModelo(hilux);
            
            marcaDAO.save(toyota);
            System.out.println("Marca Toyota inserida com 2 modelos!");
            
            // ========================================
            // OPERAÇÃO 2: Inserir um Veículo associado a um Modelo
            // ========================================
            System.out.println("\n=== OPERAÇÃO 2: Inserir Veículo associado a Modelo ===");
            
            Veiculo veiculo1 = new Veiculo(StatusVeiculo.DISPONIVEL, 0, "ABC-1234", 
                                          "9BWZZZ377VT004251", "12345678901", "Prata", TipoCarro.MEDIO);
            corolla.addVeiculo(veiculo1);
            veiculoDAO.save(veiculo1);
            System.out.println("Veículo Corolla inserido com sucesso!");
            
            Veiculo veiculo2 = new Veiculo(StatusVeiculo.DISPONIVEL, 5000, "XYZ-5678", 
                                          "9BD146000P1234567", "98765432109", "Preto", TipoCarro.SUV);;
            hilux.addVeiculo(veiculo2);
            veiculoDAO.save(veiculo2);
            System.out.println("Veículo Hilux inserido com sucesso!");
            
            // ========================================
            // Criar Cliente para as próximas operações
            // ========================================
            System.out.println("\n=== Criando Cliente de Exemplo ===");
            
            Contato contato = new Contato("cliente@email.com", "11-1234-5678", "11-98765-4321");
            Endereco endereco = new Endereco("01310-100", "Av. Paulista", "Apto 100", "1000", "Próximo ao metrô");
            
            Cliente cliente = new Cliente("João Silva", "123.456.789-00", "joao.silva", "senha123", 
                                         contato, endereco, "12345678900");
            clienteDAO.save(cliente);
            System.out.println("Cliente João Silva criado!");
            
            // Criar Funcionário
            Contato contatoFunc = new Contato("func@email.com", "11-2222-3333", "11-99999-8888");
            Endereco enderecoFunc = new Endereco("01310-200", "Av. Paulista", "Sala 50", "2000", "Torre A");
            
            Funcionario funcionario = new Funcionario("Maria Santos", "987.654.321-00", "maria.santos", 
                                                     "senha456", contatoFunc, enderecoFunc, "Gerente");
            funcionarioDAO.save(funcionario);
            System.out.println("Funcionário Maria Santos criado!");
            
            // ========================================
            // OPERAÇÃO 3: Criar uma Locação para um veículo e adicionar Ocorrências
            // ========================================
            System.out.println("\n=== OPERAÇÃO 3: Criar Locação e adicionar Ocorrências ===");
            
            // Criar contrato de locação
            ContratoLocacao contrato = new ContratoLocacao(new Date(), 1000.0f, StatusContrato.ATIVO, 0.0f);
            contrato.setCliente(cliente);
            contrato.setFuncionario(funcionario);
            contratoDAO.save(contrato);
            
            // Criar locação
            Locacao locacao = new Locacao(new Date(), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000), 500.0f);
            locacao.setVeiculo(veiculo1);
            contrato.addLocacao(locacao);
            locacaoDAO.save(locacao);
            System.out.println("Locação criada para o veículo " + veiculo1.getPlaca());
            
            // Adicionar ocorrências
            Ocorrencia ocorrencia1 = new Ocorrencia("Arranhão na porta traseira", 150.0f);
            Ocorrencia ocorrencia2 = new Ocorrencia("Tanque não foi completado", 80.0f);
            
            locacao.addOcorrencia(ocorrencia1);
            locacao.addOcorrencia(ocorrencia2);
            
            ocorrenciaDAO.save(ocorrencia1);
            ocorrenciaDAO.save(ocorrencia2);
            System.out.println("2 ocorrências adicionadas à locação!");
            
            // ========================================
            // OPERAÇÃO 4: Consultar todas as Locações de um determinado Cliente
            // ========================================
            System.out.println("\n=== OPERAÇÃO 4: Consultar Locações do Cliente ===");
            
            List<Locacao> locacoesCliente = locacaoDAO.findByCliente(cliente);
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Total de locações: " + locacoesCliente.size());
            
            for (Locacao loc : locacoesCliente) {
                System.out.println("  - Locação #" + loc.getId() + 
                                 " | Veículo: " + loc.getVeiculo().getPlaca() + 
                                 " | Valor: R$ " + loc.getValorLocacao());
            }
            
            // ========================================
            // OPERAÇÃO 5: Atualizar o status de um Veículo
            // ========================================
            System.out.println("\n=== OPERAÇÃO 5: Atualizar Status do Veículo ===");
            
            System.out.println("Status anterior: " + veiculo1.getStatus());
            veiculo1.setStatus(StatusVeiculo.LOCADO);
            veiculoDAO.update(veiculo1);
            System.out.println("Status atualizado: " + veiculo1.getStatus());
            
            // Verificar atualização
            Veiculo veiculoAtualizado = veiculoDAO.findById(veiculo1.getId());
            System.out.println("Status verificado no banco: " + veiculoAtualizado.getStatus());
            
            // ========================================
            // OPERAÇÃO 6: Excluir uma Ocorrência específica
            // ========================================
            System.out.println("\n=== OPERAÇÃO 6: Excluir Ocorrência Específica ===");
            
            System.out.println("Ocorrência a ser excluída: " + ocorrencia2.getDescricao());
            ocorrenciaDAO.delete(ocorrencia2);
            System.out.println("Ocorrência excluída com sucesso!");
            
            // Verificar exclusão
            List<Ocorrencia> ocorrenciasRestantes = ocorrenciaDAO.findAll();
            System.out.println("Total de ocorrências restantes no sistema: " + ocorrenciasRestantes.size());
            
            // ========================================
            // Resumo Final
            // ========================================
            System.out.println("\n=== RESUMO FINAL ===");
            System.out.println("Total de Marcas: " + marcaDAO.findAll().size());
            System.out.println("Total de Modelos: " + modeloDAO.findAll().size());
            System.out.println("Total de Veículos: " + veiculoDAO.findAll().size());
            System.out.println("Total de Locações: " + locacaoDAO.findAll().size());
            System.out.println("Total de Ocorrências: " + ocorrenciaDAO.findAll().size());
            System.out.println("Total de Clientes: " + clienteDAO.findAll().size());
            
            System.out.println("\n=== Todas as operações foram executadas com sucesso! ===");
            
        } catch (Exception e) {
            System.err.println("Erro ao executar operações: " + e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
