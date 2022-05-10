package com.qdc.demoeurekazuul.IPFilter;

import com.netflix.zuul.ZuulFilter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class IPFilter extends ZuulFilter {
    private String[] whitelist;

    @Value("${yxwfilter.ip.whitelist}")
    private String strIPWhitelist;

    @Value("${yxwfilter.ip.whitelistenabled}")
    private String WhitelistEnabled;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return "true".equalsIgnoreCase(WhitelistEnabled);
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(strIPWhitelist);
        whitelist = strIPWhitelist.split("\\,");
        RequestContext cxt = RequestContext.getCurrentContext();
        HttpServletRequest req = cxt.getRequest();
        String ipAddr = this.getIpAddr(req);
        System.out.println("请求IP地址为：["+ipAddr+"]");
        List<String> ips = new ArrayList<>();
        for(int i =0;i<whitelist.length;++i){
            System.out.println(whitelist[i]);
            ips.add(whitelist[i]);
        }

        System.out.println("whitelist:" + ips.toString());
        if(!ips.contains(ipAddr)){
            System.out.println("未通过IP地址校验。["+ipAddr+"]");
            cxt.setResponseStatusCode(401);
            cxt.setSendZuulResponse(false);
            cxt.getResponse().setContentType("application/json;charset=UTF-8");
            cxt.setResponseBody("{\"errrode\":\"00001\",\"errmsg\":\"IpAddr is forbidden!["+ipAddr+"]\"}");
        }
        return null;
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
