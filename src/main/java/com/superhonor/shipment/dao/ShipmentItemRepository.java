package com.superhonor.shipment.dao;

import com.superhonor.shipment.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuweidong
 */
public interface ShipmentItemRepository extends JpaRepository <ShipmentItem, Long> {

    List <ShipmentItem> findByShipmentid(Long shipmentId);
}
