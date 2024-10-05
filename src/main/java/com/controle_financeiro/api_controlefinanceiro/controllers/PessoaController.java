package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.requests.pessoa.UpdatePessoaRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.pessoas.UpdatePessoaResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.services.pessoa_services.iUpdatePessoaService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    iUpdatePessoaService updatePessoaService;

    @Autowired
    public PessoaController(iUpdatePessoaService updatePessoaService) {
        this.updatePessoaService = updatePessoaService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UpdatePessoaResponseRecord.class))),
    })
    @PutMapping("/update")
    public ResponseEntity<?> updatePessoa(@RequestBody @Valid UpdatePessoaRecord updatePessoaRecord) throws Exception {
        return ResponseEntity.ok(updatePessoaService.updatePessoa(updatePessoaRecord));
    }

}
