/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.CardCommentEntity;
import cz.mutabene.model.entity.CardEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.forms.entity.CardCommentModel;
import cz.mutabene.service.manager.CardCommentManager;
import cz.mutabene.service.manager.CardManager;
import cz.mutabene.service.manager.UserManager;
import java.util.Collection;
import java.util.Date;
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
@RequestMapping(value="admin/cardComment/")
public class CardCommentController implements IEntityUseController<CardCommentModel, Long, ModelMap, String, BindingResult> {

    private UserManager userManager;
    
    private CardManager cardManager;
    
    private CardCommentManager cardCommentManager;
    
    private final String PATH_VIEW = "Entity/CardComment";
    
    private Map<String,String> getListUsers(){
    Collection<UserEntity> allUsers = userManager.findAll();
    Map<String,String> listUsers = new LinkedHashMap<String, String>();
    for(UserEntity u: allUsers){
        listUsers.put(u.getId().toString(), u.getEmail());
    }
    return listUsers;
    }
    
    private Map<String,String> getListCards(){
    Collection<CardEntity> allCards = cardManager.findAll();
    Map<String,String> listCards = new LinkedHashMap<String, String>();
    for(CardEntity c: allCards){
        listCards.put(c.getId().toString(), c.getName());
    }
    return listCards;
    }
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<CardCommentEntity> listComments = cardCommentManager.findAll();
        m.addAttribute("listComments", listComments);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(CardCommentModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCards", getListCards());
        m.addAttribute("listUsers", getListUsers());
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm", method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        CardCommentEntity comment;
        try{
            comment = cardCommentManager.findById(id);
            comment.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        CardCommentModel formModel = new CardCommentModel();
        formModel.setId(id.toString());
        formModel.setText(comment.getText());
        try{
        formModel.setIdCard(comment.getCard().getId().toString());
        } catch(Exception e){}
        try{
        formModel.setIdUser(comment.getUser().getId().toString());
        }catch(Exception e){}
        
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCards", getListCards());
        m.addAttribute("listUsers", getListUsers());
        
        return PATH_VIEW+"/edit";
        }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        CardCommentEntity comment;
        try{
        comment = cardCommentManager.findById(id);
        comment.getId();
        }catch(Exception e){
        return "reditect:show.htm";
        }
        
        cardCommentManager.delete(comment);
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(CardCommentModel formModel, BindingResult result, ModelMap m) {
        CardCommentEntity comment = new CardCommentEntity();
        comment.setText(formModel.getText());
        
        CardEntity card;
        try{
            Long id = Long.parseLong(formModel.getIdCard());
            card = cardManager.findById(id);
            card.getId();
        }catch(Exception e){
            card = null;
        }
        comment.setCard(card);
        
        UserEntity user;
        try{
            Long id = Long.parseLong(formModel.getIdUser());
            user = userManager.findById(id);
            user.getId();
        }catch(Exception e){
            user = null;
        }
        comment.setUser(user);
        comment.setDateOfComment(new Date());
        cardCommentManager.add(comment);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(CardCommentModel formModel, BindingResult result, ModelMap m) {
        CardCommentEntity comment;
        try{
            Long id = Long.parseLong(formModel.getId());
            comment = cardCommentManager.findById(id);
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        comment.setDateOfComment(new Date());
        comment.setText(formModel.getText());
        
        CardEntity card;
        try{
            Long id = Long.parseLong(formModel.getIdCard());
            card = cardManager.findById(id);
            card.getId();
        }catch(Exception e){
            card = null;
        }
        
        comment.setCard(card);
        
        UserEntity user;
        try{
            Long id = Long.parseLong(formModel.getIdUser());
            user = userManager.findById(id);
            user.getId();
        }catch(Exception e){
            user = null;
        }
        comment.setUser(user);
        
        cardCommentManager.update(comment);
        return "redirect:show.htm";
    }

    public @Autowired void setCardCommentManager(CardCommentManager cardCommentManager) {
        this.cardCommentManager = cardCommentManager;
    }

    public @Autowired void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public @Autowired void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
 
    
}
