package com.controle_financeiro.api_controlefinanceiro.services.relatorios;

import com.controle_financeiro.api_controlefinanceiro.records.responses.relatorios.GetSaldoPerGrupoResponseRecord;

import java.util.UUID;

public interface iRelatoriosService {
    public GetSaldoPerGrupoResponseRecord getSaldoPerGrupo(UUID idGrupo) throws Exception;
}
