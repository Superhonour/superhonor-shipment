package com.superhonor.shipment.service.impl;

import com.superhonor.shipment.dao.ShipmentRepository;
import com.superhonor.shipment.dto.ChangeRootQuantityDTO;
import com.superhonor.shipment.dto.ShipmentDTO;
import com.superhonor.shipment.entity.Shipment;
import com.superhonor.shipment.entity.ShipmentItem;
import com.superhonor.shipment.service.ShipmentItemService;
import com.superhonor.shipment.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author liuweidong
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentItemService itemService;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setSupplierid(shipmentDTO.getSupplierid());
        shipment.setTotal(shipmentDTO.getTotal());
        shipment = shipmentRepository.saveAndFlush(shipment);
        itemService.saveAll(shipment.getId(), shipmentDTO.getItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeRootQuantity(ChangeRootQuantityDTO changeRootQuantityDTO) {
        Optional <Shipment>  optional = shipmentRepository.findById(changeRootQuantityDTO.getShipmentId());
        List <ShipmentItem> items = itemService.getByShipmentId(changeRootQuantityDTO.getShipmentId());
        Shipment shipment = optional.get();
        shipment.setTotal(shipment.getTotal().multiply(changeRootQuantityDTO.getProportion()));
        shipmentRepository.save(shipment);
        items.forEach(e -> {
            e.setAmount(e.getAmount().multiply(changeRootQuantityDTO.getProportion()));
            itemService.save(e);
        });
    }
}
