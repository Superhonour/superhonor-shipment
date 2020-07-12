package com.superhonor.shipment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class ChangeRootQuantityDTO implements Serializable {

    private Long shipmentId;
    /**
     * 0: increase root quantity
     * 1: decrease root quantity
     */
    private Integer type;
    private BigDecimal proportion;
}
