package com.andreah.oauth220181219.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //this is the authorization server (that holds the user info that the client is requesting for the user)
    //I'm using in memory credentials but might switch to jdbc later

    static final String CLIENT_ID = "andrea-client";
    static final String CLIENT_SECRET = "andrea-secret";
    static final String GRANT_TYPE = "the_password";
    static final String AUTHORIZATION_CODE = "the_authorization_code";
    static final String REFRESH_TOKEN = "refreshed_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 60*60;
    static final int REFRESH_TOKEN_VALIDITY_SECONDS = 60*60*60;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception{
        clientDetailsServiceConfigurer
                .inMemory()
                .withClient(CLIENT_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception{
        authorizationServerEndpointsConfigurer.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }









































}
