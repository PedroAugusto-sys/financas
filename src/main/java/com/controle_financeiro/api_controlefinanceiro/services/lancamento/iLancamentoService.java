package com.controle_financeiro.api_controlefinanceiro.services.lancamento;

import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.CreateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.UpdateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.CreateLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.DeleteLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.GetAllLancamentosResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.UpdateLancamentoResponseRecord;

import java.util.UUID;

public interface iLancamentoService {
    public UpdateLancamentoResponseRecord updateLancamento(UpdateLancamentoRecord updateLancamentoRecord) throws Exception;
    public GetAllLancamentosResponseRecord getAllLancamentos(UUID groupId) throws Exception;
    public DeleteLancamentoResponseRecord deleteLancamento(UUID idLancamento) throws Exception;
    public CreateLancamentoResponseRecord createLancamento(CreateLancamentoRecord createLancamentoRecord) throws Exception;
}
