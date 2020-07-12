package com.superhonor.shipment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class ShipmentSplitDTO implements Serializable {

    private Long shipmentItemId;
    private List <ShipmentItemDTO> items;
}
