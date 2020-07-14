package com.superhonor.shipment.service.impl;

import com.superhonor.shipment.dao.ShipmentItemRepository;
import com.superhonor.shipment.dto.ShipmentItemDTO;
import com.superhonor.shipment.dto.ShipmentSplitDTO;
import com.superhonor.shipment.entity.ShipmentItem;
import com.superhonor.shipment.exception.UserFriendlyException;
import com.superhonor.shipment.service.ShipmentItemService;
import org.apache.commons.lang3.StringUtils;
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
        List<ShipmentItemDTO> items = splitDTO.getItems();
        final BigDecimal[] sum = {BigDecimal.ZERO};
        items.forEach(e -> {
            sum[0] = sum[0].add(e.getAmount());
        });
        if(sum[0].compareTo(optional.get().getAmount()) != 0) {
            throw new UserFriendlyException("拆分后重量与拆分前不一致");
        }
        itemRepository.deleteById(splitDTO.getShipmentItemId());
        this.saveAll(optional.get().getShipmentid(), splitDTO.getItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mergeShipment(List <ShipmentItemDTO> items) {
        final BigDecimal[] b = {BigDecimal.ZERO};
        items.forEach(e -> {
            Optional<ShipmentItem> optional = itemRepository.findById(e.getId());
            if(optional.get().getAmount().compareTo(e.getAmount()) != 0) {
                throw new UserFriendlyException(StringUtils.join("编号为：[", e.getId(), "]的信息数据不一致！"));
            }
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
