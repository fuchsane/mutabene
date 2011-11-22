/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.mutabene.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Anysek
 */
@Entity
@Table(name="CARDS")
public class CardEntity extends OfferObjectEntity {
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private double price;
    private CardCategoryEntity cardCategoryEntity;
    private FilePictureEntity filePicture;
    private boolean picture;
    

    @Column(name = "SERIAL_NUMBER")
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne @PrimaryKeyJoinColumn
    public CardCategoryEntity getCardCategoryEntity() {
        return cardCategoryEntity;
    }

    public void setCardCategoryEntity(CardCategoryEntity cardCategoryEntity) {
        this.cardCategoryEntity = cardCategoryEntity;
    }

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY) @PrimaryKeyJoinColumn //cascade all
    public FilePictureEntity getFilePicture() {
        return filePicture;
    }

    public void setFilePicture(FilePictureEntity filePicture) {
        this.filePicture = filePicture;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }


}
