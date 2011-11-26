/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.git;

import com.google.apps.easyconnect.easyrp.client.basic.data.Account;
import com.google.apps.easyconnect.easyrp.client.basic.data.AccountType;

/**
 *
 * @author Petr
 */
public class AccountImpl implements Account {
    
    private AccountType accountType;
    private String email;
    private String displayName;
    private String photoUrl;

    public AccountType getAccountType() {
        return accountType;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
    
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
}
