/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.entity;

import cz.mutabene.controller.entity.interfaces.IEntityUseController;
import cz.mutabene.model.forms.entity.AddressModel;
import cz.mutabene.model.entity.AddressEntity;
import cz.mutabene.model.entity.RegionEntity;
import cz.mutabene.service.manager.AddressManager;
import cz.mutabene.service.manager.RegionManager;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author stenlik
 */
@Controller
@RequestMapping(value = "admin/address/")
public class AddressController implements IEntityUseController<AddressModel, Long, ModelMap, String, BindingResult> {

    private AddressManager addressManager;
    
    private RegionManager regionManager;
    
    private final String PATH_VIEW = "Entity/Address";

    private Map<String, String> getListRegions() {
        Collection<RegionEntity> allRegions = regionManager.findAll();
        Map<String, String> listRegion = new LinkedHashMap<String, String>();
        for (RegionEntity r : allRegions) {
            if (r.getRegion() == null) {
                listRegion.put("parent", r.getName());
                for (RegionEntity r2 : allRegions) {
                    if (null != r2.getRegion() && r.getId() == r2.getRegion().getId() && r2.getRegion().getId() != r2.getId()) {
                        listRegion.put(r2.getId().toString(), "   - " + r2.getName() + " -");
                    }
                }
            }
        }
        return listRegion;
    }

    @RequestMapping(value = "show.htm", method = RequestMethod.GET)
    public String show(ModelMap m) {
        Collection<AddressEntity> allAddress = addressManager.findAll();
        m.addAttribute("allAddress", allAddress);
        return PATH_VIEW + "/show";
    }

    @RequestMapping(value = "add.htm", method = RequestMethod.GET)
    public String add(AddressModel formModel, ModelMap m) {
        m.addAttribute("formModel", formModel);
        m.addAttribute("listRegions", getListRegions());
        return PATH_VIEW + "/add";
    }

    @RequestMapping(value = "edit.htm", method = RequestMethod.GET)
    public String edit(Long id, ModelMap m) {
        AddressEntity addressModel;
        try {
            addressModel = addressManager.findById(id);
            addressModel.getId();
        } catch (Exception e) {
            return "redirect:show.htm";
        }

        AddressModel formModel = new AddressModel();
        formModel.setId(addressModel.getId().toString());
        formModel.setCity(addressModel.getCity());
        formModel.setStreet(addressModel.getStreet());
        formModel.setZipCode(addressModel.getZipCode());
        try{
            formModel.setIdRegion(addressModel.getRegion().getId().toString());
        }catch(Exception e){
            
        }
        m.addAttribute("formModel", formModel);
        m.addAttribute("listRegions", getListRegions());

        return PATH_VIEW + "/edit";
    }

    @RequestMapping(value = "delete.htm", method = RequestMethod.GET)
    public String delete(Long id, ModelMap m) {
        AddressEntity toDelete;
        try {
            toDelete = addressManager.findById(id);
            toDelete.getId();
        } catch (Exception e) {
            return "redirect:show.htm";
        }
        addressManager.delete(toDelete);
        return "redirect:show.htm";
    }

    @RequestMapping(value = "save.htm", method = RequestMethod.POST)
    public String save(AddressModel formModel, BindingResult result, ModelMap m) {
        AddressEntity addressOrig;
        RegionEntity addressRegion;

        try {
            Long id = Long.parseLong(formModel.getId());
            addressOrig = addressManager.findById(id);
            addressOrig.setCity(formModel.getCity());
            addressOrig.setStreet(formModel.getStreet());
            addressOrig.setZipCode(formModel.getZipCode());
        } catch (Exception e) {
            return "redirect:show.htm";
        }
        try {
            Long id = Long.parseLong(formModel.getIdRegion());
            addressRegion = regionManager.findById(id);
            addressOrig.setRegion(addressRegion);
        } catch (Exception e) {
            addressOrig.setRegion(null);
        }

        addressManager.update(addressOrig);

        Collection<AddressEntity> allAddress = addressManager.findAll();
        m.addAttribute("allAddress", allAddress);

        return "redirect:show.htm";
    }

    @RequestMapping(value = "submit.htm", method = RequestMethod.POST)
    public String submit(AddressModel formModel, BindingResult result, ModelMap m) {
        AddressEntity address = new AddressEntity();
        address.setCity(formModel.getCity());
        address.setStreet(formModel.getStreet());
        address.setZipCode(formModel.getZipCode());

        RegionEntity region;
        try {
            Long id = Long.parseLong(formModel.getIdRegion());
            region = regionManager.findById(id);
        } catch (Exception e) {
            region = null;
        }
        address.setRegion(region);

        addressManager.add(address);

        Collection<AddressEntity> allAddress = addressManager.findAll();
        m.addAttribute("allAddress", allAddress);

        return "redirect:show.htm";
    }

    public @Autowired
    void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    public @Autowired
    void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
}
