package com.locacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contrato_locacao")
public class ContratoLocacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_contrato")
    private Date dataContrato;
    
    @Column(name = "valor_caucao")
    private Float valorCaucao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusContrato status;
    
    @OneToMany(mappedBy = "contratoLocacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Locacao> listaLocacao = new ArrayList<>();
    
    @Column(name = "valor_total")
    private Float valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    
    // Construtores
    public ContratoLocacao() {
    }
    
    public ContratoLocacao(Date dataContrato, Float valorCaucao, StatusContrato status, Float valorTotal) {
        this.dataContrato = dataContrato;
        this.valorCaucao = valorCaucao;
        this.status = status;
        this.valorTotal = valorTotal;
    }
    
    // Métodos auxiliares para gerenciar relacionamento bidirecional
    public void addLocacao(Locacao locacao) {
        listaLocacao.add(locacao);
        locacao.setContratoLocacao(this);
    }
    
    public void removeLocacao(Locacao locacao) {
        listaLocacao.remove(locacao);
        locacao.setContratoLocacao(null);
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDataContrato() {
        return dataContrato;
    }
    
    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }
    
    public Float getValorCaucao() {
        return valorCaucao;
    }
    
    public void setValorCaucao(Float valorCaucao) {
        this.valorCaucao = valorCaucao;
    }
    
    public StatusContrato getStatus() {
        return status;
    }
    
    public void setStatus(StatusContrato status) {
        this.status = status;
    }
    
    public List<Locacao> getListaLocacao() {
        return listaLocacao;
    }
    
    public void setListaLocacao(List<Locacao> listaLocacao) {
        this.listaLocacao = listaLocacao;
    }
    
    public Float getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return "ContratoLocacao{" +
                "id=" + id +
                ", dataContrato=" + dataContrato +
                ", status=" + status +
                ", valorTotal=" + valorTotal +
                ", cliente=" + (cliente != null ? cliente.getNome() : "null") +
                '}';
    }
}
