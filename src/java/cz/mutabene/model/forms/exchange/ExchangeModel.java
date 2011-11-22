/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.forms.exchange;

/**
 *
 * @author stenlik
 */
public class ExchangeModel {
    private String id;
    private String text;
    private String title;
    private String dateOfInsert;
    private String idUser;
    private String idOfferType;
    private String idOfferState;
    
    private String idCard;
    
    private String nameObject;
    private String descriptionObject;
    private String price;
    private String idObjectCategory;

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

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public String getDescriptionObject() {
        return descriptionObject;
    }

    public void setDescriptionObject(String descriptionObject) {
        this.descriptionObject = descriptionObject;
    }

    public String getIdObjectCategory() {
        return idObjectCategory;
    }

    public void setIdObjectCategory(String idObjectCategory) {
        this.idObjectCategory = idObjectCategory;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    
}
