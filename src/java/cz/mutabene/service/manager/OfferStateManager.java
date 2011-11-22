/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.OfferStateEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("offerStateManager")
public class OfferStateManager extends GenericDataManager<OfferStateEntity> {

    @Override
    public boolean add(OfferStateEntity object) {
        try {
            hibTempl.save(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean update(OfferStateEntity object) {
        try{
            OfferStateEntity state = findById(object.getId());
            state.setName(object.getName());
            state.setDescription(object.getDescription());
            hibTempl.update(state);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(OfferStateEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public OfferStateEntity findById(Long id) {
        try{
         List<OfferStateEntity> state = hibTempl.find("from OfferStateEntity as state where state.id like ? ", id);
         if(!state.isEmpty()){
         return state.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Collection<OfferStateEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferStateEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<OfferStateEntity> findAll() {
        return hibTempl.loadAll(OfferStateEntity.class);
    }
    
}
