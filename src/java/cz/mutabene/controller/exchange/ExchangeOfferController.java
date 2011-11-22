/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mutabene.controller.exchange;

import cz.mutabene.controller.exchange.interfaces.IExchangeOfferController;
import cz.mutabene.controller.exchange.state.StateCardExchange;
import cz.mutabene.controller.exchange.state.StateOtherObjectExchange;
import cz.mutabene.controller.exchange.state.interfaces.IStateOfferExchange;
import cz.mutabene.model.forms.exchange.ExchangeModel;
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
@RequestMapping(value="exchange/")
public class ExchangeOfferController implements IExchangeOfferController<ExchangeModel, Long, ModelMap, String, BindingResult, String> {

    private IStateOfferExchange state;
    
    private StateCardExchange stateCardExchange;
    
    private StateOtherObjectExchange stateOtherObjectExchange;
    
    
    
    private IStateOfferExchange getInstanceStatus(String name){
    
    if(stateCardExchange.support(name)) {
        //System.out.println("SUPPORT CARD");
        //System.out.println(stateCardExchange.toString());
        return stateCardExchange;
    }
    if(stateOtherObjectExchange.support(name)) {
        //System.out.println("SUPPORT OTHER");
        //System.out.println(stateOtherObjectExchange.toString());
        return stateOtherObjectExchange;
    }
        //System.out.println("NON SUPPORT");
    return stateCardExchange;
    }
    @RequestMapping(value="show.htm",method= RequestMethod.GET)
    public String show(String exchange, ModelMap m) {
        state = getInstanceStatus(exchange);
        return (String) state.show(m);
    }

    public String add(String exchange, ExchangeModel formModel, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String edit(String exchange, Long id, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String delete(String exchange, Long id, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String submit(String exchange, ExchangeModel formModel, BindingResult result, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String save(String exchange, ExchangeModel formModel, BindingResult result, ModelMap m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public @Autowired void setStateCardExchange(StateCardExchange stateCardExchange) {
        this.stateCardExchange = stateCardExchange;
    }

    public @Autowired void setStateOtherObjectExchange(StateOtherObjectExchange stateOtherObjectExchange) {
        this.stateOtherObjectExchange = stateOtherObjectExchange;
    }

    @RequestMapping(value="filter.htm",method= RequestMethod.GET)
    public String filter(String exchange, @RequestParam(required=false)String category, @RequestParam(required=false) String type, ModelMap m) {
        if(exchange != null) {String tmp = "exchange="+exchange+"&"; m.addAttribute("p_exchange", tmp ); }
        if(type != null) {String tmp = "type="+type+"&"; m.addAttribute("p_type", tmp);}
        if(category != null){String tmp = "category="+category+"&"; m.addAttribute("p_category", tmp);}
        
        state = getInstanceStatus(exchange);
        return (String)state.filter(category, type, m);
    }

    
    
}
