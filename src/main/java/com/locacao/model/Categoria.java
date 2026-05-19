package com.locacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "valor_locacao")
    private Float valorLocacao;
    
    @ManyToMany(mappedBy = "categorias")
    private List<Modelo> listaModelos = new ArrayList<>();
    
    // Construtores
    public Categoria() {
    }
    
    public Categoria(String nome, Float valorLocacao) {
        this.nome = nome;
        this.valorLocacao = valorLocacao;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Float getValorLocacao() {
        return valorLocacao;
    }
    
    public void setValorLocacao(Float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    
    public List<Modelo> getListaModelos() {
        return listaModelos;
    }
    
    public void setListaModelos(List<Modelo> listaModelos) {
        this.listaModelos = listaModelos;
    }
    
    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorLocacao=" + valorLocacao +
                '}';
    }
}
