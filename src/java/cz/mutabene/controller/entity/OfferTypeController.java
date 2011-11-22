/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.OfferTypeEntity;
import cz.mutabene.service.manager.OfferTypeManager;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping("admin/offerType")
public class OfferTypeController implements IEntityUseController<OfferTypeEntity, Long, ModelMap, String, BindingResult> {

    private OfferTypeManager offerTypeManager;
    
    private final String PATH_VIEW = "Entity/OfferType";
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<OfferTypeEntity> allTypes = offerTypeManager.findAll();
        m.addAttribute("allTypes", allTypes);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(OfferTypeEntity formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm",method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        OfferTypeEntity type;
        try{
            type = offerTypeManager.findById(id);
            type.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        m.addAttribute("formModel", type);
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        OfferTypeEntity type;
        try{
            type = offerTypeManager.findById(id);
            type.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        offerTypeManager.delete(type);
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(OfferTypeEntity formModel, BindingResult result, ModelMap m) {
        offerTypeManager.add(formModel);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(OfferTypeEntity formModel, BindingResult result, ModelMap m) {
        OfferTypeEntity type;
        try{
            type = offerTypeManager.findById(formModel.getId());
            type.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        type.setName(formModel.getName());
        type.setDescription(formModel.getDescription());
        offerTypeManager.update(type);
        return "redirect:show.htm";
    }

    public @Autowired void setOfferTypeManager(OfferTypeManager offerTypeManager) {
        this.offerTypeManager = offerTypeManager;
    }
    
    
}
