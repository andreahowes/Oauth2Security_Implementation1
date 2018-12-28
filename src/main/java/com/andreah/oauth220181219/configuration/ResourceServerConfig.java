package com.andreah.oauth220181219.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "the_resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer){
        resourceServerSecurityConfigurer.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers("/users/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
