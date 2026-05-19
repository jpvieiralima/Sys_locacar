package com.locacao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusVeiculo status;
    
    @Column(name = "km")
    private Integer km;
    
    @Column(name = "placa", unique = true)
    private String placa;
    
    @Column(name = "chassi", unique = true)
    private String chassi;
    
    @Column(name = "renavam", unique = true)
    private String renavam;
    
    @Column(name = "cor")
    private String cor;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_carro")
    private TipoCarro tipoCarro;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo_id", nullable = true)
    private Modelo modelo;
    
    // Construtores
    public Veiculo() {
    }
    
    public Veiculo(StatusVeiculo status, Integer km, String placa, String chassi, String renavam, String cor, TipoCarro tipoCarro) {
        this.status = status;
        this.km = km;
        this.placa = placa;
        this.chassi = chassi;
        this.renavam = renavam;
        this.cor = cor;
        this.tipoCarro = tipoCarro;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public StatusVeiculo getStatus() {
        return status;
    }
    
    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }
    
    public Integer getKm() {
        return km;
    }
    
    public void setKm(Integer km) {
        this.km = km;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    public String getChassi() {
        return chassi;
    }
    
    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
    
    public String getRenavam() {
        return renavam;
    }
    
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }
    
    public String getCor() {
        return cor;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public Modelo getModelo() {
        return modelo;
    }
    
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
    
    public TipoCarro getTipoCarro() {
        return tipoCarro;
    }
    
    public void setTipoCarro(TipoCarro tipoCarro) {
        this.tipoCarro = tipoCarro;
    }
    
    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", status=" + status +
                ", modelo=" + (modelo != null ? modelo.getNome() : "null") +
                '}';
    }
}
