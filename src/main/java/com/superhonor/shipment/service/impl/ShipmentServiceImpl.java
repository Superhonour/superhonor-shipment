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

import java.math.BigDecimal;
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
        final BigDecimal[] total = {BigDecimal.ZERO};
        items.forEach(e -> {
            BigDecimal amount = e.getAmount().multiply(changeRootQuantityDTO.getProportion()).setScale(6, BigDecimal.ROUND_FLOOR);
            total[0] = total[0].add(amount);
            e.setAmount(amount);
            itemService.save(e);
        });
        shipment.setTotal(total[0].setScale(6, BigDecimal.ROUND_FLOOR));
        shipmentRepository.save(shipment);
    }
}
