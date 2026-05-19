package com.locacao.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "manutencao")
public class Manutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data")
    private Date data;
    
    @Column(name = "custo")
    private Float custo;
    
    // Construtores
    public Manutencao() {
    }
    
    public Manutencao(String descricao, Date data, Float custo) {
        this.descricao = descricao;
        this.data = data;
        this.custo = custo;
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
    
    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public Float getCusto() {
        return custo;
    }
    
    public void setCusto(Float custo) {
        this.custo = custo;
    }
    
    @Override
    public String toString() {
        return "Manutencao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", custo=" + custo +
                '}';
    }
}
