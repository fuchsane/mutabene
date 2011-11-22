/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.exchange.state.interfaces;

import org.springframework.ui.ModelMap;

/**
 *
 * @author stenlik
 */
public interface IStateOfferExchange<ENTITY, ID, MODEL_MAP, RETURN, BINDING_RESULT> {
    
    public RETURN show(MODEL_MAP m);
    
    public RETURN add(ENTITY formModel, MODEL_MAP m);
    
    public RETURN edit(ID id, MODEL_MAP m);
    
    public RETURN delete(ID id, MODEL_MAP m);
    
    public RETURN submit(ENTITY formModel, BINDING_RESULT result, MODEL_MAP m );
    
    public RETURN save(ENTITY formModel, BINDING_RESULT result, MODEL_MAP m);
    
    public RETURN filter(String category, String type, ModelMap m);
    
    public boolean support(String name);
}