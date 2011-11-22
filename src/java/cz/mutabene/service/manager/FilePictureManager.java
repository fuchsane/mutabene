/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.service.manager;

import cz.mutabene.model.entity.FilePictureEntity;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author stenlik
 */
@Service(value="filePictureManager")
public class FilePictureManager extends GenericDataManager<FilePictureEntity> {

    public boolean add(FilePictureEntity object) {
        try{
        hibTempl.save(object);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(FilePictureEntity object) {
                try{
            FilePictureEntity filePictures = findById(object.getId());
            filePictures.setName(object.getName());
            filePictures.setFileSize(object.getFileSize());
            filePictures.setPicture(object.getPicture());
            filePictures.setContentType(object.getContentType());
            hibTempl.update(filePictures);
            hibTempl.flush();
            return true;   
        } catch (Exception e){
            return false;
        }
    }

    public boolean delete(FilePictureEntity object) {
        try{
            hibTempl.delete(object);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public FilePictureEntity findById(Long id) {
        try{
         List<FilePictureEntity> filePictures = hibTempl.find("from FilePictureEntity as filePicture where filePicture.id like ? ", id);
         if(!filePictures.isEmpty()){
         return filePictures.get(0);
         } else {
         return null;
         }
        } catch (Exception e){
            return null;
        }
    }

    public Collection<FilePictureEntity> find(String text) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<FilePictureEntity> findInterval(Integer from, Integer count) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Collection<FilePictureEntity> findAll() {
        return hibTempl.loadAll(FilePictureEntity.class);
    }
    
}
