/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.home;

import cz.mutabene.model.entity.AddressEntity;
import cz.mutabene.model.entity.CardCategoryEntity;
import cz.mutabene.model.entity.CardEntity;
import cz.mutabene.model.entity.CenterEntity;
import cz.mutabene.model.entity.GenderEntity;
import cz.mutabene.model.entity.OfferEntity;
import cz.mutabene.model.entity.OfferOtherObjectCategoryEntity;
import cz.mutabene.model.entity.OfferOtherObjectEntity;
import cz.mutabene.model.entity.OfferStateEntity;
import cz.mutabene.model.entity.OfferTypeEntity;
import cz.mutabene.model.entity.RegionEntity;
import cz.mutabene.model.entity.UserEntity;
import cz.mutabene.model.entity.UserRoleEntity;
import cz.mutabene.service.manager.AddressManager;
import cz.mutabene.service.manager.CardCategoryManager;
import cz.mutabene.service.manager.CardManager;
import cz.mutabene.service.manager.CenterManager;
import cz.mutabene.service.manager.OfferManager;
import cz.mutabene.service.manager.OfferOtherObjectCategoryManager;
import cz.mutabene.service.manager.OfferOtherObjectManager;
import cz.mutabene.service.manager.OfferStateManager;
import cz.mutabene.service.manager.OfferTypeManager;
import cz.mutabene.service.manager.RegionManager;
import cz.mutabene.service.manager.UserManager;
import cz.mutabene.service.manager.UserRoleManager;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 *
 * @author stenlik
 */
@Controller("homeController")
public class HomeController {
    
    private CenterManager centerManager;
    
    private RegionManager regionManager;
    
    private AddressManager addressManager;
    
    private UserRoleManager userRoleManager;
    
    private UserManager userManager;
    
    private CardManager cardManager;
    
    private OfferTypeManager offerTypeManager;
    
    private OfferStateManager offerStateManager;
    
    private OfferManager offerManager;
    
    private OfferOtherObjectManager offerOtherObjectManager;
    
    private OfferOtherObjectCategoryManager offerOtherObjectCategoryManager;
    
    private CardCategoryManager cardCategoryManager;
    
    @RequestMapping(value="/index.htm", method=RequestMethod.GET)
    public String index() throws Exception{
        return "Home/index";
    }
    
    @RequestMapping(value="/about.htm", method=RequestMethod.GET)
    public String about() throws Exception{
        return "Home/about";
    }
    
