/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Anysek
 */
public class UserServiceImpl implements UserService {
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
    public void saveUser(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }

    @Override
    public List<User> listUser() {
        return hibernateTemplate.find("from User");
    }
}
