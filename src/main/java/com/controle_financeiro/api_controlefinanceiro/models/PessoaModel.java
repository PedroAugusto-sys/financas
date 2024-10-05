package com.controle_financeiro.api_controlefinanceiro.models;

import com.controle_financeiro.api_controlefinanceiro.enums.ExceptionsEnum;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.LoginRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.autenticacao.RegisterRecord;
import com.controle_financeiro.api_controlefinanceiro.records.requests.pessoa.UpdatePessoaRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Entity
@Table(name = "tb_pessoa")
public class PessoaModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID idPessoa;

    @Column(name = "nome", nullable = false, unique = true)
    private String nomePessoa;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpfPessoa;

    @Column(name = "email", nullable = false, unique = true)
    private String emailPessoa;

    @Column(name = "telefone", nullable = false, unique = true)
    private String telefonePessoa;

    @JsonIgnore
    @OneToMany(mappedBy = "donoGrupo", cascade = CascadeType.ALL)
    private List<GrupoModel> grupos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return null;
    }

    public PessoaModel() {
    }

    public PessoaModel(UUID idPessoa, String nomePessoa, String cpfPessoa, String emailPessoa, String telefonePessoa, List<GrupoModel> grupos) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.cpfPessoa = cpfPessoa;
        this.emailPessoa = emailPessoa;
        this.telefonePessoa = telefonePessoa;
        this.grupos = grupos;
    }

    public PessoaModel(RegisterRecord registerRecord) throws Exception {

        String cpf = registerRecord.cpf().replaceAll("[^0-9]", "");
        if (!isValidCPF(cpf)) {
            throw new Exception(ExceptionsEnum.INVALID_CPF.name());
        }
        this.cpfPessoa = cpf;

        String email = registerRecord.email();
        if (!isValidEmail(email)) {
            throw new Exception(ExceptionsEnum.INVALID_EMAIL.name());
        }
        this.emailPessoa = email;

        String telefone = registerRecord.telefone().replaceAll("[^0-9]", "");
        if (!isValidTelefone(telefone)) {
            throw new Exception(ExceptionsEnum.INVALID_TELEFONE.name());
        }

        this.telefonePessoa = telefone;
        this.nomePessoa = registerRecord.nome();
    }

    public PessoaModel(UpdatePessoaRecord updatePessoaRecord) throws Exception {

        String cpf = updatePessoaRecord.cpf().replaceAll("[^0-9]", "");
        if (!isValidCPF(cpf)) {
            throw new Exception(ExceptionsEnum.INVALID_CPF.name());
        }
        this.cpfPessoa = cpf;

        String email = updatePessoaRecord.email();
        if (!isValidEmail(email)) {
            throw new Exception(ExceptionsEnum.INVALID_EMAIL.name());
        }
        this.emailPessoa = email;

        String telefone = updatePessoaRecord.telefone().replaceAll("[^0-9]", "");
        if (!isValidTelefone(telefone)) {
            throw new Exception(ExceptionsEnum.INVALID_TELEFONE.name());
        }

        this.telefonePessoa = telefone;
        this.nomePessoa = updatePessoaRecord.nome();
    }

    public PessoaModel(LoginRecord loginRecord) throws Exception {

        String cpf = loginRecord.cpf().replaceAll("[^0-9]", "");
        if (!isValidCPF(cpf)) {
            throw new Exception(ExceptionsEnum.INVALID_CPF.name());
        }
        this.cpfPessoa = cpf;

        String email = loginRecord.email();
        if (!isValidEmail(email)) {
            throw new Exception(ExceptionsEnum.INVALID_EMAIL.name());
        }
        this.emailPessoa = email;

    }

    private boolean isValidCPF(String cpf) {
        return cpf.length() == 11;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidTelefone(String telefone) {
        return telefone.length() >= 10;
    }

    public UUID getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(UUID idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getCpfPessoa() {
        return cpfPessoa;
    }

    public void setCpfPessoa(String cpfPessoa) {
        this.cpfPessoa = cpfPessoa;
    }

    public String getEmailPessoa() {
        return emailPessoa;
    }

    public void setEmailPessoa(String emailPessoa) {
        this.emailPessoa = emailPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public List<GrupoModel> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoModel> grupos) {
        this.grupos = grupos;
    }
}
