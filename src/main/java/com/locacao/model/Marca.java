package com.locacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marca")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modelo> listModelo = new ArrayList<>();
    
    // Construtores
    public Marca() {
    }
    
    public Marca(String nome) {
        this.nome = nome;
    }
    
    // Métodos auxiliares para gerenciar relacionamento bidirecional
    public void addModelo(Modelo modelo) {
        listModelo.add(modelo);
        modelo.setMarca(this);
    }
    
    public void removeModelo(Modelo modelo) {
        listModelo.remove(modelo);
        modelo.setMarca(null);
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
    
    public List<Modelo> getListModelo() {
        return listModelo;
    }
    
    public void setListModelo(List<Modelo> listModelo) {
        this.listModelo = listModelo;
    }
    
    @Override
    public String toString() {
        return "Marca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", qtdModelos=" + listModelo.size() +
                '}';
    }
}
