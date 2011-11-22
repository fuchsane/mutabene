/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.UserRoleEntity;
import cz.mutabene.service.manager.UserRoleManager;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping(value="admin/userRole/")
public class UserRoleController implements IEntityUseController<UserRoleEntity, Long, ModelMap, String, BindingResult> {
    
    private final String PATH_VIEW = "Entity/UserRole";
    
    UserRoleManager userRoleManager;
    
    @RequestMapping(value="show.htm", method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<UserRoleEntity> allRoles = userRoleManager.findAll();
        m.addAttribute("allRoles", allRoles);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm", method= RequestMethod.GET)
    public String add(UserRoleEntity formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm", method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        UserRoleEntity role;
        try{
            role = userRoleManager.findById(id);
            role.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        m.addAttribute("formModel", role);
            return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm", method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        UserRoleEntity role;
        try{
            role = userRoleManager.findById(id);
            role.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }

        userRoleManager.delete(userRoleManager.findById(id));

        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm", method= RequestMethod.POST)
    public String submit(UserRoleEntity formModel, BindingResult result, ModelMap m) {
            userRoleManager.add(formModel); //TODO pridat validator
            return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm", method= RequestMethod.POST)
    public String save(UserRoleEntity formModel, BindingResult result, ModelMap m) {
            userRoleManager.update(formModel); //TODO pridat validator
            return "redirect:show.htm";
    }
    
    public @Autowired void setUserRoleManager(UserRoleManager userRoleManager) {
        this.userRoleManager = userRoleManager;
    }
}
