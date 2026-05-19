package com.locacao.model;

public enum TipoCarro {
    PEQUENO("Pequeno", 75.0f),
    MEDIO("Médio", 110.0f),
    SUV("SUV", 175.0f),
    VAN("Van", 250.0f),
    PICAPE("Picape", 190.0f),
    LUXO("Luxo", 275.0f);
    
    private final String descricao;
    private final Float precoDiaria;
    
    TipoCarro(String descricao, Float precoDiaria) {
        this.descricao = descricao;
        this.precoDiaria = precoDiaria;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public Float getPrecoDiaria() {
        return precoDiaria;
    }
    
    @Override
    public String toString() {
        return descricao + " (R$ " + String.format("%.2f", precoDiaria) + "/dia)";
    }
}
