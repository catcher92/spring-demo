package com.catcher92.demo.springdemo.controller;

import com.catcher92.demo.springdemo.util.WebUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"","index"})
    public String index(HttpServletRequest request, Model model){
        String ip = WebUtil.getRemoteAddr(request);

        // 往spring-session redis中写数据
        StringBuilder builder = new StringBuilder("ip:").append(ip);
        getEhCacheInfo(builder);
        String message = builder.toString();
        request.getSession().setAttribute("message", message);
        model.addAttribute("message", message);
        return "index";
    }

    /**
     * <p>功能描述：查看ehcache缓存中的数据</p>
     * @return 
     * @author caoxuedong
     * @date 2017/2/24 13:44
     */
    private void getEhCacheInfo(StringBuilder builder) {
        // shiro中未指定ehcache配置文件，默认使用ehcache-core-2.5.0.jar!/ehcache-failsafe.xml
        // ehcache-failsafe.xml 中指定缓存时间为120秒
        // Configuration configuration = instance.getConfiguration();
        builder.append("<br />ehcache缓存数据:");
        builder.append("<br />人员:");
        CacheManager instance = CacheManager.getInstance();
        Cache authenticationCache = instance.getCache("com.catcher92.demo.springdemo.shiro.realm.UserRealm.authenticationCache");
        List authenticationCacheKeys = authenticationCache.getKeys();
        Map<Object, Element> all = authenticationCache.getAll(authenticationCacheKeys);
        for (Map.Entry<Object, Element> entry : all.entrySet()) {
            SimpleAuthenticationInfo info = (SimpleAuthenticationInfo)entry.getValue().getValue();
            builder.append("<br />").append(entry.getKey()).append(":").append(info.getPrincipals()).append("&nbsp;&nbsp;").append(entry.getValue());
        }
        builder.append("<br /><br />权限:");
        Cache authorizationCache = instance.getCache("com.catcher92.demo.springdemo.shiro.realm.UserRealm.authorizationCache");
        List authorizationCacheKeys = authorizationCache.getKeys();
        all = authorizationCache.getAll(authorizationCacheKeys);
        for (Map.Entry<Object, Element> entry : all.entrySet()) {
            SimpleAuthorizationInfo info = (SimpleAuthorizationInfo)entry.getValue().getValue();
            builder.append("<br />").append(entry.getKey()).append(":").append(info.getStringPermissions()).append("&nbsp;&nbsp;").append(entry.getValue());
        }
    }

}
