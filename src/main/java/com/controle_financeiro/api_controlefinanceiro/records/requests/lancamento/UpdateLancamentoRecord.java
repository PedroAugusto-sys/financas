package com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento;

import com.controle_financeiro.api_controlefinanceiro.enums.CategoriaLancamentoEnum;
import com.controle_financeiro.api_controlefinanceiro.enums.TipoLancamentoEnum;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UpdateLancamentoRecord(
        @NotEmpty(message = "Nome não pode ser vazio ou nulo") UUID idLancamento,
        @NotEmpty(message = "Nome não pode ser vazio ou nulo") String nome,
        @NotEmpty(message = "Descricao não pode ser vazio ou nulo") String descricao,
        @NotEmpty(message = "Data não pode ser vazio ou nulo") OffsetDateTime data,
        @NotEmpty(message = "Tipo não pode ser vazio ou nulo") TipoLancamentoEnum tipo,
        @NotEmpty(message = "Valor não pode ser vazio ou nulo") BigDecimal valor,
        @NotEmpty(message = "Categoria não pode ser vazio ou nulo") CategoriaLancamentoEnum categoria,
        @NotEmpty(message = "idGrupo não pode ser vazio ou nulo") UUID idGrupo) {
}
