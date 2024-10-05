package com.controle_financeiro.api_controlefinanceiro.services.autenticacao_services;

import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.models.PessoaModel;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.LoginRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.RegisterRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.LoginResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.RegisterResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.repositories.iPessoaRepository;
import com.controle_financeiro.api_controlefinanceiro.services.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoServices implements iAutenticacaoServices{

    TokenService tokenService;
    iPessoaRepository pessoaRepository;

    @Autowired
    public AutenticacaoServices(TokenService tokenService, iPessoaRepository pessoaRepository) {
        this.tokenService = tokenService;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public LoginResponseRecord login(LoginRecord loginRecord) throws Exception {
        PessoaModel user = createNewPessoaByLoginRecord(loginRecord);
        user = getUserFromDataBase(user);
        String token = generateToken(user);
        return createLoginResponse(token);
    }

    @Override
    public RegisterResponseRecord register(RegisterRecord registerRecord) throws Exception {

        PessoaModel user = createNewPessoaByRegisterRecord(registerRecord);
        user = saveUserCredentials(user);
        String token = generateToken(user);

        return createRegisterResponse(token);
    }

    private PessoaModel createNewPessoaByRegisterRecord(RegisterRecord registerRecord) throws Exception {
        return new PessoaModel(registerRecord);
    }

    private PessoaModel createNewPessoaByLoginRecord(LoginRecord loginRecord) throws Exception{
        return new PessoaModel(loginRecord);
    }

    private PessoaModel saveUserCredentials(PessoaModel user) throws Exception{
        try {
            return pessoaRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            String message = e.getMostSpecificCause().getMessage();

            if (message.contains("cpf")) {
                throw new Exception(ExceptionsEnum.DUPLICATE_CPF.name());
            } else if (message.contains("email")) {
                throw new Exception(ExceptionsEnum.DUPLICATE_EMAIL.name());
            } else if (message.contains("telefone")) {
                throw new Exception(ExceptionsEnum.DUPLICATE_TELEFONE.name());
            } else {
                throw new Exception(ExceptionsEnum.DUPLICATE_DATA.name());
            }
        }
    }

    private String generateToken(PessoaModel user){
        return tokenService.generateToken(user.getIdPessoa());
    }

    private PessoaModel getUserFromDataBase(PessoaModel user) throws Exception{
        Optional<PessoaModel> dbUser = pessoaRepository.findByCpfPessoaAndEmailPessoa(user.getCpfPessoa(), user.getEmailPessoa());
        if(dbUser.isEmpty())throw new Exception(ExceptionsEnum.ACCESS_DENIED_INVALID_CREDENTIALS.name());
        return dbUser.get();
    }

    private RegisterResponseRecord createRegisterResponse(String token){
        return new RegisterResponseRecord("Success", token);
    }

    private LoginResponseRecord createLoginResponse(String token){
        return new LoginResponseRecord("Success", token);
    }
}
