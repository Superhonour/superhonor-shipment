package com.superhonor.shipment.service;

import com.superhonor.shipment.dto.ChangeRootQuantityDTO;
import com.superhonor.shipment.dto.ShipmentDTO;

/**
 * @author liuweidong
 */
public interface ShipmentService {

    void addShipment(ShipmentDTO shipmentDTO);

    void changeRootQuantity(ChangeRootQuantityDTO changeRootQuantityDTO);
}
