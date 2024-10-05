package com.controle_financeiro.api_controlefinanceiro.configurations.security;

import com.controle_financeiro.api_controlefinanceiro.models.PessoaModel;

public class UserCredentials {

    private static final ThreadLocal<UserCredentials> session = new ThreadLocal<>();
    private PessoaModel userModel;

    private UserCredentials(PessoaModel user) {
        this.userModel = user;
    }

    public static void setInstance(PessoaModel user) {
        session.set(new UserCredentials(user));
    }

    public static UserCredentials getInstance() throws Exception {
        UserCredentials userCredentials = session.get();
        if (userCredentials == null) {
            throw new Exception("UserCredentials has not been initialized yet.");
        }
        return userCredentials;
    }

    public PessoaModel getUserModel() throws Exception {
        if (userModel == null) throw new Exception("Invalid user id");
        return userModel;
    }

    public static void clear() {
        session.remove();
    }
}
