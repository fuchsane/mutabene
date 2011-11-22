/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.forms.entity;

/**
 *
 * @author stenlik
 */
public class OfferOtherObjectModel {
    private String id;
    private String name;
    private String description;
    private String price;
    private String idOtherObjectCategory;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOtherObjectCategory() {
        return idOtherObjectCategory;
    }

    public void setIdOtherObjectCategory(String idOtherObjectCategory) {
        this.idOtherObjectCategory = idOtherObjectCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
}
