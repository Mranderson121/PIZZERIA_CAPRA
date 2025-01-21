package com.pizzeria.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ImpastoDTO {
    private int idImpasto;
    private String nome;
    
    
    public ImpastoDTO() {
    }
    
    
    public ImpastoDTO(int idImpasto, String nome) {
        this.idImpasto = idImpasto;
        this.nome = nome;
    }

    public int getIdImpasto() {
        return idImpasto;
    }

    public void setIdImpasto(int idImpasto) {
        this.idImpasto = idImpasto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}