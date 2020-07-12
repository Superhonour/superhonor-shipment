package com.superhonor.shipment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class ShipmentItemDTO {

    private Long id;
    private BigDecimal amount;
}
