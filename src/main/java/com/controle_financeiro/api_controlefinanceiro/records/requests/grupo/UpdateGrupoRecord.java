package com.controle_financeiro.api_controlefinanceiro.records.requests.grupo;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UpdateGrupoRecord(
        @NotEmpty(message = "Id não pode ser vazio ou nulo") UUID id,
        @NotEmpty(message = "Nome não pode ser vazio ou nulo") String nome,
        @NotEmpty(message = "Descricao não pode ser vazio ou nulo")String descricao) {
}
