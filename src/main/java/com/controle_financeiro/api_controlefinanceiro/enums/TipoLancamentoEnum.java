package com.controle_financeiro.api_controlefinanceiro.enums;

public enum TipoLancamentoEnum {
    RECEITA("Receita"),
    DESPESA("Despesa"),
    INVESTIMENTO("Investimento"),
    TRANSFERENCIA("Transferência"),
    OUTROS("Outros");

    private final String descricao;

    TipoLancamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
