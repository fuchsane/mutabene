/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.OfferEntity;
import cz.mutabene.model.entity.OfferObjectEntity;
import cz.mutabene.model.entity.OfferStateEntity;
import cz.mutabene.model.entity.OfferTypeEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.forms.entity.OfferModel;
import cz.mutabene.service.manager.OfferManager;
import cz.mutabene.service.manager.OfferObjectManager;
import cz.mutabene.service.manager.OfferStateManager;
import cz.mutabene.service.manager.OfferTypeManager;
import cz.mutabene.service.manager.UserManager;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.hibernate.annotations.CollectionId;
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
@RequestMapping(value="admin/offer/")
public class OfferController implements IEntityUseController<OfferModel, Long, ModelMap, String, BindingResult> {

    private UserManager userManager;
    
    private OfferTypeManager offerTypeManager;
    
    private OfferStateManager offerStateManager;
    
    private OfferObjectManager offerObjectManager;
    
    private OfferManager offerManager;
    
    private final String PATH_VIEW = "Entity/Offer";
    
    private Map<String,String> getListUsers(){
    Collection<UserEntity> allUsers = userManager.findAll();
    Map<String,String> listUsers = new LinkedHashMap<String, String>();
    for(UserEntity u: allUsers){
        listUsers.put(u.getId().toString(), u.getEmail());
    }
    return listUsers;
    }
    
    private Map<String,String> getListOfferType(){
    Collection<OfferTypeEntity> allTypes = offerTypeManager.findAll();
    Map<String,String> listTypes = new LinkedHashMap<String, String>();
    for(OfferTypeEntity t:allTypes){
    listTypes.put(t.getId().toString(), t.getName());
    }
    return listTypes;
    }
    
    private Map<String,String> getListOfferState(){
    Collection<OfferStateEntity> allStates = offerStateManager.findAll();
    Map<String,String> listStates = new LinkedHashMap<String, String>();
    for(OfferStateEntity s:allStates){
    listStates.put(s.getId().toString(), s.getName());
    }
    return listStates;
    }
    
    private Map<String,String> getListObjects(){
    Collection<OfferObjectEntity> allObjects = offerObjectManager.findAll();
    Map<String,String> listObjects = new LinkedHashMap<String, String>();
    for(OfferObjectEntity o:allObjects){
    listObjects.put(o.getId().toString(), o.getName());
    }
    return listObjects;
    }
    
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {      
        Collection<OfferEntity> allOffers = offerManager.findAll();
        m.addAttribute("allOffers", allOffers);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(OfferModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listUsers", getListUsers());
        m.addAttribute("listOfferTypes", getListOfferType());
        m.addAttribute("listOfferState", getListOfferState());
        m.addAttribute("listObjects", getListObjects());
    return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm",method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        OfferEntity offer;
        try{
            offer = offerManager.findById(id);
            offer.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        OfferModel formModel = new OfferModel();
        formModel.setId(offer.getId().toString());
        formModel.setTitle(offer.getTitle());
        formModel.setText(offer.getText());
        
        try{
        formModel.setIdUser(offer.getUser().getId().toString());
        }catch(Exception e){}
        
        try{
        formModel.setIdOfferType(offer.getOfferType().getId().toString());
        }catch(Exception e){}
        
        try{
        formModel.setIdOfferState(offer.getOfferState().getId().toString());
        }catch(Exception e){}
        
        try{
        formModel.setIdOfferObject(offer.getOfferObject().getId().toString());
        }catch(Exception e){}
        
        m.addAttribute("formModel",formModel);
        m.addAttribute("listUsers", getListUsers());
        m.addAttribute("listOfferTypes", getListOfferType());
        m.addAttribute("listOfferState", getListOfferState());
        m.addAttribute("listObjects", getListObjects());
        
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        OfferEntity offer;
        try{
            offer = offerManager.findById(id);
            offer.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        offerManager.delete(offer);
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(OfferModel formModel, BindingResult result, ModelMap m) {
        OfferEntity offer = new OfferEntity();
        offer.setTitle(formModel.getTitle());
        offer.setText(formModel.getText());
        offer.setDateOfInsert(new Date());
        
        UserEntity user;
        try{
        Long id = Long.parseLong(formModel.getIdUser());
        user = userManager.findById(id);
        user.getId();
        }catch(Exception e){
        user = null;
        }        
        offer.setUser(user);
        
        OfferTypeEntity type;
        try{
            Long id = Long.parseLong(formModel.getIdOfferType());
            type = offerTypeManager.findById(id);
            type.getId();
        }catch(Exception e){
            type = null;
        }
        offer.setOfferType(type);
        
        OfferStateEntity state;
        try{
            Long id = Long.parseLong(formModel.getIdOfferState());
            state = offerStateManager.findById(id);
            state.getId();
        }catch(Exception e){
            state = null;
        }
        offer.setOfferState(state);
        
        OfferObjectEntity object;
        try{
            Long id = Long.parseLong(formModel.getIdOfferObject());
            object = offerObjectManager.findById(id);
            object.getId();
        }catch(Exception e){
            object = null;
        }
        offer.setOfferObject(object);
        
        offerManager.add(offer);
        
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(OfferModel formModel, BindingResult result, ModelMap m) {
        OfferEntity offer;
        try{
        Long id = Long.parseLong(formModel.getId());
        offer = offerManager.findById(id);
        offer.getId();
        }catch(Exception e){
        return "redirect:show.htm";
        }
        
        offer.setDateOfInsert(new Date());
        offer.setTitle(formModel.getTitle());
        offer.setText(formModel.getText());
        
        UserEntity user;
        try{
        Long id = Long.parseLong(formModel.getIdUser());
        user = userManager.findById(id);
        user.getId();
        }catch(Exception e){
        user = null;
        }
        offer.setUser(user);
        
        OfferTypeEntity type;
        try{
            Long id = Long.parseLong(formModel.getIdOfferType());
            type = offerTypeManager.findById(id);
            type.getId();
        }catch(Exception e){
            type = null;
        }
        offer.setOfferType(type);
        
        OfferStateEntity state;
        try{
            Long id = Long.parseLong(formModel.getIdOfferState());
            state = offerStateManager.findById(id);
            state.getId();
        }catch(Exception e){
            state = null;
        }
        offer.setOfferState(state);
        
        OfferObjectEntity object;
        try{
            Long id = Long.parseLong(formModel.getIdOfferObject());
            object = offerObjectManager.findById(id);
            object.getId();
        }catch(Exception e){
            object = null;
        }
        offer.setOfferObject(object);
        
        System.out.println("EDITACE = "+offerManager.update(offer));
        
        
        return "redirect:show.htm";
    }

    public @Autowired void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    public @Autowired void setOfferObjectManager(OfferObjectManager offerObjectManager) {
        this.offerObjectManager = offerObjectManager;
    }

    public @Autowired void setOfferStateManager(OfferStateManager offerStateManager) {
        this.offerStateManager = offerStateManager;
    }

    public @Autowired void setOfferTypeManager(OfferTypeManager offerTypeManager) {
        this.offerTypeManager = offerTypeManager;
    }

    public @Autowired void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    
    
}


