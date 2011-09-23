/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.Address;
import java.util.List;

/**
 *
 * @author Anysek
 */
public interface AddressService {
    public void saveAddress(Address address);
    public List<Address> listAddress();
}
