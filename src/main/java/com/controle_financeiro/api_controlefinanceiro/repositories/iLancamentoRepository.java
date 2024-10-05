package com.controle_financeiro.api_controlefinanceiro.repositories;

import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;
import com.controle_financeiro.api_controlefinanceiro.models.LancamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface iLancamentoRepository extends JpaRepository<LancamentoModel, UUID> {
    List<LancamentoModel> findAllByGrupo(GrupoModel grupo);
}
