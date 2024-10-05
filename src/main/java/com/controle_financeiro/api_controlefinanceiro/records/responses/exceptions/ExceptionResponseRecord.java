package com.controle_financeiro.api_controlefinanceiro.records.responses.exceptions;

import jakarta.validation.constraints.NotBlank;

public record ExceptionResponseRecord(
        @NotBlank String error
) {
}
