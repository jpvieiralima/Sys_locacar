package com.locacao.controller;

import com.locacao.dao.*;
import com.locacao.model.*;
import com.locacao.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {
    
    // Aba Marcas e Modelos
    @FXML private TextField txtMarcaNome;
    @FXML private Button btnInserirMarca;
    @FXML private ComboBox<Marca> cmbMarcas;
    @FXML private TextField txtModeloNome;
    @FXML private TextField txtModeloAno;
    @FXML private TextField txtModeloQt;
    @FXML private Button btnInserirModelo;
    @FXML private TableView<Marca> tableMarcas;
    @FXML private TableColumn<Marca, Long> colMarcaId;
    @FXML private TableColumn<Marca, String> colMarcaNome;
    @FXML private TableColumn<Marca, Integer> colMarcaQtdModelos;
    
    // Aba Veículos
    @FXML private ComboBox<Modelo> cmbModelos;
    @FXML private ComboBox<TipoCarro> cmbTipoCarro;
    @FXML private TextField txtVeiculoPlaca;
    @FXML private TextField txtVeiculoChassi;
    @FXML private TextField txtVeiculoRenavam;
    @FXML private TextField txtVeiculoCor;
    @FXML private TextField txtVeiculoKm;
    @FXML private ComboBox<StatusVeiculo> cmbVeiculoStatus;
    @FXML private Button btnInserirVeiculo;
    @FXML private TextField txtNovoKm;
    @FXML private ComboBox<StatusVeiculo> cmbNovoStatus;
    @FXML private Button btnAtualizarKm;
    @FXML private Button btnAtualizarStatus;
    @FXML private TableView<Veiculo> tableVeiculos;
    @FXML private TableColumn<Veiculo, Long> colVeiculoId;
    @FXML private TableColumn<Veiculo, String> colVeiculoPlaca;
    @FXML private TableColumn<Veiculo, String> colVeiculoModelo;
    @FXML private TableColumn<Veiculo, String> colVeiculoTipo;
    @FXML private TableColumn<Veiculo, StatusVeiculo> colVeiculoStatus;
    @FXML private TableColumn<Veiculo, Integer> colVeiculoKm;
    
    // Aba Locações
    @FXML private ComboBox<Cliente> cmbLocacaoCliente;
    @FXML private ComboBox<TipoCarro> cmbLocacaoTipoCarro;
    @FXML private ComboBox<Veiculo> cmbLocacaoVeiculo;
    @FXML private DatePicker dpDataRetirada;
    @FXML private DatePicker dpDataDevolucao;
    @FXML private Label lblPrecoDiaria;
    @FXML private Label lblDias;
    @FXML private Label lblValorTotal;
    @FXML private Button btnCriarLocacao;
    @FXML private TextField txtDiasAlterar;
    @FXML private ComboBox<Locacao> cmbLocacoes;
    @FXML private TextField txtOcorrenciaDescricao;
    @FXML private TextField txtOcorrenciaValor;
    @FXML private Button btnAdicionarOcorrencia;
    @FXML private ComboBox<Cliente> cmbClientes;
    @FXML private Button btnConsultarLocacoes;
    @FXML private TableView<Locacao> tableLocacoes;
    @FXML private TableColumn<Locacao, Long> colLocacaoId;
    @FXML private TableColumn<Locacao, String> colLocacaoVeiculo;
    @FXML private TableColumn<Locacao, java.util.Date> colLocacaoDataRetirada;
    @FXML private TableColumn<Locacao, java.util.Date> colLocacaoDataDevolucao;
    @FXML private TableColumn<Locacao, Float> colLocacaoValor;
    
    // Aba Ocorrências
    @FXML private TableView<Ocorrencia> tableOcorrencias;
    @FXML private TableColumn<Ocorrencia, Long> colOcorrenciaId;
    @FXML private TableColumn<Ocorrencia, String> colOcorrenciaDescricao;
    @FXML private TableColumn<Ocorrencia, Float> colOcorrenciaValor;
    @FXML private TableColumn<Ocorrencia, Long> colOcorrenciaLocacao;
    @FXML private Button btnExcluirOcorrencia;
    
    // Aba Clientes
    @FXML private TextField txtClienteCnh;
    @FXML private TextField txtClienteNome;
    @FXML private TextField txtClienteCpf;
    @FXML private TextField txtClienteLogin;
    @FXML private PasswordField txtClienteSenha;
    @FXML private TextField txtClienteEmail;
    @FXML private TextField txtClienteTelefone;
    @FXML private TextField txtClienteCelular;
    @FXML private TextField txtClienteCep;
    @FXML private TextField txtClienteLogradouro;
    @FXML private TextField txtClienteComplemento;
    @FXML private TextField txtClienteNumero;
    @FXML private TextField txtClienteReferencia;
    @FXML private Button btnInserirCliente;
    @FXML private TableView<Cliente> tableClientes;
    @FXML private TableColumn<Cliente, Long> colClienteId;
    @FXML private TableColumn<Cliente, String> colClienteCnh;
    @FXML private TableColumn<Cliente, String> colClienteNome;
    @FXML private TableColumn<Cliente, String> colClienteCpf;
    @FXML private TableColumn<Cliente, String> colClienteEmail;
    @FXML private TableColumn<Cliente, String> colClienteTelefone;
    
    // Status
    @FXML private Label lblStatus;
    
    // DAOs
    private GenericDAO<Marca> marcaDAO;
    private GenericDAO<Modelo> modeloDAO;
    private GenericDAO<Veiculo> veiculoDAO;
    private LocacaoDAO locacaoDAO;
    private OcorrenciaDAO ocorrenciaDAO;
    private GenericDAO<Cliente> clienteDAO;
    
    @FXML
    public void initialize() {
        // Inicializar DAOs
        marcaDAO = new GenericDAO<>(Marca.class);
        modeloDAO = new GenericDAO<>(Modelo.class);
        veiculoDAO = new GenericDAO<>(Veiculo.class);
        locacaoDAO = new LocacaoDAO();
        ocorrenciaDAO = new OcorrenciaDAO();
        clienteDAO = new GenericDAO<>(Cliente.class);
        
        // Configurar tabelas
        setupTables();
        
        // Configurar ComboBoxes
        setupComboBoxes();
        
        // Carregar dados iniciais
        loadData();
        
        updateStatus("Sistema inicializado com sucesso!");
    }
    
    private void setupTables() {
        // Tabela Marcas
        colMarcaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarcaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMarcaQtdModelos.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getListModelo().size()).asObject());
        
        // Tabela Veículos
        colVeiculoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVeiculoPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colVeiculoModelo.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getModelo() != null ? cellData.getValue().getModelo().getNome() : "N/A"));
        colVeiculoTipo.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getTipoCarro() != null ? cellData.getValue().getTipoCarro().getDescricao() : "N/A"));
        colVeiculoStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colVeiculoKm.setCellValueFactory(new PropertyValueFactory<>("km"));
        
        // Tabela Locações
        colLocacaoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLocacaoVeiculo.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVeiculo() != null ? cellData.getValue().getVeiculo().getPlaca() : "N/A"));
        colLocacaoDataRetirada.setCellValueFactory(new PropertyValueFactory<>("dataRetirada"));
        colLocacaoDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        colLocacaoValor.setCellValueFactory(new PropertyValueFactory<>("valorLocacao"));
        
        // Tabela Ocorrências
        colOcorrenciaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOcorrenciaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colOcorrenciaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colOcorrenciaLocacao.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleLongProperty(
                cellData.getValue().getLocacao() != null ? cellData.getValue().getLocacao().getId() : 0).asObject());
        
        // Configurar colunas da tabela de Clientes
        colClienteId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClienteCnh.setCellValueFactory(new PropertyValueFactory<>("cnh"));
        colClienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colClienteCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colClienteEmail.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getContato() != null ? cellData.getValue().getContato().getEmail() : ""));
        colClienteTelefone.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getContato() != null ? cellData.getValue().getContato().getTelefone() : ""));
    }
    
    private void setupComboBoxes() {
        // ComboBox Status Veículo
        cmbVeiculoStatus.setItems(FXCollections.observableArrayList(StatusVeiculo.values()));
        cmbVeiculoStatus.setValue(StatusVeiculo.DISPONIVEL);
        
        cmbNovoStatus.setItems(FXCollections.observableArrayList(StatusVeiculo.values()));
        
        // ComboBox Tipo de Carro
        cmbTipoCarro.setItems(FXCollections.observableArrayList(TipoCarro.values()));
        cmbLocacaoTipoCarro.setItems(FXCollections.observableArrayList(TipoCarro.values()));
        
        // Configurar conversores para exibição
        cmbMarcas.setConverter(new javafx.util.StringConverter<Marca>() {
            @Override
            public String toString(Marca marca) {
                return marca != null ? marca.getNome() : "";
            }
            @Override
            public Marca fromString(String string) {
                return null;
            }
        });
        
        cmbModelos.setConverter(new javafx.util.StringConverter<Modelo>() {
            @Override
            public String toString(Modelo modelo) {
                return modelo != null ? modelo.getNome() : "";
            }
            @Override
            public Modelo fromString(String string) {
                return null;
            }
        });
        
        cmbLocacaoVeiculo.setConverter(new javafx.util.StringConverter<Veiculo>() {
            @Override
            public String toString(Veiculo veiculo) {
                return veiculo != null ? veiculo.getPlaca() + " - " + 
                    (veiculo.getModelo() != null ? veiculo.getModelo().getNome() : "") : "";
            }
            @Override
            public Veiculo fromString(String string) {
                return null;
            }
        });
        
        cmbLocacoes.setConverter(new javafx.util.StringConverter<Locacao>() {
            @Override
            public String toString(Locacao locacao) {
                return locacao != null ? "Locação #" + locacao.getId() : "";
            }
            @Override
            public Locacao fromString(String string) {
                return null;
            }
        });
        
        cmbClientes.setConverter(new javafx.util.StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente != null ? cliente.getNome() + " (CPF: " + cliente.getCpf() + ")" : "";
            }
            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
        
        cmbLocacaoCliente.setConverter(new javafx.util.StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                return cliente != null ? cliente.getNome() + " (CPF: " + cliente.getCpf() + ")" : "";
            }
            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
    }
    
    private void loadData() {
        // Carregar Marcas
        List<Marca> marcas = marcaDAO.findAll();
        tableMarcas.setItems(FXCollections.observableArrayList(marcas));
        cmbMarcas.setItems(FXCollections.observableArrayList(marcas));
        
        // Carregar Modelos
        List<Modelo> modelos = modeloDAO.findAll();
        cmbModelos.setItems(FXCollections.observableArrayList(modelos));
        
        // Carregar Veículos
        List<Veiculo> veiculos = veiculoDAO.findAll();
        tableVeiculos.setItems(FXCollections.observableArrayList(veiculos));
        
        // Carregar Locações
        List<Locacao> locacoes = locacaoDAO.findAll();
        cmbLocacoes.setItems(FXCollections.observableArrayList(locacoes));
        
        // Carregar Ocorrências
        List<Ocorrencia> ocorrencias = ocorrenciaDAO.findAll();
        tableOcorrencias.setItems(FXCollections.observableArrayList(ocorrencias));
        
        // Carregar Clientes
        List<Cliente> clientes = clienteDAO.findAll();
        cmbClientes.setItems(FXCollections.observableArrayList(clientes));
        cmbLocacaoCliente.setItems(FXCollections.observableArrayList(clientes));
        tableClientes.setItems(FXCollections.observableArrayList(clientes));
    }
    
    @FXML
    private void handleInserirMarca() {
        try {
            String nome = txtMarcaNome.getText().trim();
            if (nome.isEmpty()) {
                showAlert("Erro", "Por favor, informe o nome da marca.");
                return;
            }
            
            Marca marca = new Marca(nome);
            marcaDAO.save(marca);
            
            txtMarcaNome.clear();
            loadData();
            updateStatus("Marca inserida com sucesso!");
            showAlert("Sucesso", "Marca inserida com sucesso!");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao inserir marca: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleInserirModelo() {
        try {
            Marca marca = cmbMarcas.getValue();
            String nome = txtModeloNome.getText().trim();
            String anoStr = txtModeloAno.getText().trim();
            String qtStr = txtModeloQt.getText().trim();
            
            if (marca == null || nome.isEmpty() || anoStr.isEmpty() || qtStr.isEmpty()) {
                showAlert("Erro", "Por favor, preencha todos os campos.");
                return;
            }
            
            int ano = Integer.parseInt(anoStr);
            int qt = Integer.parseInt(qtStr);
            
            LocalDate localDate = LocalDate.of(ano, 1, 1);
            java.util.Date data = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            Modelo modelo = new Modelo(nome, data, qt);
            marca.addModelo(modelo);
            
            modeloDAO.save(modelo);
            
            txtModeloNome.clear();
            txtModeloAno.clear();
            txtModeloQt.clear();
            loadData();
            updateStatus("Modelo inserido com sucesso!");
            showAlert("Sucesso", "Modelo inserido com sucesso!");
        } catch (NumberFormatException e) {
            showAlert("Erro", "Ano e Quantidade devem ser números válidos.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao inserir modelo: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleInserirVeiculo() {
        try {
            Modelo modelo = cmbModelos.getValue();
            TipoCarro tipoCarro = cmbTipoCarro.getValue();
            String placa = txtVeiculoPlaca.getText().trim();
            String chassi = txtVeiculoChassi.getText().trim();
            String renavam = txtVeiculoRenavam.getText().trim();
            String cor = txtVeiculoCor.getText().trim();
            String kmStr = txtVeiculoKm.getText().trim();
            StatusVeiculo status = cmbVeiculoStatus.getValue();
            
            if (modelo == null || tipoCarro == null || placa.isEmpty() || chassi.isEmpty() || 
                renavam.isEmpty() || cor.isEmpty() || kmStr.isEmpty() || status == null) {
                showAlert("Erro", "Por favor, preencha todos os campos.");
                return;
            }
            
            int km = Integer.parseInt(kmStr);
            
            Veiculo veiculo = new Veiculo();
            veiculo.setStatus(status);
            veiculo.setKm(km);
            veiculo.setPlaca(placa);
            veiculo.setChassi(chassi);
            veiculo.setRenavam(renavam);
            veiculo.setCor(cor);
            veiculo.setTipoCarro(tipoCarro);
            veiculo.setModelo(modelo);
            
            veiculoDAO.save(veiculo);
            
            txtVeiculoPlaca.clear();
            txtVeiculoChassi.clear();
            txtVeiculoRenavam.clear();
            txtVeiculoCor.clear();
            txtVeiculoKm.clear();
            loadData();
            updateStatus("Veículo inserido com sucesso!");
            showAlert("Sucesso", "Veículo inserido com sucesso!");
        } catch (NumberFormatException e) {
            showAlert("Erro", "KM deve ser um número válido.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao inserir veículo: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleAtualizarKm() {
        try {
            Veiculo veiculo = tableVeiculos.getSelectionModel().getSelectedItem();
            if (veiculo == null) {
                showAlert("Erro", "Por favor, selecione um veículo na tabela.");
                return;
            }
            
            String kmStr = txtNovoKm.getText().trim();
            if (kmStr.isEmpty()) {
                showAlert("Erro", "Por favor, informe o novo KM.");
                return;
            }
            
            int novoKm = Integer.parseInt(kmStr);
            
            if (novoKm < veiculo.getKm()) {
                showAlert("Aviso", "O novo KM é menor que o KM atual. Tem certeza?");
            }
            
            veiculo.setKm(novoKm);
            veiculoDAO.update(veiculo);
            
            txtNovoKm.clear();
            loadData();
            updateStatus("KM do veículo atualizado com sucesso!");
            showAlert("Sucesso", "KM atualizado de " + veiculo.getKm() + " para " + novoKm + " km!");
        } catch (NumberFormatException e) {
            showAlert("Erro", "KM deve ser um número válido.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao atualizar KM: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleAtualizarStatus() {
        try {
            Veiculo veiculo = tableVeiculos.getSelectionModel().getSelectedItem();
            if (veiculo == null) {
                showAlert("Erro", "Por favor, selecione um veículo na tabela.");
                return;
            }
            
            StatusVeiculo novoStatus = cmbNovoStatus.getValue();
            if (novoStatus == null) {
                showAlert("Erro", "Por favor, selecione um status.");
                return;
            }
            
            StatusVeiculo statusAnterior = veiculo.getStatus();
            veiculo.setStatus(novoStatus);
            veiculoDAO.update(veiculo);
            
            loadData();
            updateStatus("Status do veículo atualizado com sucesso!");
            showAlert("Sucesso", "Status atualizado de " + statusAnterior + " para " + novoStatus + "!");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao atualizar status: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleTipoCarroSelecionado() {
        TipoCarro tipo = cmbLocacaoTipoCarro.getValue();
        if (tipo != null) {
            lblPrecoDiaria.setText("R$ " + String.format("%.2f", tipo.getPrecoDiaria()));
            
            // Filtrar veículos disponíveis do tipo selecionado
            List<Veiculo> veiculos = veiculoDAO.findAll();
            List<Veiculo> veiculosDisponiveis = veiculos.stream()
                .filter(v -> v.getTipoCarro() == tipo && v.getStatus() == StatusVeiculo.DISPONIVEL)
                .collect(Collectors.toList());
            
            cmbLocacaoVeiculo.setItems(FXCollections.observableArrayList(veiculosDisponiveis));
            
            // Recalcular valor
            handleCalcularValorLocacao();
        }
    }
    
    @FXML
    private void handleCalcularValorLocacao() {
        TipoCarro tipo = cmbLocacaoTipoCarro.getValue();
        LocalDate dataRetirada = dpDataRetirada.getValue();
        LocalDate dataDevolucao = dpDataDevolucao.getValue();
        
        if (tipo != null && dataRetirada != null && dataDevolucao != null) {
            long dias = ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
            
            if (dias < 0) {
                lblDias.setText("0");
                lblValorTotal.setText("R$ 0,00");
                showAlert("Erro", "Data de devolução deve ser posterior à data de retirada!");
                return;
            }
            
            if (dias == 0) {
                dias = 1; // Mínimo 1 dia
            }
            
            float valorTotal = tipo.getPrecoDiaria() * dias;
            
            lblDias.setText(String.valueOf(dias));
            lblValorTotal.setText("R$ " + String.format("%.2f", valorTotal));
        }
    }
    
    @FXML
    private void handleCriarLocacao() {
        try {
            Cliente cliente = cmbLocacaoCliente.getValue();
            Veiculo veiculo = cmbLocacaoVeiculo.getValue();
            LocalDate dataRetirada = dpDataRetirada.getValue();
            LocalDate dataDevolucao = dpDataDevolucao.getValue();
            String valorStr = lblValorTotal.getText().replace("R$ ", "").replace(",", ".");
            
            if (cliente == null || veiculo == null || dataRetirada == null || dataDevolucao == null) {
                showAlert("Erro", "Por favor, preencha todos os campos (Cliente, Veículo e Datas).");
                return;
            }
            
            float valor = Float.parseFloat(valorStr);
            
            java.util.Date dataRet = Date.from(dataRetirada.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date dataDev = Date.from(dataDevolucao.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            Locacao locacao = new Locacao(dataRet, dataDev, valor);
            locacao.setVeiculo(veiculo);
            locacao.setCliente(cliente);
            
            locacaoDAO.save(locacao);
            
            veiculo.setStatus(StatusVeiculo.LOCADO);
            veiculoDAO.update(veiculo);
            
            cmbLocacaoCliente.setValue(null);
            cmbLocacaoTipoCarro.setValue(null);
            cmbLocacaoVeiculo.setValue(null);
            dpDataRetirada.setValue(null);
            dpDataDevolucao.setValue(null);
            lblPrecoDiaria.setText("R$ 0,00");
            lblDias.setText("0");
            lblValorTotal.setText("R$ 0,00");
            
            loadData();
            updateStatus("Locação criada com sucesso!");
            showAlert("Sucesso", "Locação criada com sucesso! Valor: R$ " + String.format("%.2f", valor));
        } catch (NumberFormatException e) {
            showAlert("Erro", "Valor inválido.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao criar locação: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleAdicionarOcorrencia() {
        try {
            Locacao locacao = cmbLocacoes.getValue();
            String descricao = txtOcorrenciaDescricao.getText().trim();
            String valorStr = txtOcorrenciaValor.getText().trim();
            
            if (locacao == null || descricao.isEmpty() || valorStr.isEmpty()) {
                showAlert("Erro", "Por favor, preencha todos os campos.");
                return;
            }
            
            float valor = Float.parseFloat(valorStr);
            
            Ocorrencia ocorrencia = new Ocorrencia(descricao, valor);
            locacao.addOcorrencia(ocorrencia);
            
            ocorrenciaDAO.save(ocorrencia);
            
            txtOcorrenciaDescricao.clear();
            txtOcorrenciaValor.clear();
            loadData();
            updateStatus("Ocorrência adicionada com sucesso!");
            showAlert("Sucesso", "Ocorrência adicionada com sucesso!");
        } catch (NumberFormatException e) {
            showAlert("Erro", "Valor deve ser um número válido.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao adicionar ocorrência: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleConsultarLocacoes() {
        try {
            Cliente cliente = cmbClientes.getValue();
            if (cliente == null) {
                showAlert("Erro", "Por favor, selecione um cliente.");
                return;
            }
            
            List<Locacao> locacoes = locacaoDAO.findByCliente(cliente);
            tableLocacoes.setItems(FXCollections.observableArrayList(locacoes));
            updateStatus("Locações consultadas: " + locacoes.size() + " encontrada(s).");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao consultar locações: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleExcluirOcorrencia() {
        try {
            Ocorrencia ocorrencia = tableOcorrencias.getSelectionModel().getSelectedItem();
            if (ocorrencia == null) {
                showAlert("Erro", "Por favor, selecione uma ocorrência na tabela.");
                return;
            }
            
            Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacao.setTitle("Confirmar Exclusão");
            confirmacao.setHeaderText("Deseja realmente excluir esta ocorrência?");
            confirmacao.setContentText("Esta ação não pode ser desfeita.");
            
            if (confirmacao.showAndWait().get() == ButtonType.OK) {
                // Usar deleteById em vez de delete para evitar problemas de detached entity
                ocorrenciaDAO.deleteById(ocorrencia.getId());
                loadData();
                updateStatus("Ocorrência excluída com sucesso!");
                showAlert("Sucesso", "Ocorrência excluída com sucesso!");
            }
        } catch (Exception e) {
            showAlert("Erro", "Erro ao excluir ocorrência: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleInserirCliente() {
        try {
            String cnh = txtClienteCnh.getText().trim();
            String nome = txtClienteNome.getText().trim();
            String cpf = txtClienteCpf.getText().trim();
            String login = txtClienteLogin.getText().trim();
            String senha = txtClienteSenha.getText().trim();
            String email = txtClienteEmail.getText().trim();
            String telefone = txtClienteTelefone.getText().trim();
            String celular = txtClienteCelular.getText().trim();
            String cep = txtClienteCep.getText().trim();
            String logradouro = txtClienteLogradouro.getText().trim();
            String complemento = txtClienteComplemento.getText().trim();
            String numero = txtClienteNumero.getText().trim();
            String referencia = txtClienteReferencia.getText().trim();
            
            if (cnh.isEmpty() || nome.isEmpty() || cpf.isEmpty() || login.isEmpty() || senha.isEmpty()) {
                showAlert("Erro", "Por favor, preencha os campos obrigatórios: CNH, Nome, CPF, Login e Senha.");
                return;
            }
            
            Endereco endereco = new Endereco();
            endereco.setCep(cep);
            endereco.setLogradouro(logradouro);
            endereco.setComplemento(complemento);
            endereco.setNumero(numero);
            endereco.setReferencia(referencia);
            
            Contato contato = new Contato();
            contato.setEmail(email);
            contato.setTelefone(telefone);
            contato.setCelular(celular);
            
            Cliente cliente = new Cliente();
            cliente.setCnh(cnh);
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setLogin(login);
            cliente.setSenha(senha);
            cliente.setEndereco(endereco);
            cliente.setContato(contato);
            
            clienteDAO.save(cliente);
            
            txtClienteCnh.clear();
            txtClienteNome.clear();
            txtClienteCpf.clear();
            txtClienteLogin.clear();
            txtClienteSenha.clear();
            txtClienteEmail.clear();
            txtClienteTelefone.clear();
            txtClienteCelular.clear();
            txtClienteCep.clear();
            txtClienteLogradouro.clear();
            txtClienteComplemento.clear();
            txtClienteNumero.clear();
            txtClienteReferencia.clear();
            
            loadData();
            updateStatus("Cliente cadastrado com sucesso!");
            showAlert("Sucesso", "Cliente cadastrado com sucesso!");
            
        } catch (Exception e) {
            showAlert("Erro", "Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleAlterarLocacao() {
        try {
            Locacao locacao = tableLocacoes.getSelectionModel().getSelectedItem();
            String diasStr = txtDiasAlterar.getText().trim();
            
            if (locacao == null) {
                showAlert("Erro", "Selecione uma locação na tabela.");
                return;
            }
            
            if (diasStr.isEmpty()) {
                showAlert("Erro", "Informe o número de dias (+3 ou -2).");
                return;
            }
            
            int dias = Integer.parseInt(diasStr.replace("+", ""));
            
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(locacao.getDataDevolucao());
            cal.add(java.util.Calendar.DAY_OF_MONTH, dias);
            
            if (cal.getTime().before(locacao.getDataRetirada())) {
                showAlert("Erro", "A data de devolução não pode ser anterior à data de retirada.");
                return;
            }
            
            locacao.setDataDevolucao(cal.getTime());
            
            long diffInMillies = Math.abs(locacao.getDataDevolucao().getTime() - locacao.getDataRetirada().getTime());
            long diffInDays = java.util.concurrent.TimeUnit.DAYS.convert(diffInMillies, java.util.concurrent.TimeUnit.MILLISECONDS);
            
            TipoCarro tipo = locacao.getVeiculo().getTipoCarro();
            float novoValor = tipo.getPrecoDiaria() * diffInDays;
            locacao.setValorLocacao(novoValor);
            
            locacaoDAO.update(locacao);
            
            txtDiasAlterar.clear();
            loadData();
            updateStatus("Locação alterada com sucesso!");
            showAlert("Sucesso", "Locação alterada! Novo valor: R$ " + String.format("%.2f", novoValor));
        } catch (NumberFormatException e) {
            showAlert("Erro", "Número de dias inválido.");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao alterar locação: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleExcluirLocacao() {
        try {
            Locacao locacao = tableLocacoes.getSelectionModel().getSelectedItem();
            
            if (locacao == null) {
                showAlert("Erro", "Selecione uma locação na tabela.");
                return;
            }
            
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmar Exclusão");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Deseja realmente excluir esta locação?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                Long locacaoId = locacao.getId();
                Long veiculoId = locacao.getVeiculo().getId();
                
                Veiculo veiculo = veiculoDAO.findById(veiculoId);
                veiculo.setStatus(StatusVeiculo.DISPONIVEL);
                veiculoDAO.update(veiculo);
                
                Session session = null;
                Transaction transaction = null;
                try {
                    session = HibernateUtil.getSessionFactory().openSession();
                    transaction = session.beginTransaction();
                    Locacao loc = session.get(Locacao.class, locacaoId);
                    if (loc != null) {
                        session.remove(loc);
                    }
                    transaction.commit();
                } catch (Exception ex) {
                    if (transaction != null) transaction.rollback();
                    throw ex;
                } finally {
                    if (session != null && session.isOpen()) session.close();
                }
                
                loadData();
                updateStatus("Locação excluída com sucesso!");
                showAlert("Sucesso", "Locação excluída com sucesso!");
            }
        } catch (Exception e) {
            showAlert("Erro", "Erro ao excluir locação: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleCarregarCliente() {
        try {
            Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
            
            if (cliente == null) {
                showAlert("Erro", "Selecione um cliente na tabela.");
                return;
            }
            
            txtClienteCnh.setText(cliente.getCnh());
            txtClienteNome.setText(cliente.getNome());
            txtClienteCpf.setText(cliente.getCpf());
            txtClienteLogin.setText(cliente.getLogin());
            txtClienteSenha.setText(cliente.getSenha());
            
            if (cliente.getContato() != null) {
                txtClienteEmail.setText(cliente.getContato().getEmail() != null ? cliente.getContato().getEmail() : "");
                txtClienteTelefone.setText(cliente.getContato().getTelefone() != null ? cliente.getContato().getTelefone() : "");
                txtClienteCelular.setText(cliente.getContato().getCelular() != null ? cliente.getContato().getCelular() : "");
            }
            
            if (cliente.getEndereco() != null) {
                txtClienteCep.setText(cliente.getEndereco().getCep() != null ? cliente.getEndereco().getCep() : "");
                txtClienteLogradouro.setText(cliente.getEndereco().getLogradouro() != null ? cliente.getEndereco().getLogradouro() : "");
                txtClienteComplemento.setText(cliente.getEndereco().getComplemento() != null ? cliente.getEndereco().getComplemento() : "");
                txtClienteNumero.setText(cliente.getEndereco().getNumero() != null ? cliente.getEndereco().getNumero() : "");
                txtClienteReferencia.setText(cliente.getEndereco().getReferencia() != null ? cliente.getEndereco().getReferencia() : "");
            }
            
            updateStatus("Dados do cliente carregados!");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao carregar cliente: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleAtualizarCliente() {
        try {
            Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
            
            if (cliente == null) {
                showAlert("Erro", "Selecione um cliente na tabela.");
                return;
            }
            
            String cnh = txtClienteCnh.getText().trim();
            String nome = txtClienteNome.getText().trim();
            String cpf = txtClienteCpf.getText().trim();
            String login = txtClienteLogin.getText().trim();
            String senha = txtClienteSenha.getText().trim();
            
            if (cnh.isEmpty() || nome.isEmpty() || cpf.isEmpty() || login.isEmpty() || senha.isEmpty()) {
                showAlert("Erro", "Preencha os campos obrigatórios: CNH, Nome, CPF, Login e Senha.");
                return;
            }
            
            cliente.setCnh(cnh);
            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setLogin(login);
            cliente.setSenha(senha);
            
            if (cliente.getContato() != null) {
                cliente.getContato().setEmail(txtClienteEmail.getText().trim());
                cliente.getContato().setTelefone(txtClienteTelefone.getText().trim());
                cliente.getContato().setCelular(txtClienteCelular.getText().trim());
            }
            
            if (cliente.getEndereco() != null) {
                cliente.getEndereco().setCep(txtClienteCep.getText().trim());
                cliente.getEndereco().setLogradouro(txtClienteLogradouro.getText().trim());
                cliente.getEndereco().setComplemento(txtClienteComplemento.getText().trim());
                cliente.getEndereco().setNumero(txtClienteNumero.getText().trim());
                cliente.getEndereco().setReferencia(txtClienteReferencia.getText().trim());
            }
            
            clienteDAO.update(cliente);
            
            loadData();
            updateStatus("Cliente atualizado com sucesso!");
            showAlert("Sucesso", "Cliente atualizado com sucesso!");
        } catch (Exception e) {
            showAlert("Erro", "Erro ao atualizar cliente: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleExcluirCliente() {
        try {
            Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
            
            if (cliente == null) {
                showAlert("Erro", "Selecione um cliente na tabela.");
                return;
            }
            
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirmar Exclusão");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Deseja realmente excluir este cliente?");
            
            if (confirmAlert.showAndWait().get() == ButtonType.OK) {
                clienteDAO.delete(cliente);
                
                txtClienteCnh.clear();
                txtClienteNome.clear();
                txtClienteCpf.clear();
                txtClienteLogin.clear();
                txtClienteSenha.clear();
                txtClienteEmail.clear();
                txtClienteTelefone.clear();
                txtClienteCelular.clear();
                txtClienteCep.clear();
                txtClienteLogradouro.clear();
                txtClienteComplemento.clear();
                txtClienteNumero.clear();
                txtClienteReferencia.clear();
                
                loadData();
                updateStatus("Cliente excluído com sucesso!");
                showAlert("Sucesso", "Cliente excluído com sucesso!");
            }
        } catch (Exception e) {
            showAlert("Erro", "Erro ao excluir cliente: " + e.getMessage());
        }
    }
    
    private void updateStatus(String message) {
        lblStatus.setText(message);
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
