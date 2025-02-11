/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.account.registration;


import cz.mutabene.model.entity.AddressEntity;
import cz.mutabene.model.entity.CenterEntity;
import cz.mutabene.model.entity.GenderEntity;
import cz.mutabene.model.entity.RegionEntity;
import cz.mutabene.model.entity.UserRoleEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.forms.registration.RegistrationFederatedValidator;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import cz.mutabene.model.forms.registration.RegistrationModel;
import cz.mutabene.model.forms.registration.RegistrationValidator;
import cz.mutabene.service.manager.AddressManager;
import cz.mutabene.service.manager.CenterManager;
import cz.mutabene.service.manager.RegionManager;
import cz.mutabene.service.manager.UserRoleManager;
import cz.mutabene.service.manager.UserManager;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 *
 * @author stenlik
 */
@Controller("registrationController")
public class RegistrationController {
   
    UserManager userManager;
    AddressManager addressManager;
    CenterManager centerManager;
    UserRoleManager roleItManager;
    RegionManager regionManager;

    RegistrationValidator registrationValidator;
    RegistrationFederatedValidator registrationFederatedValidator;
    
    @ModelAttribute(value="centersList")
    public Map<String,String> centers(){
       Collection<CenterEntity> centers = centerManager.findAll();
       Map<String,String> centersList = new LinkedHashMap<String, String>();
       for(CenterEntity c: centers){
           centersList.put(c.getId().toString(), c.getName());
       }
       return centersList;
    }
    
    @ModelAttribute(value="regionsList")
    public Map<String,String> addRegions(){
       Collection<RegionEntity> regions = regionManager.findAll();
       Map<String,String> regionsList = new LinkedHashMap<String, String>();       
       for(RegionEntity r: regions){
            if( null == r.getRegion()){
                regionsList.put(r.getId().toString(), r.getName());
                for(RegionEntity r2: regions){
                    if(null != r2.getRegion() && r.getId() == r2.getRegion().getId() && r2.getRegion().getId() != r2.getId()){
                      regionsList.put(r2.getId().toString(), "   - "+r2.getName()+" -");
                    }
                }
            }
        }
       return regionsList;
    }
    

    @RequestMapping(value="/register.htm", method= RequestMethod.GET)
    public String register(RegistrationModel regModelForm, ModelMap model){     
        model.addAttribute("regModelForm", regModelForm);
        return "Account/registration";
    }
    
    @RequestMapping(value="/registerFederated.htm", method= RequestMethod.GET)
    public String registerFederated(RegistrationModel regModelForm, ModelMap model){     
        model.addAttribute("regModelForm", regModelForm);
        return "Account/registrationFederated";
    }
    
    @RequestMapping(value="/addUser.htm", method= RequestMethod.POST)
    public String addUser(@ModelAttribute("regModelForm") RegistrationModel regModel, BindingResult result){   
        registrationValidator.validate(regModel, result);
        if(result.hasErrors()){                
            return "Account/registration";
        }
        AddressEntity address = regModel.getAddress();
        UserEntity user = regModel.getUser();
        CenterEntity center = centerManager.findById(Long.parseLong(regModel.getCenterId()));
        RegionEntity region = regionManager.findById(Long.parseLong(regModel.getRegionId()));
        address.setRegion(region);
        addressManager.add(address);
        user.setAddress(address);
        user.setCenter(center);
        //user.setLogin(user.getLogin().toLowerCase());
        if("MALE".equals(regModel.getGender())){
            user.setGender(GenderEntity.MALE);
        } else {
            user.setGender(GenderEntity.FEMALE);
        }
        UserRoleEntity role = roleItManager.findById((long)1);
       

                HashSet<UserRoleEntity> userRoles = new HashSet<UserRoleEntity>();
                userRoles.add(role);
                user.setRoleIT(userRoles);

      
        userManager.add(user);
        return "Home/index";
    }
    
    @RequestMapping(value="/addUserFederated.htm", method= RequestMethod.POST)
    public String addUserFederated(@ModelAttribute("regModelForm") RegistrationModel regModel, BindingResult result){   
        System.out.println("CREATING FEDERATED USER");
        registrationFederatedValidator.validate(regModel, result);
        if(result.hasErrors()){                
            return "Account/registrationFederated";
        }
        
        AddressEntity address = regModel.getAddress();
        
        UserEntity user = regModel.getUser();
        
        CenterEntity center = centerManager.findById(Long.parseLong(regModel.getCenterId()));
        RegionEntity region = regionManager.findById(Long.parseLong(regModel.getRegionId()));
        address.setRegion(region);
        addressManager.add(address);
        
        user.setAddress(address);
        user.setCenter(center);
        
        //user.setLogin(user.getLogin().toLowerCase());
        if("MALE".equals(regModel.getGender())){
            user.setGender(GenderEntity.MALE);
        } else {
            user.setGender(GenderEntity.FEMALE);
        }
        
        UserRoleEntity role = roleItManager.findById((long)1);
       

        HashSet<UserRoleEntity> userRoles = new HashSet<UserRoleEntity>();
        userRoles.add(role);
        user.setRoleIT(userRoles);

      
        System.out.println("this would finish the registration of federated user");
        //userManager.add(user);
        return "Home/index";
    }
        

    public @Autowired void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    public @Autowired void setCenterManager(CenterManager centerManager) {
        this.centerManager = centerManager;
    }

    public @Autowired void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public @Autowired void setRoleItManager(UserRoleManager roleItManager) {
        this.roleItManager = roleItManager;
    }

    public @Autowired void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
      public @Autowired void setValidator(RegistrationValidator validator) {
        this.registrationValidator = validator;
      }
      
    public @Autowired void setFederatedValidator(RegistrationFederatedValidator validator) {
        this.registrationFederatedValidator = validator;
      }
    
}
