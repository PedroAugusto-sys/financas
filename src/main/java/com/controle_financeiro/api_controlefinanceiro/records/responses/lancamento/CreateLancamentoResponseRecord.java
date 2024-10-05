package com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento;

import com.controle_financeiro.api_controlefinanceiro.models.LancamentoModel;

public record CreateLancamentoResponseRecord(String response, LancamentoModel lancamento) {
}
