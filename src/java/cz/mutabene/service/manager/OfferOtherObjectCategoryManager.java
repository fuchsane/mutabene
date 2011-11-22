/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.OfferOtherObjectCategoryEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerObjectCategoryManager")
public class OfferOtherObjectCategoryManager extends GenericDataManager<OfferOtherObjectCategoryEntity> {

    public boolean add(OfferOtherObjectCategoryEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(OfferOtherObjectCategoryEntity object) {
        try{
            OfferOtherObjectCategoryEntity otherObjectCategory = findById(object.getId());
            otherObjectCategory.setName(object.getName());
            otherObjectCategory.setDescription(object.getDescription());
            otherObjectCategory.setOfferOtherObjectCategory(object.getOfferOtherObjectCategory());
            hibTempl.update(otherObjectCategory);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(OfferOtherObjectCategoryEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public OfferOtherObjectCategoryEntity findById(Long id) {
        try{
         List<OfferOtherObjectCategoryEntity> offerOtherObjectCategory = hibTempl.find("from OfferOtherObjectCategoryEntity as category where category.id like ? ", id);
         if(!offerOtherObjectCategory.isEmpty()){
         return offerOtherObjectCategory.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<OfferOtherObjectCategoryEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferOtherObjectCategoryEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferOtherObjectCategoryEntity> findAll() {
        return hibTempl.loadAll(OfferOtherObjectCategoryEntity.class);
    }
    
}
