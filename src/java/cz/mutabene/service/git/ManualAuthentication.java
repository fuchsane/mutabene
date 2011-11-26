/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.git;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Petr
 */
public class ManualAuthentication implements Authentication {

    private Collection<GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private String name;
    private Object credentials;
    private Object principal;
    
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Object getCredentials() {
        return credentials;
    }

    public Object getDetails() {
        return null;
    }

    public Object getPrincipal() {
        return principal;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean bln) throws IllegalArgumentException {
        isAuthenticated = bln;
    }

    public String getName() {
        return name;
    }
    
    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }
    
    public void setPrincipal(Object principal) {
        this.principal = principal;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
}
