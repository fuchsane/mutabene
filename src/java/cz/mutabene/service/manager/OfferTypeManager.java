/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.OfferTypeEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerTypeManager")
public class OfferTypeManager extends GenericDataManager<OfferTypeEntity> {

    @Override
    public boolean add(OfferTypeEntity object) {
        try {
            hibTempl.save(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(OfferTypeEntity object) {try{
            OfferTypeEntity type = findById(object.getId());
            type.setName(object.getName());
            type.setDescription(object.getDescription());
            hibTempl.update(type);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(OfferTypeEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public OfferTypeEntity findById(Long id) {
        try{
         List<OfferTypeEntity> type = hibTempl.find("from OfferTypeEntity as type where type.id like ? ", id);
         if(!type.isEmpty()){
         return type.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<OfferTypeEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferTypeEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferTypeEntity> findAll() {
        return hibTempl.loadAll(OfferTypeEntity.class);
    }
    
}
