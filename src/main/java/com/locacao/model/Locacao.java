package com.locacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "locacao")
public class Locacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_retirada")
    private Date dataRetirada;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_devolucao")
    private Date dataDevolucao;
    
    @Column(name = "valor_locacao")
    private Float valorLocacao;
    
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ocorrencia> listaOcorrencias = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "contrato_locacao_id")
    private ContratoLocacao contratoLocacao;
    
    // Construtores
    public Locacao() {
    }
    
    public Locacao(Date dataRetirada, Date dataDevolucao, Float valorLocacao) {
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valorLocacao = valorLocacao;
    }
    
    // Métodos auxiliares para gerenciar relacionamento bidirecional
    public void addOcorrencia(Ocorrencia ocorrencia) {
        listaOcorrencias.add(ocorrencia);
        ocorrencia.setLocacao(this);
    }
    
    public void removeOcorrencia(Ocorrencia ocorrencia) {
        listaOcorrencias.remove(ocorrencia);
        ocorrencia.setLocacao(null);
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDataRetirada() {
        return dataRetirada;
    }
    
    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }
    
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public Float getValorLocacao() {
        return valorLocacao;
    }
    
    public void setValorLocacao(Float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    public List<Ocorrencia> getListaOcorrencias() {
        return listaOcorrencias;
    }
    
    public void setListaOcorrencias(List<Ocorrencia> listaOcorrencias) {
        this.listaOcorrencias = listaOcorrencias;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public ContratoLocacao getContratoLocacao() {
        return contratoLocacao;
    }
    
    public void setContratoLocacao(ContratoLocacao contratoLocacao) {
        this.contratoLocacao = contratoLocacao;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", dataRetirada=" + dataRetirada +
                ", dataDevolucao=" + dataDevolucao +
                ", valorLocacao=" + valorLocacao +
                ", veiculo=" + (veiculo != null ? veiculo.getPlaca() : "null") +
                '}';
    }
}
