/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.exchange.state;

import cz.mutabene.controller.exchange.state.interfaces.IStateOfferExchange;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.OfferEntity;
import cz.mutabene.model.entity.OfferOtherObjectCategoryEntity;
import cz.mutabene.model.entity.OfferTypeEntity;
import cz.mutabene.model.forms.exchange.ExchangeModel;
import cz.mutabene.service.manager.OfferManager;
import cz.mutabene.service.manager.OfferOtherObjectCategoryManager;
import cz.mutabene.service.manager.OfferOtherObjectManager;
import cz.mutabene.service.manager.OfferTypeManager;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stenlik
 */

@Service("stateOtherObjectExchange")
public class StateOtherObjectExchange implements IStateOfferExchange<ExchangeModel, Long, ModelMap, String, BindingResult>  {

    private OfferOtherObjectCategoryManager offerOtherObjectCategoryManager;
    
    private OfferOtherObjectManager offerOtherObjectManager;
    
    private OfferManager offerManager;
    
    private OfferTypeManager offerTypeManager;
    
    private final String NAME = "other";
    
    private final String PATH_VIEW = "OtherExchange";
    
    public String show(ModelMap m) {
        Collection<OfferEntity> allOffers = offerManager.findAllOtherOffer();
        Collection<OfferOtherObjectCategoryEntity> allCategories = offerOtherObjectCategoryManager.findAll();
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

    public @Autowired void setOfferOtherObjectCategoryManager(OfferOtherObjectCategoryManager offerOtherObjectCategoryManager) {
        this.offerOtherObjectCategoryManager = offerOtherObjectCategoryManager;
    }

    public @Autowired void setOfferOtherObjectManager(OfferOtherObjectManager offerOtherObjectManager) {
        this.offerOtherObjectManager = offerOtherObjectManager;
    }

    public @Autowired void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }

    public @Autowired void setOfferTypeManager(OfferTypeManager offerTypeManager) {
        this.offerTypeManager = offerTypeManager;
    }
    
    public String filter(String category, String type, ModelMap m) {
        m.addAttribute("type_p", "type="+type+"&");
        Collection<OfferEntity> allOffers = offerManager.findAllOtherOffer();
        Collection<OfferOtherObjectCategoryEntity> allCategories = offerOtherObjectCategoryManager.findAll();
        Collection<OfferTypeEntity> allTypes = offerTypeManager.findAll();
        m.addAttribute("allTypes", allTypes);
        m.addAttribute("allOffers", allOffers);
        m.addAttribute("allCategories", allCategories);
        return PATH_VIEW+"/show";
    }
    
    
}
