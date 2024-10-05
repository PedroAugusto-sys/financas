package com.controle_financeiro.api_controlefinanceiro.services.grupo_services;

import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.CreateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.UpdateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.grupo.*;

import java.util.UUID;

public interface iGrupoServices {
    public DeleteGrupoResponseRecord DeleteGrupo(UUID idGrupo) throws Exception;
    public CreateGrupoResponseRecord createGrupo(CreateGrupoRecord createGrupoRecord) throws Exception;
    public GetAllGruposResponseRecord getAllGrupos() throws Exception;
    public GetGrupoResponseRecord getGrupo(UUID idGrupo) throws Exception;
    public UpdateGrupoResponseRecord updateGrupo(UpdateGrupoRecord updateGrupoRecord) throws Exception;
}
