/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.catalog;

import cz.mutabene.controller.catalog.interfaces.ICatalogController;
import cz.mutabene.model.forms.catalog.CatalogModel;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.CardCommentEntity;
import cz.mutabene.model.entity.CardEntity;
import cz.mutabene.model.entity.FilePictureEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.forms.entity.CardCommentModel;
import cz.mutabene.service.manager.CardCategoryManager;
import cz.mutabene.service.manager.CardCommentManager;
import cz.mutabene.service.manager.CardManager;
import cz.mutabene.service.manager.FilePictureManager;
import cz.mutabene.service.manager.UserManager;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping(value="catalog/")
public class CatalogController implements ICatalogController<CatalogModel, Long, ModelMap, String, BindingResult> {

    private CardManager cardManager;
    
    private CardCategoryManager cardCategoryManager;
    
    private CardCommentManager cardCommentManager;
    
    private FilePictureManager filePictureManager;
    
    private UserManager userManager;
    
    private final String PATH_VIEW = "Catalog";
    
        private Map<String, String> getListCardCategory() {
        Collection<CardCategoryEntity> allCardCategory = cardCategoryManager.findAll();
        Map<String, String> listCardCategories = new LinkedHashMap<String, String>();
        for (CardCategoryEntity r : allCardCategory) {
            listCardCategories.put(r.getId().toString(), r.getName());
        }
        return listCardCategories;
    }
    
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<CardEntity> allCards = cardManager.findAll();
        Collection<CardCategoryEntity> allCategories = cardCategoryManager.findAll();
        m.addAttribute("allCards", allCards);
        m.addAttribute("allCategories", allCategories);
        return PATH_VIEW+"/show";
    }

    @RequestMapping(value="add.htm",method= RequestMethod.GET)
    public String add(CatalogModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCardCategories", getListCardCategory());
        return PATH_VIEW + "/add";
    }

    @RequestMapping(value="edit.htm",method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        CardEntity card;
        try {
            card = cardManager.findById(id);
            card.getId();
        } catch (Exception e) {
            return "redirect:show.htm";
        }

        CatalogModel formModel = new CatalogModel();
        formModel.setId(card.getId().toString());
        formModel.setDelete(false);
        formModel.setDescription(card.getDescription());
        formModel.setName(card.getName());
        formModel.setPrice(card.getPrice() + "");
        formModel.setSerialNumber(card.getSerialNumber());
        try {
            formModel.setIdCardCategory(card.getCardCategoryEntity().getId().toString());
        } catch (Exception e) {
            //bez kategorie
        }
        try {
            formModel.setIdFilePicture(card.getFilePicture().getId().toString());
            formModel.setPicture(true);
        } catch (Exception e) {
            formModel.setPicture(false);
            //bez obrazku
        }

        m.addAttribute("formModel", formModel);
        m.addAttribute("listCardCategories", getListCardCategory());
        return PATH_VIEW + "/edit";
    }

    @RequestMapping(value="delete.htm",method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        CardEntity card;
        try{
            card = cardManager.findById(id);
            cardManager.delete(card);
        }catch(Exception e){
        }
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm",method= RequestMethod.POST)
    public String submit(CatalogModel formModel, BindingResult result, ModelMap m) {
        CardEntity card = new CardEntity();
        try {
            Long id = Long.parseLong(formModel.getIdCardCategory());
            CardCategoryEntity category = cardCategoryManager.findById(id);
            card.setCardCategoryEntity(category);
        } catch (Exception e) {
            card.setCardCategoryEntity(null);
        }
        card.setDescription(formModel.getDescription());
        card.setName(formModel.getName());
        card.setSerialNumber(formModel.getSerialNumber());
        try {
            card.setPrice(Integer.parseInt(formModel.getPrice()));
        } catch (Exception e) {
            card.setPrice(0);
        }

        if (formModel.getImage().getSize() > 0) { //pokud je prilozeny soubor, tak se ulozi
            FilePictureEntity file = new FilePictureEntity();
            file.setName(formModel.getImage().getOriginalFilename());
            file.setFileSize((long) formModel.getImage().getSize());
            file.setContentType(formModel.getImage().getContentType());
            file.setPicture(formModel.getImage().getBytes());
            filePictureManager.add(file);
            card.setFilePicture(file);
            card.setPicture(true);
        } else {
            card.setPicture(false);
        }

        cardManager.add(card);
        return "redirect:show.htm";
    }

    @RequestMapping(value="save.htm",method= RequestMethod.POST)
    public String save(CatalogModel formModel, BindingResult result, ModelMap m) {
        CardEntity card;
        try {
            card = cardManager.findById(Long.parseLong(formModel.getId()));
        } catch (Exception e) {
            return "redirect:show.htm";
        }

        card.setName(formModel.getName());
        card.setDescription(formModel.getDescription());
        card.setSerialNumber(formModel.getSerialNumber());

        CardCategoryEntity category;
        try {
            Long id = Long.parseLong(formModel.getIdCardCategory());
            category = cardCategoryManager.findById(id);
            card.setCardCategoryEntity(category);
        } catch (Exception e) {
            card.setCardCategoryEntity(null);
        }

        if (formModel.getImage().getSize() > 0) {
            try {
                card.setFilePicture(null);
                Long id = Long.parseLong(formModel.getIdFilePicture());
                filePictureManager.delete(filePictureManager.findById(id));
            } catch (Exception e) {
                //smazano
            }

            FilePictureEntity file = new FilePictureEntity();
            file.setName(formModel.getImage().getOriginalFilename());
            file.setFileSize((long) formModel.getImage().getSize());
            file.setContentType(formModel.getImage().getContentType());
            file.setPicture(formModel.getImage().getBytes());
            filePictureManager.add(file);
            card.setFilePicture(file);
            card.setPicture(true);
        } else {
            if (formModel.isDelete()) {
                card.setFilePicture(null);
                card.setPicture(false);
                try {
                    Long id = Long.parseLong(formModel.getIdFilePicture());
                    filePictureManager.delete(filePictureManager.findById(id));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        cardManager.update(card);
        return "redirect:show.htm";
    }

    @RequestMapping(value="filter.htm",method= RequestMethod.GET)
    public String show(String category, ModelMap m) {  
        Long id;
        try{
            id = Long.parseLong(category);
        }catch(Exception e){
            return "redirect:show.htm";
        }
        Collection<CardEntity> allCards = cardManager.findByCategory(id);
        Collection<CardCategoryEntity> allCategories = cardCategoryManager.findAll();
        m.addAttribute("allCards", allCards);
        m.addAttribute("allCategories", allCategories);
        return PATH_VIEW+"/show";
    }
    
    @RequestMapping(value="detail.htm",method= RequestMethod.GET)
    public String detail(Long id, ModelMap m){
        CardEntity card;
        try{
            card = cardManager.findById(id);
            card.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        m.addAttribute("card", card);
        
        Collection<CardCommentEntity> comments = cardCommentManager.findByIdCard(id);
        m.addAttribute("allComments", comments);
        
        CardCommentModel formModel = new CardCommentModel();
        formModel.setIdCard(card.getId().toString());
        m.addAttribute("formModel", formModel);
        
        return PATH_VIEW+"/detail";
    }
    
    @RequestMapping(value="submitComment.htm",method= RequestMethod.POST)
    public String submit(CardCommentModel formModel, BindingResult result, ModelMap m, Authentication aut) {
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
        
        UserEntity actualUser;
        try{
            User user = (User) aut.getPrincipal();
            actualUser = userManager.findByEmail(user.getUsername());
            actualUser.getId();
        }catch(Exception e){
            actualUser = null;
        }
        comment.setUser(actualUser);
        comment.setDateOfComment(new Date());
        cardCommentManager.add(comment);
        return "redirect:detail.htm?id="+card.getId();
    }
    
    @RequestMapping(value="editComment.htm", method= RequestMethod.GET)
    public String editComment(Long id, ModelMap m) {
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
        
        return PATH_VIEW+"/edit_comment";
        }
    
    @RequestMapping(value="deleteComment.htm",method= RequestMethod.GET)
    public String deleteComment(Long id, ModelMap m) {
        CardCommentEntity comment;
        try{
        comment = cardCommentManager.findById(id);
        comment.getId();
        }catch(Exception e){
        return "reditect:show.htm";
        }
        String idret = comment.getCard().getId().toString();
        cardCommentManager.delete(comment);
        
        return "redirect:detail.htm?id="+idret;
    }
    
    @RequestMapping(value="saveComment.htm",method= RequestMethod.POST)
    public String saveComment(CardCommentModel formModel, BindingResult result, ModelMap m) {
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
        try{
        return "redirect:detail.htm?id="+comment.getCard().getId();
        }catch(Exception e){
        return "redirect:show.htm";
        }
        
    }
    
    public @Autowired void setCardCategoryManager(CardCategoryManager cardCategoryManager) {
        this.cardCategoryManager = cardCategoryManager;
    }

    public @Autowired void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public @Autowired void setFilePictureManager(FilePictureManager filePictureManager) {
        this.filePictureManager = filePictureManager;
    }

    public @Autowired void setCardCommentManager(CardCommentManager cardCommentManager) {
        this.cardCommentManager = cardCommentManager;
    }

    public @Autowired void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    
    
    
}
