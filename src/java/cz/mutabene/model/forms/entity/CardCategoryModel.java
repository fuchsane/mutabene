/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.forms.entity;

/**
 *
 * @author stenlik
 */
public class CardCategoryModel {
    private String id;
    private String idCardCategory;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCardCategory() {
        return idCardCategory;
    }

    public void setIdCardCategory(String idCardCategory) {
        this.idCardCategory = idCardCategory;
    }
    
    
    
}
