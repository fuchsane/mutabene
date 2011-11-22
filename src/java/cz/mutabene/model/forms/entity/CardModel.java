/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.model.forms.entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author stenlik
 */
public class CardModel {
    private String id;
    private String name;
    private String description;
    private String price;
    private String idCardCategory;
    private String serialNumber;
    private CommonsMultipartFile image;
    private boolean delete;
    private String idFilePicture;
    private boolean picture;

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

    public String getIdCardCategory() {
        return idCardCategory;
    }

    public void setIdCardCategory(String idCardCategory) {
        this.idCardCategory = idCardCategory;
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

    public CommonsMultipartFile getImage() {
        return image;
    }

    public void setImage(CommonsMultipartFile image) {
        this.image = image;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getIdFilePicture() {
        return idFilePicture;
    }

    public void setIdFilePicture(String idFilePicture) {
        this.idFilePicture = idFilePicture;
    }

    public boolean isPicture() {
        return picture;
    }

    public void setPicture(boolean picture) {
        this.picture = picture;
    }




    
    
}
