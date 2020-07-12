package com.superhonor.shipment.dao;

import com.superhonor.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuweidong
 */
public interface ShipmentRepository extends JpaRepository <Shipment, Long> {

}
