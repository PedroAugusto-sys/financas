package com.controle_financeiro.api_controlefinanceiro.services.pessoa_services;

import com.controle_financeiro.api_controlefinanceiro.records.requests.pessoa.UpdatePessoaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.pessoas.UpdatePessoaResponseRecord;
import org.springframework.stereotype.Service;

@Service
public interface iUpdatePessoaService {
    public UpdatePessoaResponseRecord updatePessoa(UpdatePessoaRecord updatePessoaRecord)throws Exception;
}
