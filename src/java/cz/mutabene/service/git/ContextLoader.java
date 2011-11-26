/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.git;

import com.google.apps.easyconnect.easyrp.client.basic.Context;
import com.google.apps.easyconnect.easyrp.client.basic.data.AccountService;
import com.google.apps.easyconnect.easyrp.client.basic.session.RpConfig;
import com.google.apps.easyconnect.easyrp.client.basic.session.SessionBasedSessionManager;
import com.google.apps.easyconnect.easyrp.client.basic.session.SessionManager;
import com.google.apps.easyconnect.easyrp.client.basic.util.ApiClient;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Petr
 */
public class ContextLoader implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent sce) {
        initEasyRpContext();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
    // Set config parameters.
        private void initEasyRpContext() {
          RpConfig config = new RpConfig.Builder()
              .sessionUserKey("login_account")
              .sessionIdpAssertionKey("idp_assertion")
              .homeUrl("/index.htm")
              .signupUrl("/signup.jsp")
              .build();

        //Suppose GaeAccountService is your own AccountService implementation.

        AccountService accountService = new MyAccountService();

        // SessionBasedSessionManager fully supports server side session state.
        SessionManager sessionManager = new SessionBasedSessionManager(config);

        Context.setConfig(config);
        Context.setAccountService(accountService);
        Context.setSessionManager(sessionManager);
        Context.setApiClient(new
        ApiClient("AIzaSyDD6ZJipa2LQo3MdcED2i8aThi6nQOLQno"));
     }
    
}
