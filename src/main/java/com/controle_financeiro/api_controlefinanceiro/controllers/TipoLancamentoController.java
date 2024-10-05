package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.enums.TipoLancamentoEnum;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipolancamento")
public class TipoLancamentoController {

    @GetMapping("/getall")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = TipoLancamentoEnum.class))),
    })
    public ResponseEntity<?> getAllTiposLancamento(){
        return ResponseEntity.ok(TipoLancamentoEnum.values());
    }
}
