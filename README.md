================================================================================
SYS_LOCACAR - SISTEMA DE LOCACAO DE VEICULOS
================================================================================

REQUISITOS:
- Java 17 ou superior
- PostgreSQL 12 ou superior
- IntelliJ IDEA (recomendado)
- Maven (incluido no IntelliJ)

================================================================================
PASSO A PASSO PARA EXECUCAO:
================================================================================

1. CONFIGURAR BANCO DE DADOS
   - Abra o PostgreSQL (pgAdmin ou terminal)
   - Execute o comando:
     CREATE DATABASE locacao_veiculos;

2. CONFIGURAR SENHA DO BANCO
   - Abra o arquivo: src/main/resources/hibernate.properties
   - Localize a linha: hibernate.connection.password=postgres
   - Substitua "postgres" pela sua senha do PostgreSQL
   - Salve o arquivo

3. ABRIR PROJETO NO INTELLIJ
   - Abra o IntelliJ IDEA
   - File -> Open
   - Selecione a pasta do projeto
   - Aguarde o Maven baixar as dependencias

4. EXECUTAR O PROJETO
   - Localize o arquivo: src/main/java/com/locacao/Main.java
   - Clique com botao direito -> Run 'Main.main()'
   - A interface grafica sera aberta

================================================================================
FUNCIONALIDADES DO SISTEMA:
================================================================================

ABA MARCAS E MODELOS:
- Inserir marca de veiculo
- Inserir modelo associado a uma marca

ABA VEICULOS:
- Cadastrar veiculo com tipo (Pequeno, Medio, SUV, Van, Picape, Luxo)
- Atualizar KM do veiculo
- Atualizar status do veiculo (Disponivel, Locado, Manutencao)

ABA LOCACOES:
- Selecionar cliente
- Selecionar tipo de carro (calculo automatico do preco)
- Criar locacao
- Alterar locacao (adicionar ou diminuir dias: +3 ou -2)
- Excluir locacao

ABA OCORRENCIAS:
- Adicionar ocorrencia a uma locacao
- Excluir ocorrencia

ABA CLIENTES:
- Cadastrar cliente com dados completos
- Carregar dados de cliente para edicao
- Atualizar cliente
- Excluir cliente

================================================================================
PRECOS POR TIPO DE CARRO:
================================================================================

Pequeno: R$ 75,00/dia
Medio:   R$ 110,00/dia
SUV:     R$ 175,00/dia
Van:     R$ 250,00/dia
Picape:  R$ 190,00/dia
Luxo:    R$ 275,00/dia

================================================================================
SOLUCAO DE PROBLEMAS:
================================================================================

ERRO: "database locacao_veiculos does not exist"
SOLUCAO: Crie o banco de dados (Passo 1)

ERRO: "password authentication failed"
SOLUCAO: Configure a senha correta no hibernate.properties (Passo 2)

ERRO: "Module javafx.controls not found"
SOLUCAO: Execute via Maven: mvn javafx:run
         Ou deixe o campo VM options vazio
         (EM CASO DE NÃO FUNCIONAMENTO DO JAVAFX COM OS PASSOS ANTERIORES UTILIZAR NAS VM OPTIONS:
         --module-path "C:\Users\"SEU USUARIO"\.m2\repository\org\openjfx\javafx-controls\21.0.1;C:\Users\"SEU USUARIO"\.m2\repository\org\openjfx\javafx-fxml\21.0.1;C:\Users\"SEU USUARIO"\.m2\repository\org\openjfx\javafx-graphics\21.0.1;C:\Users\"SEU USUARIO"\.m2\repository\org\openjfx\javafx-base\21.0.1" --add-modules javafx.controls,javafx.fxml)


ERRO: Tela nao abre
SOLUCAO: Verifique se o PostgreSQL esta rodando
         Verifique a senha em hibernate.properties

================================================================================
OBSERVACOES IMPORTANTES:
================================================================================

- O Hibernate cria as tabelas automaticamente na primeira execucao
- Cadastre pelo menos um cliente antes de criar uma locacao
- Cadastre pelo menos uma marca e modelo antes de inserir veiculo
- Ao excluir uma locacao, o veiculo volta para status DISPONIVEL
- Ao alterar dias de locacao, o valor e recalculado automaticamente

================================================================================
ESTRUTURA DO PROJETO:
================================================================================

src/main/java/com/locacao/
  ├── model/          - Classes de entidade (Veiculo, Cliente, etc)
  ├── dao/            - Classes de acesso ao banco de dados
  ├── controller/     - Controlador da interface JavaFX
  ├── util/           - Utilitarios (HibernateUtil)
  └── Main.java       - Classe principal para executar

src/main/resources/
  ├── fxml/           - Arquivo de interface (MainView.fxml)
  └── hibernate.properties - Configuracao do banco de dados

================================================================================
FIM
================================================================================
