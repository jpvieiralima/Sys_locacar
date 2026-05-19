package com.locacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario extends Usuario {
    
    @Column(name = "cargo")
    private String cargo;
    
    // Construtores
    public Funcionario() {
        super();
    }
    
    public Funcionario(String nome, String cpf, String login, String senha, Contato contato, Endereco endereco, String cargo) {
        super(nome, cpf, login, senha, contato, endereco);
        this.cargo = cargo;
    }
    
    // Getters e Setters
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
