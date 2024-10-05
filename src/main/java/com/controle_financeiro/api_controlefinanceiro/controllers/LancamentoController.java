package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.CreateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.lancamento.UpdateLancamentoRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.CreateLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.DeleteLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.GetAllLancamentosResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.lancamento.UpdateLancamentoResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.services.lancamento.iLancamentoService;
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
@RequestMapping("/api/lancamento")
public class LancamentoController {

    iLancamentoService lancamentoService;

    @Autowired
    public LancamentoController(iLancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateLancamentoResponseRecord.class))),
    })
    @PostMapping("/create")
    ResponseEntity<?> createLancamento(@RequestBody @Valid CreateLancamentoRecord createLancamentoRecord)throws Exception{
        return ResponseEntity.ok(lancamentoService.createLancamento(createLancamentoRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UpdateLancamentoResponseRecord.class))),
    })
    @PutMapping("/update")
    ResponseEntity<?> updateLancamento(@RequestBody @Valid UpdateLancamentoRecord updateLancamentoRecord)throws Exception{
        return ResponseEntity.ok(lancamentoService.updateLancamento(updateLancamentoRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetAllLancamentosResponseRecord.class))),
    })
    @GetMapping("/getall/{id_grupo}")
    ResponseEntity<?> getAllLancamentos(@PathVariable @Valid UUID id_grupo)throws Exception{
        return ResponseEntity.ok(lancamentoService.getAllLancamentos(id_grupo));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = DeleteLancamentoResponseRecord.class))),
    })
    @DeleteMapping("/delete/{id_grupo}")
    ResponseEntity<?> deleteLancamento(@PathVariable @Valid UUID id_grupo)throws Exception{
        return ResponseEntity.ok(lancamentoService.getAllLancamentos(id_grupo));
    }
}
