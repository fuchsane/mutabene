/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.image;


import cz.mutabene.model.entity.FilePictureEntity;
import cz.mutabene.service.manager.FilePictureManager;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping("images/")
public class ImageController {
    
    private FilePictureManager filePictureManager;
    
    @RequestMapping(value = "download.htm", method = RequestMethod.GET)
    public String download(Long id, HttpServletResponse response, ModelMap m) {
        FilePictureEntity file = filePictureManager.findById(id);

        try {
            response.setHeader("Content-Disposition", "inline;filename=\"" + file.getName() + "\"");
            OutputStream out = response.getOutputStream();
            response.setContentType(file.getContentType());
            Blob blob = Hibernate.createBlob(file.getPicture());
            IOUtils.copy(blob.getBinaryStream(), out);
            out.flush();
            out.close();
        } catch (IOException e) {
            return null; //odkazat na error page
        } catch (SQLException e) {
            return null;
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    public @Autowired void setFilePictureManager(FilePictureManager filePictureManager) {
        this.filePictureManager = filePictureManager;
    }
    
    
    
}
