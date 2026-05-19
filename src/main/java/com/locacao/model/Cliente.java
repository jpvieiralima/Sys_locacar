package com.locacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {
    
    @Column(name = "cnh")
    private String cnh;
    
    // Construtores
    public Cliente() {
        super();
    }
    
    public Cliente(String nome, String cpf, String login, String senha, Contato contato, Endereco endereco, String cnh) {
        super(nome, cpf, login, senha, contato, endereco);
        this.cnh = cnh;
    }
    
    // Getters e Setters
    public String getCnh() {
        return cnh;
    }
    
    public void setCnh(String cnh) {
        this.cnh = cnh;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", cnh='" + cnh + '\'' +
                '}';
    }
}
