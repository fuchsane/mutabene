/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.OfferObjectEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerObjectManager")
public class OfferObjectManager extends GenericDataManager<OfferObjectEntity> {

    public boolean add(OfferObjectEntity object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean update(OfferObjectEntity object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean delete(OfferObjectEntity object) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public OfferObjectEntity findById(Long id) {
     try{
         List<OfferObjectEntity> offerObject = hibTempl.find("from OfferObjectEntity as offerObject where offerObject.id like ? ", id);
         if(!offerObject.isEmpty()){
         return offerObject.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<OfferObjectEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferObjectEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<OfferObjectEntity> findAll() {
        return hibTempl.loadAll(OfferObjectEntity.class);
    }
    
}
