package com.controle_financeiro.api_controlefinanceiro.records.requests.meta;

import com.controle_financeiro.api_controlefinanceiro.enums.TipoLancamentoEnum;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateMetaRecord(
        @NotEmpty(message = "idMeta não pode ser vazio ou nulo") UUID idMeta,
        @NotEmpty(message = "tipo não pode ser vazio ou nulo") TipoLancamentoEnum tipo,
        @NotEmpty(message = "valor não pode ser vazio ou nulo") BigDecimal valor,
        @NotEmpty(message = "idGrupo não pode ser vazio ou nulo") UUID idGrupo)  {
}
