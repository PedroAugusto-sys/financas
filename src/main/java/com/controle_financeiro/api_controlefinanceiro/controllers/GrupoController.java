package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.CreateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.grupo.UpdateGrupoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.grupo.*;
import com.controle_financeiro.api_controlefinanceiro.services.grupo_services.iGrupoServices;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/grupo")
public class GrupoController {

    iGrupoServices GrupoService;

    @Autowired
    public GrupoController(iGrupoServices grupoService) {
        GrupoService = grupoService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateGrupoResponseRecord.class))),
    })
    @PostMapping("/create")
    public ResponseEntity<?> createGrupo(@RequestBody @Valid CreateGrupoRecord createGrupoRecord)throws Exception{
        return ResponseEntity.ok(GrupoService.createGrupo(createGrupoRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UpdateGrupoResponseRecord.class))),
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateGrupo(@RequestBody @Valid UpdateGrupoRecord updateGrupoRecord)throws Exception{
        return ResponseEntity.ok(GrupoService.updateGrupo(updateGrupoRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DeleteGrupoResponseRecord.class))),
    })
    @DeleteMapping("/delete/{id_grupo}")
    public ResponseEntity<?> deleteGrupo(@Valid @PathVariable UUID id_grupo)throws Exception{
        return ResponseEntity.ok(GrupoService.DeleteGrupo(id_grupo));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetGrupoResponseRecord.class))),
    })
    @GetMapping("/get/{id_grupo}")
    public ResponseEntity<?> getGrupo(@Valid @PathVariable UUID id_grupo)throws Exception{
        return ResponseEntity.ok(GrupoService.getGrupo(id_grupo));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetAllGruposResponseRecord.class))),
    })
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllGrupos()throws Exception{
        return ResponseEntity.ok(GrupoService.getAllGrupos());
    }
}
