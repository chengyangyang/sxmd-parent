# 常用springweb
> 包含 springweb swagger mybatis-plus 的整合

## 启动配置
```
需要配置mybatis-plus 配置
可参考我的demo    sxmd-template

```


## 配置使用说明
```
跨域请求配置。sxmd.web.cors-config.enabled:true

全局日期转换配置。sxmd.web.date-converter.enabled:true

mybatis-plus 分页配置。sxmd.web.mybatis-plus-page-config.enabled:true

swagger 配置开启关闭 sxmd.web.swagger-web-mvc.enabled:true
swagger 授权类型。ApiKey 和OAuth2。 web.swagger.security-scheme:ApiKey
授权范围 sxmd.web.swagger.scope= 例如 all|所有,server|服务  名称和描述用|隔开，多个用,隔开
apiKey keyname配置 sxmd.web.swagger.api-key.keyname=Authorization
apiKey token位置配置 sxmd.web.swagger.api-key.pass-as=header
OAuth2 登录配置 sxmd.web.swagger.oauth-token-url=/oauth/token

```

