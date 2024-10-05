package com.controle_financeiro.api_controlefinanceiro.services.meta;

import com.controle_financeiro.api_controlefinanceiro.configurations.security.UserCredentials;
import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;
import com.controle_financeiro.api_controlefinanceiro.models.MetaModel;
import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.CreateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.UpdateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.CreateMetaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.GetAllMetasResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.UpdateMetaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.repositories.iGrupoRepository;
import com.controle_financeiro.api_controlefinanceiro.repositories.iMetaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MetaService implements iMetaService{

    iMetaRepository metaRepository;
    iGrupoRepository grupoRepository;

    @Override
    public UpdateMetaResponseRecord updateMeta(UpdateMetaRecord updateMetaRecord) throws Exception {
        MetaModel metaToBeAltered = findMeta(updateMetaRecord.idMeta());
        MetaModel AlteredMeta = updateMeta(updateMetaRecord, metaToBeAltered);
        AlteredMeta = saveMeta(AlteredMeta);
        return new UpdateMetaResponseRecord(AlteredMeta);
    }

    @Override
    public GetAllMetasResponseRecord getAllMetas(UUID groupId) throws Exception {
        GrupoModel grupo = findGrupo(groupId);
        return new GetAllMetasResponseRecord(grupo.getMetas());
    }

    @Override
    public CreateMetaResponseRecord createMeta(CreateMetaRecord createMetaRecord) throws Exception {
        GrupoModel grupo = findGrupo(createMetaRecord.idGrupo());
        MetaModel newMeta = createNewMeta(createMetaRecord, grupo);
        newMeta = saveMetaModel(newMeta);
        return new CreateMetaResponseRecord(newMeta);
    }

    private MetaModel createNewMeta(CreateMetaRecord createMetaRecord, GrupoModel grupoModel) throws Exception{
        MetaModel newMeta = new MetaModel();
        newMeta.setValor(createMetaRecord.valor());
        newMeta.setTipoLancamento(createMetaRecord.tipo());
        newMeta.setGrupo(grupoModel);
        return newMeta;
    }

    private MetaModel saveMetaModel(MetaModel metaModel) throws Exception{
        return metaRepository.save(metaModel);
    }

    private GrupoModel findGrupo(UUID idGrupo) throws Exception{
        Optional<GrupoModel> grupo = grupoRepository.findById(idGrupo);
        if(grupo.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_GRUPO.name());
        if(grupo.get().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return grupo.get();
    }

    private MetaModel findMeta(UUID idMeta) throws Exception{
        Optional<MetaModel> meta = metaRepository.findById(idMeta);
        if(meta.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_LANCAMENTO.name());
        if(meta.get().getGrupo().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return meta.get();
    }

    private MetaModel updateMeta(UpdateMetaRecord updateMetaRecord, MetaModel metaToBeAltered)throws Exception{
        metaToBeAltered.setValor(updateMetaRecord.valor());
        metaToBeAltered.setTipoLancamento(updateMetaRecord.tipo());
        return metaToBeAltered;
    }

    private MetaModel saveMeta(MetaModel metaModel) throws Exception{
        return metaRepository.save(metaModel);
    }
}
