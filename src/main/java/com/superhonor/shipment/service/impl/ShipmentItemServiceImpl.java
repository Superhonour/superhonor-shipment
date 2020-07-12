package com.superhonor.shipment.service.impl;

import com.superhonor.shipment.dao.ShipmentItemRepository;
import com.superhonor.shipment.dto.ShipmentItemDTO;
import com.superhonor.shipment.dto.ShipmentSplitDTO;
import com.superhonor.shipment.entity.ShipmentItem;
import com.superhonor.shipment.service.ShipmentItemService;
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
public class ShipmentItemServiceImpl implements ShipmentItemService {

    @Autowired
    private ShipmentItemRepository itemRepository;

    @Override
    public void saveAll(Long shipmentId, List <ShipmentItemDTO> items) {
        items.forEach(e -> {
            ShipmentItem shipmentItem = new ShipmentItem();
            shipmentItem.setShipmentid(shipmentId);
            shipmentItem.setAmount(e.getAmount());
            itemRepository.save(shipmentItem);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void splitShipment(ShipmentSplitDTO splitDTO) {
        Optional <ShipmentItem> optional = itemRepository.findById(splitDTO.getShipmentItemId());
        // TODO 校验原值和新值是否一致
        itemRepository.deleteById(splitDTO.getShipmentItemId());
        this.saveAll(optional.get().getShipmentid(), splitDTO.getItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mergeShipment(List <ShipmentItemDTO> items) {
        final BigDecimal[] b = {BigDecimal.ZERO};
        items.forEach(e -> {
            itemRepository.deleteById(e.getId());
            b[0] = b[0].add(e.getAmount());
        });

        ShipmentItem shipmentItem = new ShipmentItem();
        Optional <ShipmentItem> one = itemRepository.findById(items.get(0).getId());
        shipmentItem.setShipmentid(one.get().getShipmentid());
        shipmentItem.setAmount(b[0]);
        itemRepository.save(shipmentItem);
    }

    @Override
    public List <ShipmentItem> getByShipmentId(Long shipmentId) {
        return itemRepository.findByShipmentid(shipmentId);
    }

    @Override
    public void save(ShipmentItem item) {
        itemRepository.save(item);
    }
}
