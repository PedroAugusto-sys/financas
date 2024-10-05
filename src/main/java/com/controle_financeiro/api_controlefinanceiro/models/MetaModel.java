package com.controle_financeiro.api_controlefinanceiro.models;

import com.controle_financeiro.api_controlefinanceiro.enums.TipoLancamentoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_meta")
public class MetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoModel grupo;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_lancamento", nullable = false)
    private TipoLancamentoEnum tipoLancamento;

    public MetaModel(UUID id, GrupoModel grupo, BigDecimal valor, TipoLancamentoEnum tipoLancamento) {
        this.id = id;
        this.grupo = grupo;
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
    }

    public MetaModel() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public GrupoModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoModel grupo) {
        this.grupo = grupo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoLancamentoEnum getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(TipoLancamentoEnum tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }
}
