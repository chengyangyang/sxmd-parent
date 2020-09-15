# 授权客户端
> 和授权中心配合使用

## 功能介绍
```
用户只需要引入当前包即可。登录和注册从授权中心中进行

```

## 启动配置
```
引入即可使用
可参考我的demo    sxmd-template
```

## 配置使用说明
```
修改jwt密钥。如：jwt.signing-key=123，该密钥和授权中心保持一致

默认url授权忽略 swagger、knife4j。如需要自定义可继承 AbstractAuthIgnoreUrl 自定义。

```