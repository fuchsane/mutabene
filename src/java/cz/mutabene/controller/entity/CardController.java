/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.CardEntity;
import cz.mutabene.model.entity.FilePictureEntity;
import cz.mutabene.model.forms.entity.CardModel;
import cz.mutabene.service.manager.CardCategoryManager;
import cz.mutabene.service.manager.CardManager;
import cz.mutabene.service.manager.FilePictureManager;
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
@RequestMapping("admin/card/")
public class CardController implements IEntityUseController<CardModel, Long, ModelMap, String, BindingResult> {

    private CardCategoryManager cardCategoryManager;
    private CardManager cardManager;
    private FilePictureManager filePictureManager;
    private final String PATH_VIEW = "Entity/Card";

    private Map<String, String> getListCardCategory() {
        Collection<CardCategoryEntity> allCardCategory = cardCategoryManager.findAll();
        Map<String, String> listCardCategories = new LinkedHashMap<String, String>();
        for (CardCategoryEntity r : allCardCategory) {
            listCardCategories.put(r.getId().toString(), r.getName());
        }
        return listCardCategories;
    }

    @RequestMapping(value = "show.htm", method = RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<CardEntity> allCards = cardManager.findAll();
        m.addAttribute("allCards", allCards);
        return PATH_VIEW + "/show";
    }

    @RequestMapping(value = "add.htm", method = RequestMethod.GET)
    public String add(CardModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listCardCategories", getListCardCategory());
        return PATH_VIEW + "/add";
    }

    @RequestMapping(value = "edit.htm", method = RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        CardEntity card;
        try {
            card = cardManager.findById(id);
            card.getId();
        } catch (Exception e) {
            return "redirect:show.htm";
        }

        CardModel formModel = new CardModel();
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

    @RequestMapping(value = "delete.htm", method = RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        CardEntity card;
        try{
            card = cardManager.findById(id);
            cardManager.delete(card);
        }catch(Exception e){
        }
        return "redirect:show.htm";
    }

    @RequestMapping(value = "submit.htm", method = RequestMethod.POST)
    public String submit(CardModel formModel, BindingResult result, ModelMap m) {
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

    @RequestMapping(value = "save.htm", method = RequestMethod.POST)
    public String save(CardModel formModel, BindingResult result, ModelMap m) {
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

    public @Autowired
    void setCardCategoryManager(CardCategoryManager cardCategoryManager) {
        this.cardCategoryManager = cardCategoryManager;
    }

    public @Autowired
    void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public @Autowired
    void setFilePictureManager(FilePictureManager filePictureManager) {
        this.filePictureManager = filePictureManager;
    }
}
