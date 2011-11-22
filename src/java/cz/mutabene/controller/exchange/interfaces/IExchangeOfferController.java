/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.exchange.interfaces;

import cz.mutabene.model.entity.AddressEntity;
import java.io.Serializable;
import org.springframework.ui.ModelMap;

/**
 *
 * @author stenlik
 */
public interface IExchangeOfferController<ENTITY, ID, MODEL_MAP, RETURN, BINDING_RESULT, PARAMETER> {
    
    public RETURN show(PARAMETER burza, MODEL_MAP m);
    
    public RETURN add(PARAMETER burza, ENTITY formModel, MODEL_MAP m);
    
    public RETURN edit(PARAMETER burza, ID id, MODEL_MAP m);
    
    public RETURN delete(PARAMETER burza, ID id, MODEL_MAP m);
    
    public RETURN submit(PARAMETER burza, ENTITY formModel, BINDING_RESULT result, MODEL_MAP m );
    
    public RETURN save(PARAMETER burza, ENTITY formModel, BINDING_RESULT result, MODEL_MAP m);
    
    public RETURN filter(PARAMETER burza,PARAMETER category, PARAMETER type, ModelMap m);
    
}