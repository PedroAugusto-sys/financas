package com.controle_financeiro.api_controlefinanceiro.services.pessoa_services;

import com.controle_financeiro.api_controlefinanceiro.configurations.security.UserCredentials;
import com.controle_financeiro.api_controlefinanceiro.models.PessoaModel;
import com.controle_financeiro.api_controlefinanceiro.records.requests.pessoa.UpdatePessoaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.pessoas.UpdatePessoaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.repositories.iPessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePessoaService implements iUpdatePessoaService {
    iPessoaRepository pessoaRepository;

    public UpdatePessoaService(iPessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UpdatePessoaResponseRecord updatePessoa(UpdatePessoaRecord updatePessoaRecord) throws Exception {
        PessoaModel pessoaToBeAltered = getPessoaToBeAltered();
        pessoaToBeAltered = getPessoaModelAltered(pessoaToBeAltered, updatePessoaRecord);
        pessoaToBeAltered = saveAlteredPessoaModel(pessoaToBeAltered);
        return createUpdatePessoaResponse(pessoaToBeAltered);
    }

    private PessoaModel getPessoaToBeAltered() throws Exception{
        return UserCredentials.getInstance().getUserModel();
    }

    private PessoaModel getPessoaModelAltered(PessoaModel userToBeAltered, UpdatePessoaRecord updatePessoaRecord) throws Exception{
        PessoaModel modelWithNewInfo = new PessoaModel(updatePessoaRecord);
        userToBeAltered.setCpfPessoa(modelWithNewInfo.getCpfPessoa());
        userToBeAltered.setEmailPessoa(modelWithNewInfo.getEmailPessoa());
        userToBeAltered.setTelefonePessoa(modelWithNewInfo.getTelefonePessoa());
        userToBeAltered.setNomePessoa(modelWithNewInfo.getNomePessoa());
        return userToBeAltered;
    }

    private PessoaModel saveAlteredPessoaModel(PessoaModel alteredPessoaModel) throws Exception{
        return pessoaRepository.save(alteredPessoaModel);
    }

    private UpdatePessoaResponseRecord createUpdatePessoaResponse(PessoaModel newPessoaModel){
        return new UpdatePessoaResponseRecord("Sucess", newPessoaModel);
    }
}
