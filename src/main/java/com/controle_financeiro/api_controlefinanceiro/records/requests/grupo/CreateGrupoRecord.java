package com.controle_financeiro.api_controlefinanceiro.records.requests.grupo;

import jakarta.validation.constraints.NotEmpty;

public record CreateGrupoRecord(
        @NotEmpty(message = "Nome não pode ser vazio ou nulo")String nome,
        @NotEmpty(message = "Descricao não pode ser vazio ou nulo")String descricao) {
}
