package com.controle_financeiro.api_controlefinanceiro.services.grupo_services;

import com.controle_financeiro.api_controlefinanceiro.configurations.security.UserCredentials;
import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.models.GrupoModel;
import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.CreateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.UpdateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.grupo.*;
import com.controle_financeiro.api_controlefinanceiro.repositories.iGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GrupoServices implements iGrupoServices{

    iGrupoRepository grupoRepository;

    @Autowired
    public GrupoServices(iGrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Override
    public UpdateGrupoResponseRecord updateGrupo(UpdateGrupoRecord updateGrupoRecord) throws Exception {
        GrupoModel grupoToBeAltered = findGrupo(updateGrupoRecord.id());
        grupoToBeAltered = alterGrupo(grupoToBeAltered,updateGrupoRecord);
        return new UpdateGrupoResponseRecord("Success", grupoToBeAltered);
    }

    @Override
    public GetGrupoResponseRecord getGrupo(UUID idGrupo) throws Exception {
        GrupoModel grupoToBeReturned = findGrupo(idGrupo);
        return new GetGrupoResponseRecord("Success", grupoToBeReturned);
    }

    @Override
    public GetAllGruposResponseRecord getAllGrupos() throws Exception {
        List<GrupoModel> gruposList = getListaGruposUsuario();
        return new GetAllGruposResponseRecord("Success",gruposList);
    }

    @Override
    public CreateGrupoResponseRecord createGrupo(CreateGrupoRecord createGrupoRecord) throws Exception {
        GrupoModel newGrupo = createNewGrupoModel(createGrupoRecord);
        newGrupo = saveNewGrupo(newGrupo);
        return new CreateGrupoResponseRecord("Success",newGrupo);
    }

    @Override
    public DeleteGrupoResponseRecord DeleteGrupo(UUID idGrupo) throws Exception {
        GrupoModel grupoToBeDeleted = findGrupo(idGrupo);
        deleteGroup(grupoToBeDeleted);
        return new DeleteGrupoResponseRecord("Success");
    }

    private GrupoModel findGrupo(UUID idGrupo) throws Exception{
        Optional<GrupoModel> grupo = grupoRepository.findById(idGrupo);
        if(grupo.isEmpty()) throw new Exception(ExceptionsEnum.INVALID_ID_GRUPO.name());
        if(grupo.get().getDonoGrupo().getIdPessoa() != UserCredentials.getInstance().getUserModel().getIdPessoa()) throw new Exception(ExceptionsEnum.ACCESS_DENIED_INSUFFICIENT_PERMISSION.name());
        return grupo.get();
    }

    private void deleteGroup(GrupoModel groupToBeDeleted) throws Exception{
        grupoRepository.delete(groupToBeDeleted);
    }

    private GrupoModel createNewGrupoModel(CreateGrupoRecord createGrupoRecord) throws Exception{
        GrupoModel grupoModel = new GrupoModel();
        grupoModel.setDescricaoGrupo(createGrupoRecord.descricao());
        grupoModel.setNomeGrupo(createGrupoRecord.nome());
        grupoModel.setDonoGrupo(UserCredentials.getInstance().getUserModel());
        return grupoModel;
    }

    private GrupoModel saveNewGrupo(GrupoModel grupoToBeSaved) throws Exception{
        return grupoRepository.save(grupoToBeSaved);
    }

    private List<GrupoModel> getListaGruposUsuario() throws Exception {
        return UserCredentials.getInstance().getUserModel().getGrupos();
    }

    private GrupoModel alterGrupo(GrupoModel grupoToBeAltered, UpdateGrupoRecord updateGrupoRecord)throws Exception{
        grupoToBeAltered.setDescricaoGrupo(updateGrupoRecord.descricao());
        grupoToBeAltered.setNomeGrupo(updateGrupoRecord.nome());
        return grupoToBeAltered;
    }

}
