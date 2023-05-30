package org.zzd.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zzd.constant.SecurityConstants;
import org.zzd.constant.WeChatConstant;
import org.zzd.entity.SystemUser;
import org.zzd.entity.WechatUser;
import org.zzd.mapper.WechatUserMapper;
import org.zzd.pojo.SecuritySystemUser;
import org.zzd.result.ResponseResult;
import org.zzd.service.SystemUserService;
import org.zzd.service.WeChatService;
import org.zzd.utils.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @apiNote :微信服务实现类
 * @author :zzd
 * @date : 2023-03-29 22:45
 */
@Service
@Slf4j
public class WeChatServiceImpl extends ServiceImpl<WechatUserMapper, WechatUser> implements WeChatService {
    @Autowired
    private WeChatService weChatService;
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseResult wxLogin(String code) {
        //拼接url，微信登录凭证校验接口 发起http调用
        String uri = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + WeChatConstant.APPID +
                "&secret=" + WeChatConstant.APPSECRET +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        String res = HttpUtil.get(uri);
        JSONObject json = JSONObject.parseObject(res);
        log.info("uri:{}\nwechat return {}", uri, json);
        //失败{"errcode":40029,"errmsg":"invalid code, rid: 642452e9-4c04dd06-08584dc4"}
        //成功{"session_key":"iTnI15CLymAVnTBcDo3f0w==","openid":"olHiI4quDueG0ycjhIRLcVmoXHfg"}
        String token;
        if (json.get("errcode") == null) {
            //唯一标识
            String openid = json.getString("openid");
            WechatUser wechatUser = weChatService.getOne(new LambdaQueryWrapper<WechatUser>().eq(WechatUser::getOpenId, openid));
            if (wechatUser == null) {
                //注册+登录
                SystemUser systemUser = new SystemUser();
                systemUser.setIfWxUser(1);
                systemUser.setUsername(RandomStringUtils.randomAlphabetic(5));
                systemUser.setNickname("微信用户" + RandomStringUtils.randomAlphabetic(5));
                systemUser.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");
                systemUserService.save(systemUser);

                WechatUser wxUser = new WechatUser();
                wxUser.setOpenId(openid);
                wxUser.setUserId(systemUser.getId());
                wxUser.setSessionKey(json.getString("session_key"));
                weChatService.save(wxUser);

                SecuritySystemUser securitySystemUser = new SecuritySystemUser(systemUser);
                token = jwtTokenUtil.generateToken(securitySystemUser);
            } else {
                //登录
                wechatUser.setSessionKey(json.getString("session_key"));
                Long userId = wechatUser.getUserId();
                LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(SystemUser::getId, userId);
                SystemUser systemUser = systemUserService.getOne(wrapper);
                SecuritySystemUser securitySystemUser = new SecuritySystemUser(systemUser);
                token = jwtTokenUtil.generateToken(securitySystemUser);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("tokenHead", SecurityConstants.TOKEN_PREFIX);
            map.put("expireTime", jwtTokenUtil.getExpiredDateFromToken(token).getTime());
            return ResponseResult.success("登录成功", map);
        } else {
            return ResponseResult.error("微信登录凭证校验接口调用失败");
        }
    }
}
