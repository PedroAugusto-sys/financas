package com.controle_financeiro.api_controlefinanceiro.records.requests.pessoa;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePessoaRecord(@NotEmpty(message = "Nome não pode ser vazio ou nulo") String nome,
                                 @NotEmpty(message = "CPF não pode ser vazio ou nulo") String cpf,
                                 @NotEmpty(message = "Email não pode ser vazio ou nulo")String email,
                                 @NotEmpty(message = "Telefone não pode ser vazio ou nulo")String telefone) {
}
