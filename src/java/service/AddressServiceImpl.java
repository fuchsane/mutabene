/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Address;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Anysek
 */
public class AddressServiceImpl implements AddressService {
    Session session = null;
    //potrebuje Hibernate pro svou praci
    private SessionFactory sessionFactory = null;
    //pro zjednoduseni pouziti sessionFactory
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveAddress(Address address) {
        hibernateTemplate.saveOrUpdate(address);
    }

    @Override
    public List<Address> listAddress() {
        return hibernateTemplate.find("from Address");
    }
}
