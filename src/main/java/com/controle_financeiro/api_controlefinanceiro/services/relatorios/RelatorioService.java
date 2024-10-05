package com.controle_financeiro.api_controlefinanceiro.services.relatorios;

import com.controle_financeiro.api_controlefinanceiro.configurations.security.UserCredentials;
import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;
import com.controle_financeiro.api_controlefinanceiro.models.LancamentoModel;
import com.controle_financeiro.api_controlefinanceiro.records.responses.relatorios.GetSaldoPerGrupoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.repositories.iGrupoRepository;
import com.controle_financeiro.api_controlefinanceiro.repositories.iLancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RelatorioService implements iRelatoriosService{

    iLancamentoRepository lancamentoRepository;
    iGrupoRepository grupoRepository;

    @Autowired
    public RelatorioService(iLancamentoRepository lancamentoRepository, iGrupoRepository grupoRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.grupoRepository = grupoRepository;
    }

    @Override
    public GetSaldoPerGrupoResponseRecord getSaldoPerGrupo(UUID idGrupo) throws Exception {
        GrupoModel grupo = findGrupo(idGrupo);
        List<LancamentoModel> lancamentos = getLancamentos(grupo);
        return new GetSaldoPerGrupoResponseRecord("Success", getSaldoTotal(lancamentos).toString());
    }

    private List<LancamentoModel> getLancamentos(GrupoModel grupo) throws Exception {
        return lancamentoRepository.findAllByGrupo(grupo);
    }

    private GrupoModel findGrupo(UUID idGrupo) throws Exception{
        Optional<GrupoModel> grupo = grupoRepository.findById(idGrupo);
        if(grupo.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_GRUPO.name());
        if(grupo.get().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return grupo.get();
    }

    private Double getSaldoTotal(List<LancamentoModel> listaLancamentos) throws Exception{

        double soma = 0.0;

        for (int i = 0; i < listaLancamentos.size(); i++) {
            soma = soma + listaLancamentos.get(i).getValor().doubleValue();
        }

        return soma;
    }
}
