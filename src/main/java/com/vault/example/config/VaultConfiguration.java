package com.vault.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
@VaultPropertySource(value = "database/creds/mongo-role", renewal = VaultPropertySource.Renewal.ROTATE)
public class VaultConfiguration extends AbstractVaultConfiguration {

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint vaultEndpoint = VaultEndpoint.create("127.0.0.1",8200);
        vaultEndpoint.setScheme("http");
        return vaultEndpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication("myroot");
    }
}