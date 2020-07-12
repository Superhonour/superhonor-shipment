package com.superhonor.shipment.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author liuweidong
 */
public interface UserService {

    UserDetails getUserDetailByUserName(String username);
}
