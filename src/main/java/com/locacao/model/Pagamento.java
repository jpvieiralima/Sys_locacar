package com.locacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class Pagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pagamento")
    private TipoPagamento tipoPagamento;
    
    @Column(name = "valor_total")
    private Float valorTotal;
    
    // Construtores
    public Pagamento() {
    }
    
    public Pagamento(TipoPagamento tipoPagamento, Float valorTotal) {
        this.tipoPagamento = tipoPagamento;
        this.valorTotal = valorTotal;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
    
    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    
    public Float getValorTotal() {
        return valorTotal;
    }
    
    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", tipoPagamento=" + tipoPagamento +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