    @RequestMapping(value="/init.htm", method=RequestMethod.GET)
    public String init() throws Exception{
    RegionEntity praha = new RegionEntity();
    praha.setName("Praha");
    praha.setRegion(null);
    regionManager.add(praha);
    RegionEntity praha1 = new RegionEntity();
    praha1.setName("Praha 1");
    praha1.setRegion(praha);
    regionManager.add(praha1);
    
    AddressEntity adresa = new AddressEntity();
    adresa.setCity("Praha");
    adresa.setStreet("Vodičkova 6");
    adresa.setZipCode("110 00");
    adresa.setRegion(praha1);
    addressManager.add(adresa);
    
    CenterEntity centrum = new CenterEntity();
    centrum.setName("Centrum 1");
    centrum.setDescription("Centrum pro rodiny");
    centrum.setAddress(adresa);
    centerManager.add(centrum);
    
    UserRoleEntity role1 = new UserRoleEntity("ROLE_ADMIN", "Administrator");
    UserRoleEntity role2 = new UserRoleEntity("ROLE_USER", "Uzivatel");
    userRoleManager.add(role1);
    userRoleManager.add(role2);
    
    UserEntity user = new UserEntity();
    user.setActive(false);
    user.setFirstname("František");
    user.setSurname("Hruška");
    user.setEmail("frantisek.h@seznam.cz");
    user.setGender(GenderEntity.MALE);
    user.setTelephoneNumber("737323111");
    user.setPassword("heslo");
    Set<UserRoleEntity> roles = new LinkedHashSet<UserRoleEntity>();
    roles.add(role1);
    roles.add(role2);
    user.setRoleIT(roles);
    user.setAddress(adresa);
    user.setCenter(centrum);
    userManager.add(user);
    
    user = new UserEntity();
    user.setActive(false);
    user.setFirstname("Petr");
    user.setSurname("Pavlik");
    user.setEmail("pavlipe7@gmail.com");
    user.setGender(GenderEntity.MALE);
    user.setTelephoneNumber("737323111");
    user.setPassword("heslo");
    roles = new LinkedHashSet<UserRoleEntity>();
    roles.add(role1);
    roles.add(role2);
    user.setRoleIT(roles);
    user.setAddress(adresa);
    user.setCenter(centrum);
    userManager.add(user);
    
    OfferTypeEntity type1 = new OfferTypeEntity();
    type1.setName("PRODÁM");
    type1.setDescription("Inzerát je nabízen k prodeji.");
    offerTypeManager.add(type1);
    
    OfferTypeEntity type2 = new OfferTypeEntity();
    type2.setName("KOUPÍM");
    type2.setDescription("Inzerát je jako poptávka po zboží.");
    offerTypeManager.add(type2);
    
    OfferTypeEntity type3 = new OfferTypeEntity();
    type3.setName("PŮJČÍM");
    type3.setDescription("Inzerát je jako nabídka k půjčení.");
    offerTypeManager.add(type3);
    
    OfferTypeEntity type4 = new OfferTypeEntity();
    type4.setName("CHCI SI PŮJČIT");
    type4.setDescription("Inzerát je jako poptávka o půjčení.");
    offerTypeManager.add(type4);
    
    OfferTypeEntity type5 = new OfferTypeEntity();
    type5.setName("VYMĚNÍM");
    type5.setDescription("Inzerát je jako směna.");
    offerTypeManager.add(type5);
    
    OfferStateEntity state1 = new OfferStateEntity();
    state1.setName("OTEVŘENO");
    state1.setDescription("Inzerát byl vložen a je aktivní.");
    offerStateManager.add(state1);
    
    OfferStateEntity state2 = new OfferStateEntity();
    state2.setName("UZAVŘENO");
    state2.setDescription("Inzerát byl uzavřen. Došlo k obchodu, není aktivní.");
    offerStateManager.add(state2);
    
    OfferOtherObjectCategoryEntity cat1 = new OfferOtherObjectCategoryEntity();
    cat1.setName("DOMÁCÍ VĚCI");
    cat1.setDescription("Domácí potřeby, kuchyň, koupelny ...");
    offerOtherObjectCategoryManager.add(cat1);
    
    OfferOtherObjectEntity obj = new OfferOtherObjectEntity();
    obj.setName("Kočárek");
    obj.setDescription("Dětský kočárek pro děti do 2 let. Používaný 3 roky v zachovalém stavu.");
    obj.setOfferOtherObjectCategory(cat1);
    obj.setPrice(1500.0);
    offerOtherObjectManager.add(obj);
    
    CardCategoryEntity ckat1 = new CardCategoryEntity();
    ckat1.setName("Kategorie 1");
    ckat1.setCardCategory(null);
    cardCategoryManager.add(ckat1);
    
    CardCategoryEntity ckat2 = new CardCategoryEntity();
    ckat2.setName("Kategorie 2");
    ckat2.setCardCategory(null);
    cardCategoryManager.add(ckat2);
    
    CardCategoryEntity ckat11 = new CardCategoryEntity();
    ckat11.setName("Kategorie 1.1");
    ckat11.setCardCategory(ckat1);
    cardCategoryManager.add(ckat11);
    
    CardCategoryEntity ckat12 = new CardCategoryEntity();
    ckat12.setName("Kategorie 1.2");
    ckat12.setCardCategory(ckat1);
    cardCategoryManager.add(ckat12);
    
    CardCategoryEntity ckat21 = new CardCategoryEntity();
    ckat21.setName("Kategorie 2.1");
    ckat21.setCardCategory(ckat2);
    cardCategoryManager.add(ckat21);
    
    CardCategoryEntity ckat22 = new CardCategoryEntity();
    ckat22.setName("Kategorie 2.2");
    ckat22.setCardCategory(ckat2);
    cardCategoryManager.add(ckat22);
    
    CardEntity card1 = new CardEntity();
    card1.setName("KARTA 1 (1.1)");
    card1.setCardCategoryEntity(ckat11);
    card1.setPicture(false);
    cardManager.add(card1);
    
    CardEntity card2 = new CardEntity();
    card2.setName("KARTA 2 (2.1)");
    card2.setCardCategoryEntity(ckat21);
    card2.setPicture(false);
    cardManager.add(card2);
    
    CardEntity card3 = new CardEntity();
    card3.setName("KARTA 3 (2.2)");
    card3.setCardCategoryEntity(ckat22);
    card3.setPicture(false);
    cardManager.add(card3);
    
    CardEntity card4 = new CardEntity();
    card4.setName("KARTA 4 (1.2)");
    card4.setCardCategoryEntity(ckat12);
    card4.setPicture(false);
    cardManager.add(card4);
    
    OfferEntity off1 = new OfferEntity();
    off1.setOfferState(state1);
    off1.setDateOfInsert(new Date());
    off1.setOfferType(type3);
    off1.setUser(user);
    off1.setText("prodavam");
    off1.setOfferObject(card1);
    off1.setTitle("inzerat 1");
    offerManager.add(off1);
    
    OfferEntity off2 = new OfferEntity();
    off2.setOfferState(state1);
    off2.setDateOfInsert(new Date());
    off2.setOfferType(type2);
    off2.setUser(user);
    off2.setText("prodavam");
    off2.setOfferObject(card2);
    off2.setTitle("inzerat 2");
    offerManager.add(off2);
    
    OfferEntity off3 = new OfferEntity();
    off3.setOfferState(state1);
    off3.setDateOfInsert(new Date());
    off3.setOfferType(type2);
    off3.setUser(user);
    off3.setText("prodavam");
    off3.setOfferObject(card3);
    off3.setTitle("inzerat 3");
    offerManager.add(off3);
    
    OfferEntity off4 = new OfferEntity();
    off4.setOfferState(state1);
    off4.setDateOfInsert(new Date());
    off4.setOfferType(type2);
    off4.setUser(user);
    off4.setText("prodavam");
    off4.setOfferObject(card4);
    off4.setTitle("inzerat 4");
    offerManager.add(off4);
    
    OfferEntity off5 = new OfferEntity();
    off5.setOfferState(state1);
    off5.setDateOfInsert(new Date());
    off5.setOfferType(type2);
    off5.setUser(user);
    off5.setText("prodavam");
    off5.setOfferObject(obj);
    off5.setTitle("inzerat 5");
    offerManager.add(off5);
    
    return "Home/index";
    }

