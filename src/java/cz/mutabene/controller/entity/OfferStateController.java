/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.OfferStateEntity;
import cz.mutabene.service.manager.OfferStateManager;
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
@RequestMapping("admin/offerState")
public class OfferStateController implements IEntityUseController<OfferStateEntity, Long, ModelMap, String, BindingResult> {

    private OfferStateManager offerStateManager;
    
    private final String PATH_VIEW = "Entity/OfferState";
    
    @RequestMapping(value="show.htm",method = RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<OfferStateEntity> allStates = offerStateManager.findAll();
        m.addAttribute("allStates", allStates);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method = RequestMethod.GET)
    public String add(OfferStateEntity formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm",method = RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        OfferStateEntity state;
        try{
            state = offerStateManager.findById(id);
            state.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        m.addAttribute("formModel", state);
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm",method = RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        OfferStateEntity state;
        try{
            state = offerStateManager.findById(id);
            state.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        offerStateManager.delete(state);
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method = RequestMethod.POST)
    public String submit(OfferStateEntity formModel, BindingResult result, ModelMap m) {
        offerStateManager.add(formModel);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method = RequestMethod.POST)
    public String save(OfferStateEntity formModel, BindingResult result, ModelMap m) {
        OfferStateEntity state;
        try{
            state = offerStateManager.findById(formModel.getId());
            state.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        state.setName(formModel.getName());
        state.setDescription(formModel.getDescription());
        
        offerStateManager.update(state);
        return "redirect:show.htm";
    }

    public @Autowired void setOfferStateManager(OfferStateManager offerStateManager) {
        this.offerStateManager = offerStateManager;
    }
    
    
}
