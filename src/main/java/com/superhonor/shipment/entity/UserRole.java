package com.superhonor.shipment.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author liuweidong
 */
@Entity
@Table(name = "user_role")
@Setter
@Getter
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String roleCode;
}
