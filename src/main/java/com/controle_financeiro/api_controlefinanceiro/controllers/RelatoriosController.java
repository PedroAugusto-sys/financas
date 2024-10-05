package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.responses.relatorios.GetSaldoPerGrupoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.services.relatorios.iRelatoriosService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/relatorios")
public class RelatoriosController {

    iRelatoriosService relatoriosService;

    @Autowired
    public RelatoriosController(iRelatoriosService relatoriosService) {
        this.relatoriosService = relatoriosService;
    }

    @GetMapping("/saldo/{idGrupo}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetSaldoPerGrupoResponseRecord.class))),
    })
    public ResponseEntity<?> getSaldoGrupo(@PathVariable UUID idGrupo) throws Exception{
        return ResponseEntity.ok(relatoriosService.getSaldoPerGrupo(idGrupo));
    }
}
