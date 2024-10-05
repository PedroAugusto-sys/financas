package com.controle_financeiro.api_controlefinanceiro.controllers;

import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.LoginRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.RegisterRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.LoginResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.records.responses.autenticacao.RegisterResponseRecord;
import com.controle_financeiro.api_controlefinanceiro.services.autenticacao_services.iAutenticacaoServices;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

    iAutenticacaoServices autenticacaoServices;

    @Autowired
    public AutenticacaoController(iAutenticacaoServices autenticacaoServices) {
        this.autenticacaoServices = autenticacaoServices;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = RegisterResponseRecord.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRecord registerRecord) throws Exception {
        return ResponseEntity.ok(autenticacaoServices.register(registerRecord));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = LoginResponseRecord.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRecord loginRecord) throws Exception {
        return ResponseEntity.ok(autenticacaoServices.login(loginRecord));
    }
}
