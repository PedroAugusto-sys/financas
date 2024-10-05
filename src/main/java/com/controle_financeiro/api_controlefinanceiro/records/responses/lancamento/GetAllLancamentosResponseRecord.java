package com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento;

import com.controle_financeiro.api_controlefinanceiro.models.LancamentoModel;

import java.util.List;

public record GetAllLancamentosResponseRecord(List<LancamentoModel> lancamentos) {
}
