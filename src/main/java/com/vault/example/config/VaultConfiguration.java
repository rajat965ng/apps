package com.vault.example.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
@VaultPropertySource(value = "database/creds/mongo-role", renewal = VaultPropertySource.Renewal.ROTATE)
public class VaultConfiguration extends AbstractVaultConfiguration implements EnvironmentAware {


    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        System.out.println("Env:"+env);
        VaultEndpoint vaultEndpoint = VaultEndpoint.create(env.getProperty("vaultHost"),8200);
        vaultEndpoint.setScheme("http");
        return vaultEndpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication(env.getProperty("vaultToken"));
    }
}