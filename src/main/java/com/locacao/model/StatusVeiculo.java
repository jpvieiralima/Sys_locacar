package com.locacao.model;

public enum StatusVeiculo {
    DISPONIVEL("Disponível"),
    LOCADO("Locado"),
    MANUTENCAO("Em Manutenção"),
    INDISPONIVEL("Indisponível");
    
    private final String descricao;
    
    StatusVeiculo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
