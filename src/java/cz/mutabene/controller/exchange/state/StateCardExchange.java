/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.exchange.state;

import cz.mutabene.controller.exchange.state.interfaces.IStateOfferExchange;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.OfferEntity;
import cz.mutabene.model.entity.OfferTypeEntity;
import cz.mutabene.model.forms.exchange.ExchangeModel;
import cz.mutabene.service.manager.CardCategoryManager;
import cz.mutabene.service.manager.CardManager;
import cz.mutabene.service.manager.OfferManager;
import cz.mutabene.service.manager.OfferStateManager;
import cz.mutabene.service.manager.OfferTypeManager;
import cz.mutabene.service.manager.UserManager;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stenlik
 */
@Service("stateCardExchange")
public class StateCardExchange implements IStateOfferExchange<ExchangeModel, Long, ModelMap, String, BindingResult> {

    private final String NAME = "card";
    
    private OfferManager offerManager;
    
    private UserManager userManager;
    
    private CardCategoryManager cardCategoryManager;
    
    private CardManager cardManager;
    
    private OfferTypeManager offerTypeManager;
    
    private OfferStateManager offerStateManager;
       
    private final String PATH_VIEW = "CardExchange";
    
    private Map<String, String> getListCardCategory() {
        Collection<CardCategoryEntity> allCardCategory = cardCategoryManager.findAll();
        Map<String, String> listCardCategories = new LinkedHashMap<String, String>();
        for (CardCategoryEntity r : allCardCategory) {
            listCardCategories.put(r.getId().toString(), r.getName());
        }
        return listCardCategories;
    }
    
    public String show(ModelMap m) {
        Collection<OfferEntity> allOffers = offerManager.findAllCardOffer();
        Collection<CardCategoryEntity> allCategories = cardCategoryManager.findAll();
        Collection<OfferTypeEntity> allTypes = offerTypeManager.findAll();
        m.addAttribute("allTypes", allTypes);
        m.addAttribute("allOffers", allOffers);
        m.addAttribute("allCategories", allCategories);
        return PATH_VIEW+"/show";
    }

    public String add(ExchangeModel formModel, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String edit(Long id, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String delete(Long id, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String submit(ExchangeModel formModel, BindingResult result, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String save(ExchangeModel formModel, BindingResult result, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean support(String name) {
        return NAME.equals(name);
    }

    public @Autowired void setCardCategoryManager(CardCategoryManager cardCategoryManager) {
        this.cardCategoryManager = cardCategoryManager;
    }

    public @Autowired void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    public @Autowired void setOfferTypeManager(OfferTypeManager offerTypeManager) {
        this.offerTypeManager = offerTypeManager;
    }

    public String filter(String category, String type, ModelMap m) {
        Collection<OfferEntity> allOffers = new LinkedList<OfferEntity>();
        if(null == category && null == type){
        allOffers = offerManager.findAllCardOffer();
        } else if(null != category && null == type){
        try{
            Long id = Long.parseLong(category);
            allOffers = offerManager.findByCardCategoryId(id);
        }catch(Exception e){}
        } else if(null == category && null != type){
        try{
            Long id = Long.parseLong(type);
            allOffers = offerManager.findByOfferTypeId(id);
            }catch(Exception e){}
        } else if(null != category && null != type){
            Long idCategory = 0L, idType = 0L;
            boolean cat = true,typ = true;
            try{
                idCategory = Long.parseLong(category);
            }catch(Exception e){
                cat = false;
            }
            try{
                idType = Long.parseLong(type);
            }catch(Exception e){
                typ = false;
            }
            if(cat && typ){
                allOffers = offerManager.findByCardCategoryAndOfferTypeId(idCategory, idType);
            } else if(cat && !typ){
                allOffers = offerManager.findByCardCategoryId(idCategory);
            } else if(!cat && typ){
                allOffers = offerManager.findByOfferTypeId(idType);
            } 
        }
        Collection<CardCategoryEntity> allCategories = cardCategoryManager.findAll();
        Collection<OfferTypeEntity> allTypes = offerTypeManager.findAll();
        m.addAttribute("allTypes", allTypes);
        m.addAttribute("allOffers", allOffers);
        m.addAttribute("allCategories", allCategories);
        return PATH_VIEW+"/show";
    }
    
    
    
}
