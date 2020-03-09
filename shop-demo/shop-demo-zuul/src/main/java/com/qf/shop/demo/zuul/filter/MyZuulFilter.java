package com.qf.shop.demo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {

    /*
    配置过滤器类型，根据生命周期的不同，有四种类型
    1.pre: 路由之前
    2.routing: 路由之时
    3.post:   路由之后
    4.error:  在上面三者执行过程中出现了异常就会调用该error过滤器
     */
    @Override
    public String filterType() {
        return "pre";
    }

    //相同过滤器类型之间确定执行的顺序，0就表示第一个执行。
    @Override
    public int filterOrder() {
        return 0;
    }

    /*
    配置是否需要过滤：true 需要   false  不需要
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器要执行过滤的具体的内容
     * 如果这一次请求，没有携带cookie，那么就不进行路由。
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //得到request的当前的上下文对象
        RequestContext ctx = RequestContext.getCurrentContext();
        //得到request对象
        HttpServletRequest request = ctx.getRequest();
        Cookie[] cookies = request.getCookies();
        if(cookies==null||cookies.length==0){
            //不进行路由
            ctx.setSendZuulResponse(false);
            //设置返回状态码
            ctx.setResponseStatusCode(401);
            //设置返回体
            ctx.setResponseBody("no permission");
            //设置自定义键值对
            ctx.set("isSuccess",false);

        }else{
            //进行路由
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess",true);
        }
        return null;
    }
}
