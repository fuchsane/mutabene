/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.forms.entity.CardCategoryModel;
import cz.mutabene.service.manager.CardCategoryManager;
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
@RequestMapping(value="admin/cardCategory")
public class CardCategoryController implements IEntityUseController<CardCategoryModel, Long, ModelMap, String, BindingResult> {

    private CardCategoryManager cardCategoryManager;
    
    private final String PATH_VIEW = "Entity/CardCategory";
    
    private Map<String, String> getListCardCategory(){
        Collection<CardCategoryEntity> allCategories = cardCategoryManager.findAll();
        Map<String, String>  listCardCategories = new LinkedHashMap<String, String>();
        for(CardCategoryEntity r: allCategories){
            listCardCategories.put(r.getId().toString(), r.getName());
        }
        return listCardCategories;
    }
    
    @RequestMapping(value="show.htm", method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<CardCategoryEntity> listCardCategory = cardCategoryManager.findAll();
        m.addAttribute("listCardCategories", listCardCategory);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm", method= RequestMethod.GET)
    public String add(CardCategoryModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCardCategories", getListCardCategory());
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm", method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        CardCategoryEntity category;
        try{
            category = cardCategoryManager.findById(id);
            category.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        CardCategoryModel formModel = new CardCategoryModel();
        formModel.setId(category.getId().toString());
        formModel.setDescription(category.getDescription());
        formModel.setName(category.getName());
        try{
        formModel.setIdCardCategory(category.getCardCategory().getId().toString());
        }catch(Exception e){
        
        }
        
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCardCategories", getListCardCategory());       
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm", method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        CardCategoryEntity category;
        try{
            category = cardCategoryManager.findById(id);
            category.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }   
        boolean isSingle = true;
        Collection<CardCategoryEntity> allCategory = cardCategoryManager.findAll();
        for(CardCategoryEntity c: allCategory){               
                if(c != null && c.getCardCategory() != null){               
                    if(id.equals(c.getCardCategory().getId())){
                        isSingle = false;
                    }
                }
        }
        if(isSingle){
        cardCategoryManager.delete(category);
        } else {
        //errorova hlaska
        }
        return "redirect:show.htm";     
    }

    @RequestMapping(value="submit.htm", method= RequestMethod.POST)
    public String submit(CardCategoryModel formModel, BindingResult result, ModelMap m) {
        CardCategoryEntity cardCategory = new CardCategoryEntity();
        cardCategory.setName(formModel.getName());
        cardCategory.setDescription(formModel.getDescription());
        try{
            Long id = Long.parseLong(formModel.getIdCardCategory());
            CardCategoryEntity parent = cardCategoryManager.findById(id);
            cardCategory.setCardCategory(parent);
        } catch (Exception e){
            cardCategory.setCardCategory(null);
        }
        cardCategoryManager.add(cardCategory);
                
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm", method= RequestMethod.POST)
    public String save(CardCategoryModel formModel, BindingResult result, ModelMap m) {
        CardCategoryEntity category;
        try{
            Long id = Long.parseLong(formModel.getId());
            category = cardCategoryManager.findById(id);
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        category.setName(formModel.getName());
        category.setDescription(formModel.getDescription());
        
        CardCategoryEntity parent;
        try{
            Long id = Long.parseLong(formModel.getIdCardCategory());
            parent = cardCategoryManager.findById(id);
            if(parent.getId().longValue() == category.getId().longValue()){
            category.setCardCategory(null);
            } else {
            category.setCardCategory(parent);
            }
            
        }catch(Exception e){
            category.setCardCategory(null);
        }
        cardCategoryManager.update(category);
        return "redirect:show.htm";
    }

    public @Autowired void setCardCategoryManager(CardCategoryManager cardCategoryManager) {
        this.cardCategoryManager = cardCategoryManager;
    }
    
    
}
