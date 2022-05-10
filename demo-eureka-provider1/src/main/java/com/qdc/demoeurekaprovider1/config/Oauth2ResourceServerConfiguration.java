//package com.qdc.demoeurekaprovider1.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
//@Configuration
//public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    private static final String URL ="http://localhost:8081/oauth/check_token";
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception{
//        //设置验证Token的方法，即使用test和12345的客户端去URL验证token
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//
//        //设置认证的客户端id和密码
//        tokenServices.setCheckTokenEndpointUrl(URL);
//        tokenServices.setClientId("test");
//        tokenServices.setClientSecret("123456");
//
//        //设置请求的资源，本次访问的是provide的userall
//        resources.tokenServices(tokenServices);
//        resources.resourceId("userall").stateless(true);
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
