package com.superhonor.shipment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class ShipmentDTO implements Serializable {

    private Long id;
    private Long supplierid;
    private BigDecimal total;
    private List<ShipmentItemDTO> items;
}
