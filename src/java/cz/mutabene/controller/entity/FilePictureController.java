/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.FilePictureEntity;
import cz.mutabene.model.forms.entity.FilePictureModel;
import cz.mutabene.service.manager.FilePictureManager;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping(value = "admin/filePicture/")
public class FilePictureController implements IEntityUseController<FilePictureModel, Long, ModelMap, String, BindingResult> {

    private FilePictureManager filePictureManager;
    private final String PATH_VIEW = "Entity/FilePicture";

    @RequestMapping(value = "show.htm", method = RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<FilePictureEntity> allFiles = filePictureManager.findAll();
        m.addAttribute("allFiles", allFiles);
        return PATH_VIEW + "/show";
    }

    @RequestMapping(value = "add.htm", method = RequestMethod.GET)
    public String add(FilePictureModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        return PATH_VIEW + "/add";
    }

    @RequestMapping(value = "edit.htm", method = RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        FilePictureEntity file;
        try{
            file = filePictureManager.findById(id);
            file.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        FilePictureModel formModel = new FilePictureModel();
        formModel.setId(file.getId().toString());
        formModel.setName(file.getName());
        formModel.setSize(file.getFileSize().toString());
        m.addAttribute("formModel", formModel);
        
        return PATH_VIEW+"/edit";
    }

    @RequestMapping(value = "delete.htm", method = RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        FilePictureEntity file;
        try{
            file = filePictureManager.findById(id);
            file.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        filePictureManager.delete(file);
        return "redirect:show.htm";
    }

    @RequestMapping(value = "submit.htm", method = RequestMethod.POST)
    public String submit(FilePictureModel formModel, BindingResult result, ModelMap m) {
        FilePictureEntity file = new FilePictureEntity();
        byte[] blob = formModel.getFilePicture().getBytes();
        file.setName(formModel.getFilePicture().getOriginalFilename());
        file.setFileSize((long) blob.length);
        file.setPicture(blob);
        file.setContentType(formModel.getFilePicture().getContentType());
        filePictureManager.add(file);
        return "redirect:show.htm";
    }

    @RequestMapping(value = "save.htm", method = RequestMethod.POST)
    public String save(FilePictureModel formModel, BindingResult result, ModelMap m) {
        FilePictureEntity file;
        try{
            Long id = Long.parseLong(formModel.getId());
            file = filePictureManager.findById(id);
            file.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        file.setName(formModel.getFilePicture().getOriginalFilename());
        file.setContentType(formModel.getFilePicture().getContentType());
        file.setPicture(formModel.getFilePicture().getBytes());
        file.setFileSize(formModel.getFilePicture().getSize());
        filePictureManager.update(file);
        
        return "redirect:show.htm";
    }

    @RequestMapping(value = "download.htm", method = RequestMethod.GET)
    public String download(@RequestParam(value = "id") Long id, HttpServletResponse response, ModelMap m) {
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
            return "redirect:show.htm"; //odkazat na error page
        } catch (SQLException e) {
            return "redirect:show.htm";
        } catch (Exception e) {
            return "redirect:show.htm";
        }

        return null;
    }

    public @Autowired
    void setFilePictureManager(FilePictureManager filePictureManager) {
        this.filePictureManager = filePictureManager;
    }
}
