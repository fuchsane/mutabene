/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.User;
import java.util.List;

/**
 *
 * @author Anysek
 */
public interface UserService {
    public void saveUser(User user);
    public List<User> listUser();
}
