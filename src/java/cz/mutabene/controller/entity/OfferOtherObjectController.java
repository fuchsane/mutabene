/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.OfferOtherObjectCategoryEntity;
import cz.mutabene.model.entity.OfferOtherObjectEntity;
import cz.mutabene.model.forms.entity.OfferOtherObjectModel;
import cz.mutabene.service.manager.OfferOtherObjectCategoryManager;
import cz.mutabene.service.manager.OfferOtherObjectManager;
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
@RequestMapping("admin/offerOtherObject")
public class OfferOtherObjectController implements IEntityUseController<OfferOtherObjectModel, Long, ModelMap, String, BindingResult> {

    private OfferOtherObjectManager offerOtherObjectManager;
    
    private OfferOtherObjectCategoryManager categoryManager;
    
    private final String PATH_VIEW = "Entity/OfferOtherObject";
    
    private Map<String, String> getListOtherObjectCategory(){
        Collection<OfferOtherObjectCategoryEntity> allCategories = categoryManager.findAll();
        Map<String, String>  listOtherObjectCategories = new LinkedHashMap<String, String>();
        for(OfferOtherObjectCategoryEntity r: allCategories){
            listOtherObjectCategories.put(r.getId().toString(), r.getName());
        }
        return listOtherObjectCategories;
    }
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<OfferOtherObjectEntity> allObjects = offerOtherObjectManager.findAll();
        m.addAttribute("allObjects", allObjects);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(OfferOtherObjectModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listOtherObjectCategories", getListOtherObjectCategory());
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm",method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        OfferOtherObjectEntity object;
        try{
            object = offerOtherObjectManager.findById(id);
            object.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        OfferOtherObjectModel formModel = new OfferOtherObjectModel();
        formModel.setId(object.getId().toString());
        formModel.setName(object.getName());
        formModel.setDescription(object.getDescription());
        formModel.setPrice(object.getPrice()+"");
        try{
            formModel.setIdOtherObjectCategory(object.getOfferOtherObjectCategory().getId().toString());
        }catch(Exception e){
        
        }
        m.addAttribute("formModel", formModel);
        m.addAttribute("listOtherObjectCategories", getListOtherObjectCategory());
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        OfferOtherObjectEntity other;
        try{
            other = offerOtherObjectManager.findById(id);
            other.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        offerOtherObjectManager.delete(other);
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(OfferOtherObjectModel formModel, BindingResult result, ModelMap m) {
        OfferOtherObjectEntity other = new OfferOtherObjectEntity();
        other.setName(formModel.getName());
        other.setDescription(formModel.getDescription());
        try{
        other.setPrice(Double.parseDouble(formModel.getPrice()));
        }catch(Exception e){
        other.setPrice((double)0);
        }
        
        OfferOtherObjectCategoryEntity category;
        try{
            Long id = Long.parseLong(formModel.getIdOtherObjectCategory());
            category = categoryManager.findById(id);
            category.getId();
        }catch(Exception e){
            category = null;
        }
        other.setOfferOtherObjectCategory(category);
        offerOtherObjectManager.add(other);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(OfferOtherObjectModel formModel, BindingResult result, ModelMap m) {
        OfferOtherObjectEntity other;
        try{
            Long id = Long.parseLong(formModel.getId());
            other = offerOtherObjectManager.findById(id);
            other.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        other.setName(formModel.getName());
        other.setDescription(formModel.getDescription());
        try{
        other.setPrice(Double.parseDouble(formModel.getPrice()));
        } catch(Exception e){
            other.setPrice(0.0);
        }
        
        OfferOtherObjectCategoryEntity category;
        try{
            Long id = Long.parseLong(formModel.getIdOtherObjectCategory());
            category = categoryManager.findById(id);
            category.getId();
        }catch(Exception e){
        category = null;
        }
        other.setOfferOtherObjectCategory(category);
        offerOtherObjectManager.update(other);
        return "redirect:show.htm";
    }

    public @Autowired void setOfferOtherObjectManager(OfferOtherObjectManager offerOtherObjectManager) {
        this.offerOtherObjectManager = offerOtherObjectManager;
    }

    public @Autowired void setCategoryManager(OfferOtherObjectCategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }
    
    
    
    
}
