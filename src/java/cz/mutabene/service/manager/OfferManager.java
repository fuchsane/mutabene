/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.CardEntity;
import cz.mutabene.model.entity.OfferEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerManager")
public class OfferManager extends GenericDataManager<OfferEntity> {

    @Override
    public boolean add(OfferEntity object) {
        try {
            hibTempl.save(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(OfferEntity object) {
        try{
            OfferEntity offer = findById(object.getId());
            offer.setDateOfInsert(object.getDateOfInsert());
            offer.setOfferObject(object.getOfferObject());
            offer.setOfferState(object.getOfferState());
            offer.setOfferType(object.getOfferType());
            offer.setText(object.getText());
            offer.setTitle(object.getTitle());
            offer.setUser(object.getUser());
            hibTempl.update(offer);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(OfferEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public OfferEntity findById(Long id) {
        try{
         List<OfferEntity> offer = hibTempl.find("from OfferEntity as offer where offer.id like ? ", id);
         if(!offer.isEmpty()){
         return offer.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<OfferEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferEntity> findAll() {
        return hibTempl.loadAll(OfferEntity.class);
    }
    
    public Collection<OfferEntity> findAllCardOffer(){
        return hibTempl.find("select o from OfferEntity as o, CardEntity as c where o.offerObject.id = c.id");
    }
    
    public Collection<OfferEntity> findAllOtherOffer(){
        return hibTempl.find("select o from OfferEntity as o, OfferOtherObjectEntity as oo where o.offerObject.id = oo.id");
    }
    
    public Collection<OfferEntity> findByCardCategoryId(Long idCardCategory){
        List<OfferEntity> result = new LinkedList<OfferEntity>();
        Collection<Long> ids = getCategoryIds(idCardCategory);
        Collection<OfferEntity> tmp = new LinkedList<OfferEntity>();
        for(Long l:ids){
            tmp = hibTempl.find("select o from OfferEntity as o where o.offerObject.cardCategoryEntity.id like ?", l);
            result.addAll(tmp);
        }
        return result;
    }
    
    public Collection<OfferEntity> findByOfferTypeId(Long idOfferType){
        return hibTempl.find("select o from OfferEntity as o where o.offerType.id like ?", idOfferType);
    }
    
    public Collection<OfferEntity> findByCardCategoryAndOfferTypeId(Long idCardCategory, Long idOfferType){
        List<OfferEntity> result = new LinkedList<OfferEntity>();
        Collection<Long> ids = getCategoryIds(idCardCategory);
        Collection<OfferEntity> tmp = new LinkedList<OfferEntity>();
        for(Long l:ids){
            tmp = hibTempl.find("select o from OfferEntity as o where o.offerObject.cardCategoryEntity.id like ? and o.offerType.id like ?",l, idOfferType);
            result.addAll(tmp);
        }
        return result;
    }
    
    private Collection<Long> getCategoryIds(Long id){
        List<CardEntity> result = new ArrayList<CardEntity>();
        Collection<CardCategoryEntity> categories = hibTempl.loadAll(CardCategoryEntity.class);
        CardCategoryEntity cat = (CardCategoryEntity) hibTempl.find("from CardCategoryEntity as cat where cat.id like ? ", id).get(0);
        Collection<Long> subCatId = new ArrayList<Long>();
        if(null == cat.getCardCategory()){
            for(CardCategoryEntity c: categories){
                if(null != c.getCardCategory() && c.getCardCategory().getId().longValue() == id){
                    subCatId.add(c.getId());
                }
            }
            return subCatId;
        } else {
            subCatId.add(id);
            return subCatId;
        }
    }
    }

