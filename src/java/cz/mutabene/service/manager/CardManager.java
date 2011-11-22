/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.CardEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("cardManager")
public class CardManager extends GenericDataManager<CardEntity> {

    @Override
    public boolean add(CardEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
        return false;
        }
    }

    @Override
    public boolean update(CardEntity object) {
        
       try{
            CardEntity card = findById(object.getId());
            card.setName(object.getName());
            card.setDescription(object.getDescription());
            card.setPrice(object.getPrice());
            card.setSerialNumber(object.getSerialNumber());
            card.setPicture(object.isPicture());
            card.setFilePicture(object.getFilePicture());
            card.setCardCategoryEntity(object.getCardCategoryEntity());
            hibTempl.update(card);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(CardEntity object) {
          try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public CardEntity findById(Long id) {
        try{
         List<CardEntity> cardCategory = hibTempl.find("from CardEntity as card where card.id like ? ", id);
         if(!cardCategory.isEmpty()){
         return cardCategory.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<CardEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<CardEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<CardEntity> findAll() {
        return hibTempl.loadAll(CardEntity.class);
    }
    
    public Collection<CardEntity> findByCategory(Long id){
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
        for(Long l: subCatId){
        Collection<CardEntity> tmp = hibTempl.find("from CardEntity as card where card.cardCategoryEntity.id like ?", l);
        result.addAll(tmp);
        }
        } else {
        result = hibTempl.find("from CardEntity as card where card.cardCategoryEntity.id like ?", id);
        }
        return result;
    }
}
