package com.superhonor.shipment.service;

import com.superhonor.shipment.dto.ShipmentItemDTO;
import com.superhonor.shipment.dto.ShipmentSplitDTO;
import com.superhonor.shipment.entity.ShipmentItem;

import java.util.List;

/**
 * @author liuweidong
 */
public interface ShipmentItemService {

    void saveAll(Long shipmentId, List <ShipmentItemDTO> items);

    void splitShipment(ShipmentSplitDTO splitDTO);

    void mergeShipment(List<ShipmentItemDTO> items);

    List <ShipmentItem> getByShipmentId(Long shipmentId);

    void save(ShipmentItem item);
}
