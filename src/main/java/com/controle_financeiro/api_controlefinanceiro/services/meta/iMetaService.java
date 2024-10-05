package com.controle_financeiro.api_controlefinanceiro.services.meta;

import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.CreateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.UpdateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.CreateMetaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.GetAllMetasResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.UpdateMetaResponseRecord;

import java.util.UUID;

public interface iMetaService {
    public UpdateMetaResponseRecord updateMeta(UpdateMetaRecord updateMetaRecord) throws Exception;
    public GetAllMetasResponseRecord getAllMetas(UUID groupId) throws Exception;
    public CreateMetaResponseRecord createMeta(CreateMetaRecord createMetaRecord) throws Exception;
}
