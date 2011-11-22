/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.OfferOtherObjectEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerOtherObjectManager")
public class OfferOtherObjectManager extends GenericDataManager<OfferOtherObjectEntity> {

    public boolean add(OfferOtherObjectEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(OfferOtherObjectEntity object) {
        try{
            OfferOtherObjectEntity otherObject = findById(object.getId());
            otherObject.setName(object.getName());
            otherObject.setDescription(object.getDescription());
            otherObject.setOfferOtherObjectCategory(object.getOfferOtherObjectCategory());
            otherObject.setPrice(object.getPrice());
            hibTempl.update(otherObject);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(OfferOtherObjectEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public OfferOtherObjectEntity findById(Long id) {
        try{
         List<OfferOtherObjectEntity> offerOtherObject = hibTempl.find("from OfferOtherObjectEntity as otherObject where otherObject.id like ? ", id);
         if(!offerOtherObject.isEmpty()){
         return offerOtherObject.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<OfferOtherObjectEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferOtherObjectEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferOtherObjectEntity> findAll() {
        return hibTempl.loadAll(OfferOtherObjectEntity.class);
    }
    
}
