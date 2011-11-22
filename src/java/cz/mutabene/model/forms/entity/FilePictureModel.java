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
public class FilePictureModel {
    private String id;
    private String name;
    private String size;
    private CommonsMultipartFile filePicture;

    public CommonsMultipartFile getFilePicture() {
        return filePicture;
    }

    public void setFilePicture(CommonsMultipartFile filePicture) {
        this.filePicture = filePicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    
}
