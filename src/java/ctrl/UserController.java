/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;


import entity.Address;
import entity.User;
import org.springframework.security.core.context.SecurityContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import service.AddressService;
import service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author Anysek
 */
public class UserController extends MultiActionController {
    private UserService userService;
    private AddressService addressService;
//    private String message;

    public UserController() {
    }
    

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    public ModelAndView add(HttpServletRequest req, HttpServletResponse resp, User user)
            throws Exception {
            addressService.saveAddress(user.getAddress());
            userService.saveUser(user);
        return new ModelAndView("redirect:list.htm");
    }

    public ModelAndView list(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        SecurityContext context = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("username", "admin");
        //context.setAuthentication(null);

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("userList", userService.listUser());
        User user = new User();
        Address address = new Address();
        user.setAddress(address);
        modelMap.addAttribute("user", user);
        return new ModelAndView("userForm", modelMap);
    }
}