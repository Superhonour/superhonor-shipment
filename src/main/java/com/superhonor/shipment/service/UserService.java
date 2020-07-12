package com.superhonor.shipment.service;

import com.superhonor.shipment.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author liuweidong
 */
public interface UserService {

    UserDetails getUserDetailByUserName(String username);

    User getUserByUserName(String username);
}
