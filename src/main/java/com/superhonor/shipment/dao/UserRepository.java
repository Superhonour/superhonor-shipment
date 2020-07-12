package com.superhonor.shipment.dao;

import com.superhonor.shipment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author liuweidong
 */
public interface UserRepository extends JpaRepository <User, Long> {

    User findByUsername(String username);

    @Query(value = "select r.roleCode from User u inner join UserRole as r on r.userId = u.id where u.username = ?1")
    List <String> queryUserOwnedRoleCodes(@Param(value = "userName") String userName);
}
