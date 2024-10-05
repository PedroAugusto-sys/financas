package com.controle_financeiro.api_controlefinanceiro.services.lancamento;

import com.controle_financeiro.api_controlefinanceiro.configurations.security.UserCredentials;
import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;
import com.controle_financeiro.api_controlefinanceiro.models.LancamentoModel;
import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.CreateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.UpdateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.CreateLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.DeleteLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.GetAllLancamentosResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.UpdateLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.repositories.iGrupoRepository;
import com.controle_financeiro.api_controlefinanceiro.repositories.iLancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LancamentoService implements iLancamentoService{

    iGrupoRepository grupoRepository;
    iLancamentoRepository lancamentoRepository;

    @Autowired
    public LancamentoService(iGrupoRepository grupoRepository, iLancamentoRepository lancamentoRepository) {
        this.grupoRepository = grupoRepository;
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public UpdateLancamentoResponseRecord updateLancamento(UpdateLancamentoRecord updateLancamentoRecord) throws Exception {
        LancamentoModel lancamentoToBeAltered = findLancamento(updateLancamentoRecord.idLancamento());
        LancamentoModel alteredLancamento = updateLancamento(lancamentoToBeAltered, updateLancamentoRecord);
        alteredLancamento = saveNewLancamento(alteredLancamento);
        return new UpdateLancamentoResponseRecord(alteredLancamento);
    }

    @Override
    public GetAllLancamentosResponseRecord getAllLancamentos(UUID groupId) throws Exception {
        GrupoModel grupo = findGrupo(groupId);
        return new GetAllLancamentosResponseRecord(grupo.getLancamentos());
    }

    @Override
    public DeleteLancamentoResponseRecord deleteLancamento(UUID idLancamento) throws Exception {
        LancamentoModel lancamentoToBeDeleted = findLancamento(idLancamento);
        deleteLancamento(lancamentoToBeDeleted);
        return new DeleteLancamentoResponseRecord("Success");
    }

    @Override
    public CreateLancamentoResponseRecord createLancamento(CreateLancamentoRecord createLancamentoRecord) throws Exception {
        GrupoModel grupo = findGrupo(createLancamentoRecord.idGrupo());
        LancamentoModel newLancamento = createNewLancamento(createLancamentoRecord,grupo);
        return new CreateLancamentoResponseRecord("Success",newLancamento);
    }

    private LancamentoModel createNewLancamento(CreateLancamentoRecord createLancamentoRecord, GrupoModel grupo)throws Exception{
        LancamentoModel newLancamento = new LancamentoModel();

        newLancamento.setValor(createLancamentoRecord.valor());
        newLancamento.setTipoLancamento(createLancamentoRecord.tipo());
        newLancamento.setData(createLancamentoRecord.data());
        newLancamento.setGrupo(grupo);
        newLancamento.setDescricao(createLancamentoRecord.descricao());
        newLancamento.setCategoriaLancamento(createLancamentoRecord.categoria());
        newLancamento.setNome(createLancamentoRecord.nome());

        return lancamentoRepository.save(newLancamento);
    }

    private void deleteLancamento(LancamentoModel lancamentoModel) throws  Exception{
        lancamentoRepository.delete(lancamentoModel);
    }

    private GrupoModel findGrupo(UUID idGrupo) throws Exception{
        Optional<GrupoModel> grupo = grupoRepository.findById(idGrupo);
        if(grupo.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_GRUPO.name());
        if(grupo.get().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return grupo.get();
    }

    private LancamentoModel findLancamento(UUID idLancamento) throws Exception{
        Optional<LancamentoModel> lancamento = lancamentoRepository.findById(idLancamento);
        if(lancamento.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_LANCAMENTO.name());
        if(lancamento.get().getGrupo().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return lancamento.get();
    }

    private LancamentoModel saveNewLancamento(LancamentoModel lancamentoModel) throws  Exception{
        return lancamentoRepository.save(lancamentoModel);
    }

    private LancamentoModel updateLancamento(LancamentoModel lancamentoToBeAltered, UpdateLancamentoRecord updateLancamentoRecord) throws Exception{
        lancamentoToBeAltered.setCategoriaLancamento(updateLancamentoRecord.categoria());
        lancamentoToBeAltered.setTipoLancamento(updateLancamentoRecord.tipo());
        lancamentoToBeAltered.setNome(updateLancamentoRecord.nome());
        lancamentoToBeAltered.setValor(updateLancamentoRecord.valor());
        lancamentoToBeAltered.setDescricao(updateLancamentoRecord.descricao());
        lancamentoToBeAltered.setData(updateLancamentoRecord.data());
        return lancamentoToBeAltered;
    }
}
