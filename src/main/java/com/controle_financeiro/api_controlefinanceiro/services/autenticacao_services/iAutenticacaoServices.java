package com.controle_financeiro.api_controlefinanceiro.services.autenticacao_services;

import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.LoginRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.RegisterRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.LoginResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.RegisterResponseRecord;

public interface iAutenticacaoServices {
    public LoginResponseRecord login(LoginRecord loginRecord) throws Exception;
    public RegisterResponseRecord register(RegisterRecord registerRecord) throws Exception;
}
