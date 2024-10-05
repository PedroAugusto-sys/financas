package com.controle_financeiro.api_controlefinanceiro.records.responses.grupo;

import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;

import java.util.List;

public record GetAllGruposResponseRecord(String response, List<GrupoModel> grupos) {
}
