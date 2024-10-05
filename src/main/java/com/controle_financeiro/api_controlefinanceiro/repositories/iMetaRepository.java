package com.controle_financeiro.api_controlefinanceiro.repositories;

import com.controle_financeiro.api_controlefinanceiro.models.MetaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface iMetaRepository extends JpaRepository<MetaModel, UUID> {
}
