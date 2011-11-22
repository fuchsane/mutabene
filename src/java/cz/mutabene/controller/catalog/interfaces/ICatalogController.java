/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.catalog.interfaces;

/**
 *
 * @author stenlik
 */
public interface ICatalogController <ENTITY, ID, MODEL_MAP, RETURN, BINDING_RESULT> {
    
    public RETURN show(MODEL_MAP m);
    
    public RETURN add(ENTITY formModel, MODEL_MAP m);
    
    public RETURN edit(ID id, MODEL_MAP m);
    
    public RETURN delete(ID id, MODEL_MAP m);
    
    public RETURN submit(ENTITY formModel, BINDING_RESULT result, MODEL_MAP m );
    
    public RETURN save(ENTITY formModel, BINDING_RESULT result, MODEL_MAP m);
    
}