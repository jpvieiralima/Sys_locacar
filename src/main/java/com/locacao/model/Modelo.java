package com.locacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "modelo")
public class Modelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ano")
    private Date ano;
    
    @Column(name = "qt_modelo")
    private Integer qtModelo;
    
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
    
    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Veiculo> listVeiculo = new ArrayList<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "modelo_categoria",
        joinColumns = @JoinColumn(name = "modelo_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();
    
    // Construtores
    public Modelo() {
    }
    
    public Modelo(String nome, Date ano, Integer qtModelo) {
        this.nome = nome;
        this.ano = ano;
        this.qtModelo = qtModelo;
    }
    
    // Métodos auxiliares para gerenciar relacionamento bidirecional
    public void addVeiculo(Veiculo veiculo) {
        listVeiculo.add(veiculo);
        veiculo.setModelo(this);
    }
    
    public void removeVeiculo(Veiculo veiculo) {
        listVeiculo.remove(veiculo);
        veiculo.setModelo(null);
    }
    
    public void addCategoria(Categoria categoria) {
        categorias.add(categoria);
        categoria.getListaModelos().add(this);
    }
    
    public void removeCategoria(Categoria categoria) {
        categorias.remove(categoria);
        categoria.getListaModelos().remove(this);
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
    
    public Date getAno() {
        return ano;
    }
    
    public void setAno(Date ano) {
        this.ano = ano;
    }
    
    public Integer getQtModelo() {
        return qtModelo;
    }
    
    public void setQtModelo(Integer qtModelo) {
        this.qtModelo = qtModelo;
    }
    
    public Marca getMarca() {
        return marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    public List<Veiculo> getListVeiculo() {
        return listVeiculo;
    }
    
    public void setListVeiculo(List<Veiculo> listVeiculo) {
        this.listVeiculo = listVeiculo;
    }
    
    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    @Override
    public String toString() {
        return "Modelo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ano=" + ano +
                ", qtModelo=" + qtModelo +
                ", marca=" + (marca != null ? marca.getNome() : "null") +
                '}';
    }
}
