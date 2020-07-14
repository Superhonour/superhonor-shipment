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
    private BigDecimal proportion;
}
