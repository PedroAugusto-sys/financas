package com.controle_financeiro.api_controlefinanceiro.models;

import com.controle_financeiro.api_controlefinanceiro.enums.CategoriaLancamentoEnum;
import com.controle_financeiro.api_controlefinanceiro.enums.TipoLancamentoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_lancamento")
public class LancamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID idLancamento;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime data;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_lancamento", nullable = false)
    private TipoLancamentoEnum tipoLancamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_lancamento", nullable = false)
    private CategoriaLancamentoEnum categoriaLancamento;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GrupoModel grupo;

    public LancamentoModel() {
    }

    public LancamentoModel(UUID idLancamento, String nome, String descricao, OffsetDateTime data, BigDecimal valor, TipoLancamentoEnum tipoLancamento, CategoriaLancamentoEnum categoriaLancamento, GrupoModel grupo) {
        this.idLancamento = idLancamento;
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipoLancamento = tipoLancamento;
        this.categoriaLancamento = categoriaLancamento;
        this.grupo = grupo;
    }

    public UUID getIdLancamento() {
        return idLancamento;
    }

    public void setIdLancamento(UUID idLancamento) {
        this.idLancamento = idLancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getData() {
        return data;
    }

    public void setData(OffsetDateTime data) {
        this.data = data;
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

    public CategoriaLancamentoEnum getCategoriaLancamento() {
        return categoriaLancamento;
    }

    public void setCategoriaLancamento(CategoriaLancamentoEnum categoriaLancamento) {
        this.categoriaLancamento = categoriaLancamento;
    }

    public GrupoModel getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoModel grupo) {
        this.grupo = grupo;
    }
}