    public @Autowired void setCenterManager(CenterManager centerManager) {
        this.centerManager = centerManager;
    }

    public @Autowired void setAddressManager(AddressManager addressManager) {
        this.addressManager = addressManager;
    }

    public @Autowired void setRegionManager(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    public @Autowired void setUserRoleManager(UserRoleManager userRoleManager) {
        this.userRoleManager = userRoleManager;
    }

    public @Autowired void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public @Autowired void setCardManager(CardManager cardManager) {
        this.cardManager = cardManager;
    }

    public @Autowired void setCardCategoryManager(CardCategoryManager cardCategoryManager) {
        this.cardCategoryManager = cardCategoryManager;
    }

    
    
    public @Autowired void setOfferOtherObjectManager(OfferOtherObjectManager offerOtherObjectManager) {
        this.offerOtherObjectManager = offerOtherObjectManager;
    }

    public @Autowired void setOfferStateManager(OfferStateManager offerStateManager) {
        this.offerStateManager = offerStateManager;
    }

    public @Autowired void setOfferTypeManager(OfferTypeManager offerTypeManager) {
        this.offerTypeManager = offerTypeManager;
    }

    public @Autowired void setOfferOtherObjectCategoryManager(OfferOtherObjectCategoryManager offerOtherObjectCategoryManager) {
        this.offerOtherObjectCategoryManager = offerOtherObjectCategoryManager;
    }

    public @Autowired void setOfferManager(OfferManager offerManager) {
        this.offerManager = offerManager;
    }
    
    

}
