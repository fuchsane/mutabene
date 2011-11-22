/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 *
 * @author stenlik
 */
@Entity
public class OfferOtherObjectEntity extends OfferObjectEntity {
    private OfferOtherObjectCategoryEntity offerOtherObjectCategory;
    private double price;

    @ManyToOne @PrimaryKeyJoinColumn
    public OfferOtherObjectCategoryEntity getOfferOtherObjectCategory() {
        return offerOtherObjectCategory;
    }

    public void setOfferOtherObjectCategory(OfferOtherObjectCategoryEntity offerOtherObjectCategory) {
        this.offerOtherObjectCategory = offerOtherObjectCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
