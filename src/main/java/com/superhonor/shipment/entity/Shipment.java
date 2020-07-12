package com.superhonor.shipment.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuweidong
 */
@Entity
@Table(name = "shipment")
@Setter
@Getter
@ToString
public class Shipment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long supplierid;
    private BigDecimal total;
}
