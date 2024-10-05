package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.CreateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.meta.UpdateMetaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.CreateMetaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.GetAllMetasResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.meta.UpdateMetaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.services.meta.iMetaService;
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
@RequestMapping("/api/meta")
public class MetaController {
    iMetaService metaService;

    @Autowired
    public MetaController(iMetaService metaService) {
        this.metaService = metaService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CreateMetaResponseRecord.class))),
    })
    @PostMapping("/create")
    ResponseEntity<?> createMeta(@RequestBody @Valid CreateMetaRecord createMetaRecord)throws Exception{
        return ResponseEntity.ok(metaService.createMeta(createMetaRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UpdateMetaResponseRecord.class))),
    })
    @PutMapping("/update")
    ResponseEntity<?> updateMeta(@RequestBody @Valid UpdateMetaRecord updateMetaRecord)throws Exception{
        return ResponseEntity.ok(metaService.updateMeta(updateMetaRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = GetAllMetasResponseRecord.class))),
    })
    @GetMapping("/getall/{id_grupo}")
    ResponseEntity<?> getAllMetas(@Valid @PathVariable UUID id_grupo)throws Exception{
        return ResponseEntity.ok(metaService.getAllMetas(id_grupo));
    }
}
