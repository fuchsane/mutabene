/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.CardCategoryEntity;
import java.util.Collection;
import java.util.List;
import org.hibernate.ObjectDeletedException;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("cardCategoryManager")
public class CardCategoryManager extends GenericDataManager<CardCategoryEntity> {

    public boolean add(CardCategoryEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(CardCategoryEntity object) {
        try{
            CardCategoryEntity cardCategory = findById(object.getId());
            cardCategory.setName(object.getName());
            cardCategory.setDescription(object.getDescription());
            cardCategory.setCardCategory(object.getCardCategory());
            hibTempl.update(cardCategory);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(CardCategoryEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public CardCategoryEntity findById(Long id) {
        try{
         List<CardCategoryEntity> cardCategory = hibTempl.find("from CardCategoryEntity as cardCategory where cardCategory.id like ? ", id);
         if(!cardCategory.isEmpty()){
         return cardCategory.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<CardCategoryEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<CardCategoryEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<CardCategoryEntity> findAll() {
        return hibTempl.loadAll(CardCategoryEntity.class);
    }
    
}
