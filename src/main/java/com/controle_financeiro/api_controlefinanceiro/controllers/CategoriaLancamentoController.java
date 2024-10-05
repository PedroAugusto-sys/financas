package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.enums.CategoriaLancamentoEnum;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorialancamento")
public class CategoriaLancamentoController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CategoriaLancamentoEnum.class))),
    })
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCategoriaLancamento(){
        return ResponseEntity.ok(CategoriaLancamentoEnum.values());
    }
}
