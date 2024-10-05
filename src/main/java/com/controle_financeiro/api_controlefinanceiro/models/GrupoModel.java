package com.controle_financeiro.api_controlefinanceiro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_grupo")
public class GrupoModel {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID idGrupo;

    @Column(name = "nome", unique = true, nullable = false)
    private String nomeGrupo;

    @Column(name = "descricao", nullable = false)
    private String descricaoGrupo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dono_id", nullable = false)
    private PessoaModel donoGrupo;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<LancamentoModel> lancamentos;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<MetaModel> metas;

    public GrupoModel() {
    }

    public GrupoModel(UUID idGrupo, String nomeGrupo, String descricaoGrupo, PessoaModel donoGrupo, List<LancamentoModel> lancamentos, List<MetaModel> metas) {
        this.idGrupo = idGrupo;
        this.nomeGrupo = nomeGrupo;
        this.descricaoGrupo = descricaoGrupo;
        this.donoGrupo = donoGrupo;
        this.lancamentos = lancamentos;
        this.metas = metas;
    }

    public UUID getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(UUID idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }

    public PessoaModel getDonoGrupo() {
        return donoGrupo;
    }

    public void setDonoGrupo(PessoaModel donoGrupo) {
        this.donoGrupo = donoGrupo;
    }

    public List<LancamentoModel> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<LancamentoModel> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<MetaModel> getMetas() {
        return metas;
    }

    public void setMetas(List<MetaModel> metas) {
        this.metas = metas;
    }
}
