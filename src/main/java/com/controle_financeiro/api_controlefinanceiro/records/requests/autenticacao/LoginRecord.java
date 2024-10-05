package com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao;

import jakarta.validation.constraints.NotEmpty;

public record LoginRecord(@NotEmpty(message = "CPF não pode ser vazio ou nulo") String cpf,
                          @NotEmpty(message = "Email não pode ser vazio ou nulo")String email
) {
}
