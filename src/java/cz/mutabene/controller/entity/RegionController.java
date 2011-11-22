/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.model.forms.entity.RegionModel;
import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.entity.RegionEntity;
import cz.mutabene.service.manager.RegionManager;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping("admin/region/")
public class RegionController implements IEntityUseController<RegionModel, Long, ModelMap, String, BindingResult> {

    
    RegionManager regionManager;
    
    private final String PATH_VIEW = "Entity/Region";
    
    private Map<String, String> getListRegions(){
    Collection<RegionEntity> allRegions = regionManager.findAll();
        Map<String, String>  listRegion = new LinkedHashMap<String, String>();
        for(RegionEntity r: allRegions){
            listRegion.put(r.getId().toString(), r.getName());
        }
    return listRegion;
    }
    
    @RequestMapping(value="show.htm", method= RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<RegionEntity> allRegions = regionManager.findAll();
        m.addAttribute("allRegions", allRegions);
        return PATH_VIEW+"/show";
    }
    
    @RequestMapping(value="add.htm", method= RequestMethod.GET)
    public String add(RegionModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listRegions", getListRegions());
        return PATH_VIEW+"/add";
    }

    @RequestMapping(value="edit.htm", method= RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        RegionEntity region;
        try{
            region = regionManager.findById(id);
            region.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        RegionModel formModel = new RegionModel();
        formModel.setName(region.getName());
        
        try{
        formModel.setId(region.getRegion().getId().toString());
        } catch (Exception e){
        
        }
        formModel.setIdOrig(region.getId().toString());

        m.addAttribute("listRegions", getListRegions());
        m.addAttribute("formModel", formModel);
            return PATH_VIEW+"/edit";
    }

    @RequestMapping(value="delete.htm", method= RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        RegionEntity region;
        try{
            region = regionManager.findById(id);
            region.getId();
        }catch(Exception e){
            return "redirect:show.htm";
        }
        
        boolean isSingle = true; 
        Collection<RegionEntity> allRegions = regionManager.findAll();               
            for(RegionEntity r: allRegions){               
                if(r != null && r.getRegion() != null){                  
                    if(id.equals(r.getRegion().getId())){
                        isSingle = false;
                    }
                }
            }
        if(isSingle){
        regionManager.delete(region);
        } else {
        //error, nelze smazat, vazane objekty
        }        
        return "redirect:show.htm";
    }

    @RequestMapping(value="submit.htm", method= RequestMethod.POST)
    public String submit(RegionModel formModel, BindingResult result, ModelMap m) {
        RegionEntity region = new RegionEntity();
        region.setName(formModel.getName());
        try{
            Long id = Long.parseLong(formModel.getId());
            RegionEntity parent = regionManager.findById(id);
            region.setRegion(parent);
        } catch (Exception e){
            region.setRegion(null);
        }

        regionManager.add(region);

        return "redirect:show.htm";
    }
    
    @RequestMapping(value="save.htm", method= RequestMethod.POST)
    public String save(RegionModel formModel, BindingResult result, ModelMap m) {
        RegionEntity regionOrig;
        RegionEntity parent;
        
        try{
            Long id = Long.parseLong(formModel.getIdOrig());
            regionOrig = regionManager.findById(id);          
            regionOrig.getId();
        } catch (Exception e){
            return "redirect:show.htm";
        }
        
        regionOrig.setName(formModel.getName());
        
        try{
            Long idParent = Long.parseLong(formModel.getId());
            parent = regionManager.findById(idParent);
            if(parent.getId().longValue() != regionOrig.getId().longValue()){
            regionOrig.setRegion(parent);
            } else {
            regionOrig.setRegion(null);
            }
        } catch (Exception e){
            regionOrig.setRegion(null);
        }
            regionManager.update(regionOrig); //TODO pridat validator

            return "redirect:show.htm";
    }
    
    public @Autowired void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
}
