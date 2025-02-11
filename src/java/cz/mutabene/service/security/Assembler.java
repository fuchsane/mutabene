/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import cz.mutabene.model.entity.UserRoleEntity;
import cz.mutabene.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author stenlik
 */
@Service("assembler")
public class Assembler {
    
    private static Assembler instance;
    
    public static Assembler getInstanceIfExists() {
        return instance;
    }
    
    Assembler() {
        //FIXME: potential leak
        instance = this;
    }
    
    @Transactional(readOnly = true)
    public User buildUserFromUserEntity(UserEntity userEntity){
    
    String username = userEntity.getEmail();
    String password = userEntity.getPassword();
    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean accoountNonLocked = true;
    
    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    Set<UserRoleEntity> roles = userEntity.getRoleIT();
    try{
        
    }catch(Exception e){
        
    }
    for(UserRoleEntity role: roles){
        authorities.add(new GrantedAuthorityImpl(role.getName()));
        //System.out.println(userEntity.getLogin()+" "+role.getName());
    }
    
    User user = new User(username, password, enabled, accountNonExpired, accountNonExpired, accoountNonLocked, authorities);
    return user;
    }
}
