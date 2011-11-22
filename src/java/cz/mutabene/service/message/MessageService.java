/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.message;

import cz.mutabene.service.manager.OfferManager;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service("messageService")
public class MessageService implements Serializable {
    
    private OfferManager offerManager;

    public String getCount(){
        try{
            return "{"+String.valueOf(offerManager.findAll().size())+"}";
        }catch(Exception e){
            return "{ :-( }";
        }      
    }

    public @Autowired void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }    
}
