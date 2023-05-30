package org.zzd.utils;

import org.springframework.security.core.GrantedAuthority;

/**
 * @apiNote 权限封装
 * @author zzd
 * @date 2023/5/28 22:00
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
