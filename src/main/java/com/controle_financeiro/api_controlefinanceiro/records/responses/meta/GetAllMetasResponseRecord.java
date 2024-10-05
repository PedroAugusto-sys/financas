package com.controle_financeiro.api_controlefinanceiro.records.responses.meta;

import com.controle_financeiro.api_controlefinanceiro.models.MetaModel;

import java.util.List;

public record GetAllMetasResponseRecord(List<MetaModel> metas) {
}
