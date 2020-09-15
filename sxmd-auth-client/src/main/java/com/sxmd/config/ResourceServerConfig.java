package com.sxmd.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.List;

/**
 * Description: 资源服务器,登录使用
 *
 * @author cy
 * @date 2020年09月02日 16:43
 * Version 1.0
 */
@Configuration
@EnableResourceServer
@ConditionalOnExpression("${sxmd.auth.resource-server.enabled:true}")
@Slf4j
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * jwt 密钥前缀，和授权中心保持一致
     */
    private static final String PRE_SIGNING_KEY = "SXMD-";

    @Value("${sxmd.jwt.signing-key:}")
    private String signingKey;
    @Autowired
    private AuthIgnoreUrl authIgnoreUrl;


    @Bean
    @ConditionalOnMissingBean(AuthIgnoreUrl.class)
    public AuthIgnoreUrl ignoreUrl() {
        return new AbstractAuthIgnoreUrl() {
            @Override
            public List<String> ignoreUrl() {
                log.info("添加默认权限忽略路径");
                return super.ignoreUrl();
            }
        };
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        List<String> ignoreUrl = authIgnoreUrl.ignoreUrl();
        http.httpBasic().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(ignoreUrl.toArray(new String[ignoreUrl.size()]))
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(PRE_SIGNING_KEY);
        return converter;
    }
}
