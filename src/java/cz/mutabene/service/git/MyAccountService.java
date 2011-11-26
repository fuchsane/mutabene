/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.git;

import com.google.apps.easyconnect.easyrp.client.basic.data.Account;
import com.google.apps.easyconnect.easyrp.client.basic.data.AccountService;
import com.google.apps.easyconnect.easyrp.client.basic.data.AccountType;
import cz.mutabene.model.entity.GenderEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.entity.UserRoleEntity;
import cz.mutabene.service.manager.UserManager;
import cz.mutabene.service.security.Assembler;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Petr
 */
public class MyAccountService implements AccountService {
    
    public Account getAccountByEmail(String email) {
        
        System.out.println("Google wants to obtain user " + email);
        
        UserManager userManager = UserManager.getInstanceIfExists();
        
        UserEntity userEntity = userManager.findByEmail(email);
        
        if(userEntity != null) {
            System.out.println("OK I know this user");
            AccountImpl account = new AccountImpl();
            account.setAccountType(AccountType.FEDERATED);
            account.setEmail(userEntity.getEmail());
            account.setDisplayName(userEntity.getFirstname() + " " + userEntity.getSurname());
            account.setPhotoUrl("http://assets1.mi-web.org/foto_miniaturas/0005/5121/megusta_mediano.jpg?1297290970");
            
            Assembler assembler = Assembler.getInstanceIfExists();
            
            User user = assembler.buildUserFromUserEntity(userEntity);
            
            ManualAuthentication authentication = new ManualAuthentication();
            authentication.setName(user.getUsername());
            authentication.setAuthorities(user.getAuthorities());
            authentication.setPrincipal(user);
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            return account;
        }
        else {
            System.out.println("I do not know this user");
            return null;
        }
       
    }

    public Account createFederatedAccount(JSONObject jsono) {
        
        try {            
            String email = jsono.getString("email");
            
            String firstName = null;
            
            if (jsono.has("firstName")) {
                firstName = jsono.getString("firstName");
            }
            
            String lastName = null;
            
            if (jsono.has("lastName")) {
                lastName = jsono.getString("lastName");
            }
            
            //user.setPhotoUrl(assertion.getString("profilePicture"));
            
            //TODO: create a new user from available data
            System.out.println("Google asks to create a new user " + email);
            
            
            UserEntity user = new UserEntity();
            
            user.setActive(false);
            user.setPassword("heslo");
            user.setFirstname(firstName);
            user.setSurname(lastName);
            user.setEmail(email);
            
            UserManager userManager = UserManager.getInstanceIfExists();
            userManager.add(user);
                
            /*user.setActive(false);
            user.setGender(GenderEntity.MALE);
            user.setTelephoneNumber("737323111");
            user.setPassword("heslo");
            Set<UserRoleEntity> roles = new LinkedHashSet<UserRoleEntity>();
            roles.add(role1);
            roles.add(role2);
            user.setRoleIT(roles);
            user.setAddress(adresa);
            user.setCenter(centrum);
            userManager.add(user);*/
            
            AccountImpl account = new AccountImpl();
            account.setAccountType(AccountType.FEDERATED);
            account.setEmail("email");
            account.setDisplayName("blah");
            account.setPhotoUrl("http://assets1.mi-web.org/foto_miniaturas/0005/5121/megusta_mediano.jpg?1297290970");
            return account;
            
            
        } catch (JSONException ex) {
            Logger.getLogger(MyAccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("to create an user");
        
        return null;
    }

    public boolean checkPassword(String email, String passeord) {
        System.out.println("Google wants to check password for user " + email);
        return false;
    }

    public ResponseCode toFederated(String email) {
 
        System.out.println("Google wants to upgrade this user to federated " + email);
        
        return ResponseCode.ACCOUNT_NOT_FOUND;
    }
    
    
}
