package com.sxmd.config;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: swagger 配置文件
 *
 * @author cy
 * @date 2020年08月18日 8:55
 * Version 1.0
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    private static final String SCHEME_API_KEY = "ApiKey";

    /**
     * 授权范围，   如  all|所有,server|服务  名称和描述用|隔开，多个用,隔开
     */
    @Value("${web.swagger.scope:}")
    private String scope;
    /**
     * 授权类型  ApiKey和OAuth2 默认是 OAuth2
     * 如果选用 ApiKey 生成的token 前面要统一加上 "Bearer "
     */
    @Value("${web.swagger.security-scheme:}")
    private String securityScheme;

    /**
     * apiKey 配置
     */
    @Value("${web.swagger.api-key.keyname:}")
    private String keyName;
    /**
     * apiKey 配置
     */
    @Value("${web.swagger.api-key.pass-as:}")
    private String passAs;

    @Value("${web.swagger.oauth-token-url:}")
    private String tokenUrl;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build().securityContexts(Collections.singletonList(securityContexts()));
        if (StringUtils.isNotBlank(securityScheme) && SCHEME_API_KEY.equals(securityScheme)) {
            docket.securitySchemes(Collections.singletonList(apiKey()));
        } else {
            docket.securitySchemes(Collections.singletonList(securitySchemeOauthPassword()));
        }
        return docket;
    }


    /**
     * api 基本信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger接口文档")
                .description("文档描述")
                .contact(new Contact("cyy", "https://github.com/chengyangyang/springcloud-template.git", "390518881@qq.com"))
                .version("1.0")
                .build();
    }

    /**
     * oauth 认证方式，使用密码的方式
     */
    private SecurityScheme securitySchemeOauthPassword() {
        if (StringUtils.isBlank(tokenUrl)) {
            tokenUrl = "/oauth/token";
        }
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(tokenUrl);
        return new OAuthBuilder()
                .name("oauth")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(scopes())
                .build();
    }

    private ApiKey apiKey() {
        String key = "Authorization";
        String pass = "header";
        if (StringUtils.isNotBlank(keyName)) {
            key = keyName;
        }
        if (StringUtils.isNotBlank(passAs)) {
            pass = passAs;
        }
        return new ApiKey("Bearer", key, pass);
    }

    /**
     * 设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContexts() {
        String referenceName = "oauth";
        if (StringUtils.isNotBlank(securityScheme) && SCHEME_API_KEY.equals(securityScheme)) {
            referenceName = "Bearer";
        }
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(referenceName, scopes().toArray(new AuthorizationScope[scopes().size()]))))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 允许认证的范围
     *
     * @return
     */
    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> list = new ArrayList<>();
        if (StringUtils.isNotBlank(scope)) {
            String[] conetnt = scope.split(",");
            for (int i = 0; i < conetnt.length; i++) {
                String[] scopes = conetnt[i].split("|");
                String name = scopes[0];
                String des = "描述";
                if (scopes.length > 0) {
                    des = scopes[1];
                }
                list.add(new AuthorizationScope(name, des));
            }
        } else {
            list.add(new AuthorizationScope("all", "所有权限"));
        }
        return list;
    }


}
