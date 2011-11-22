/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.OfferOtherObjectCategoryEntity;
import cz.mutabene.model.forms.entity.OfferOtherObjectCategoryModel;
import cz.mutabene.service.manager.OfferOtherObjectCategoryManager;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
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
@RequestMapping("admin/offerOtherObjectCategory")
public class OfferOtherObjectCategoryController implements IEntityUseController<OfferOtherObjectCategoryModel, Long, ModelMap, String, BindingResult> {

    private OfferOtherObjectCategoryManager offerOtherObjectCategoryManager;
    
    private final String PATH_VIEW = "Entity/OfferOtherObjectCategory";
    
    private Map<String, String> getListOtherObjectCategory(){
        Collection<OfferOtherObjectCategoryEntity> allCategories = offerOtherObjectCategoryManager.findAll();
        Map<String, String>  listOtherObjectCategories = new LinkedHashMap<String, String>();
        for(OfferOtherObjectCategoryEntity r: allCategories){
            listOtherObjectCategories.put(r.getId().toString(), r.getName());
        }
        return listOtherObjectCategories;
    }
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<OfferOtherObjectCategoryEntity> allObjectCategories = offerOtherObjectCategoryManager.findAll();
        m.addAttribute("allObjectCategories", allObjectCategories);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(OfferOtherObjectCategoryModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listOtherObjectCategories", getListOtherObjectCategory());
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm",method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        OfferOtherObjectCategoryEntity other;
        try{
            other = offerOtherObjectCategoryManager.findById(id);
            other.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        OfferOtherObjectCategoryModel formModel = new OfferOtherObjectCategoryModel();
        formModel.setId(other.getId().toString());
        formModel.setName(other.getName());
        formModel.setDescription(other.getDescription());
        try{
        formModel.setIdOtherObjectCategory(other.getOfferOtherObjectCategory().getId().toString());    
        }catch(Exception e){
        }
        
        m.addAttribute("formModel", formModel);
        m.addAttribute("listOtherObjectCategories", getListOtherObjectCategory());
        
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        OfferOtherObjectCategoryEntity other;
        try{
            other = offerOtherObjectCategoryManager.findById(id);
            other.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        boolean isSingle = true;
        Collection<OfferOtherObjectCategoryEntity> allCategory = offerOtherObjectCategoryManager.findAll();
        for(OfferOtherObjectCategoryEntity c: allCategory){               
                if(c != null && c.getOfferOtherObjectCategory() != null){               
                    if(id.equals(c.getOfferOtherObjectCategory().getId())){
                        isSingle = false;
                    }
                }
        }
        if(isSingle){
            offerOtherObjectCategoryManager.delete(other);
        } else {
        //errorova hlaska
        }
        
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(OfferOtherObjectCategoryModel formModel, BindingResult result, ModelMap m) {
        OfferOtherObjectCategoryEntity other = new OfferOtherObjectCategoryEntity();
        other.setName(formModel.getName());
        other.setDescription(formModel.getDescription());
        
        OfferOtherObjectCategoryEntity parent;
        try{
            Long id = Long.parseLong(formModel.getIdOtherObjectCategory());
            parent = offerOtherObjectCategoryManager.findById(id);
            parent.getId();
        }catch(Exception e){
            parent = null;
        }
        other.setOfferOtherObjectCategory(parent);
        offerOtherObjectCategoryManager.add(other);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(OfferOtherObjectCategoryModel formModel, BindingResult result, ModelMap m) {
        OfferOtherObjectCategoryEntity other;
        try{
            Long id = Long.parseLong(formModel.getId());
            other = offerOtherObjectCategoryManager.findById(id);
            other.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        other.setName(formModel.getName());
        other.setDescription(formModel.getDescription());
        
        OfferOtherObjectCategoryEntity parent;
        try{
            Long id = Long.parseLong(formModel.getIdOtherObjectCategory());
            parent = offerOtherObjectCategoryManager.findById(id);
            parent.getId();
            if(other.getId().longValue() != parent.getId().longValue()){
            other.setOfferOtherObjectCategory(parent);
            } else {
            other.setOfferOtherObjectCategory(null);
            }
        }catch(Exception e){
            other.setOfferOtherObjectCategory(null);
        }
        offerOtherObjectCategoryManager.update(other);
        return "redirect:show.htm";
    }

    public @Autowired void setOfferOtherObjectCategoryManager(OfferOtherObjectCategoryManager offerOtherObjectCategoryManager) {
        this.offerOtherObjectCategoryManager = offerOtherObjectCategoryManager;
    }
    
    
}
