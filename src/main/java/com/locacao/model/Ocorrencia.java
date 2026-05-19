package com.locacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ocorrencia")
public class Ocorrencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "valor")
    private Float valor;
    
    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;
    
    // Construtores
    public Ocorrencia() {
    }
    
    public Ocorrencia(String descricao, Float valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Float getValor() {
        return valor;
    }
    
    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    public Locacao getLocacao() {
        return locacao;
    }
    
    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }
    
    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
