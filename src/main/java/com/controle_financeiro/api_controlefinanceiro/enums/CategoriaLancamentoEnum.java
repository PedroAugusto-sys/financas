package com.controle_financeiro.api_controlefinanceiro.enums;

public enum CategoriaLancamentoEnum {
    ALIMENTACAO("Alimentação"),
    TRANSPORTE("Transporte"),
    SAUDE("Saúde"),
    LAZER("Lazer"),
    EDUCACAO("Educação"),
    MORADIA("Moradia"),
    DESPESAS_VARIAVEIS("Despesas Variáveis"),
    SALARIO("Salário"),
    INVESTIMENTOS("Investimentos"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaLancamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
