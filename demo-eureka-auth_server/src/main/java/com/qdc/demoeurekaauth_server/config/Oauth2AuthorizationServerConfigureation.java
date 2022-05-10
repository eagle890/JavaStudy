package com.qdc.demoeurekaauth_server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Oauth2AuthorizationServerConfigureation extends AuthorizationServerConfigurerAdapter {
    //用于进行身份验证的接口

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DruidDataSource druidDataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{
        security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetails)throws Exception{
        clientDetails.withClientDetails(new JdbcClientDetailsService(druidDataSource));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        endpoints.userDetailsService(userDetailsService);
        JdbcTokenStore tokenStore = new JdbcTokenStore(druidDataSource);

        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(new JdbcClientDetailsService(druidDataSource));
        tokenServices.setAccessTokenValiditySeconds(38000);
        endpoints.tokenServices(tokenServices);
        //数据库管理授权码
        endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(druidDataSource));
        //数据库管理授权信息
        ApprovalStore approvalStore = new JdbcApprovalStore(druidDataSource);
        endpoints.approvalStore(approvalStore);

    }
}
