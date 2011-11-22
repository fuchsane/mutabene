/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.forms.entity;

/**
 *
 * @author stenlik
 */
public class OfferModel {
    private String id;
    private String text;
    private String title;
    private String dateOfInsert;
    private String idUser;
    private String idOfferType;
    private String idOfferState;
    private String idOfferObject;

    public String getDateOfInsert() {
        return dateOfInsert;
    }

    public void setDateOfInsert(String dateOfInsert) {
        this.dateOfInsert = dateOfInsert;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOfferObject() {
        return idOfferObject;
    }

    public void setIdOfferObject(String idOfferObject) {
        this.idOfferObject = idOfferObject;
    }

    public String getIdOfferState() {
        return idOfferState;
    }

    public void setIdOfferState(String idOfferState) {
        this.idOfferState = idOfferState;
    }

    public String getIdOfferType() {
        return idOfferType;
    }

    public void setIdOfferType(String idOfferType) {
        this.idOfferType = idOfferType;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
