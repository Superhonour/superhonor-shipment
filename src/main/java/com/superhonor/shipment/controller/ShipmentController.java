package com.superhonor.shipment.controller;

import com.superhonor.shipment.dto.ChangeRootQuantityDTO;
import com.superhonor.shipment.dto.ShipmentDTO;
import com.superhonor.shipment.dto.ShipmentItemDTO;
import com.superhonor.shipment.dto.ShipmentSplitDTO;
import com.superhonor.shipment.entity.ShipmentItem;
import com.superhonor.shipment.exception.UserFriendlyException;
import com.superhonor.shipment.result.OperationResult;
import com.superhonor.shipment.service.ShipmentItemService;
import com.superhonor.shipment.service.ShipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author liuweidong
 */
@RestController
@RequestMapping("/shipment")
@Api("Shipment api")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentItemService itemService;

    @PostMapping("/addShipment")
    @ApiOperation("addShipment")
    @PreAuthorize("isAuthenticated()")
    public OperationResult<String> addShipment(@RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.addShipment(shipmentDTO);
        return OperationResult.Success("add success!", null);
    }

    @PostMapping("/splitShipment")
    @ApiOperation("splitShipment")
    @PreAuthorize("isAuthenticated()")
    public OperationResult<String> splitShipment(@RequestBody ShipmentSplitDTO splitDTO) {
        itemService.splitShipment(splitDTO);
        return OperationResult.Success("split success!", null);
    }

    @PostMapping("/mergeShipment")
    @ApiOperation("mergeShipment")
    @PreAuthorize("isAuthenticated()")
    public OperationResult<String> mergeShipment(@RequestBody List <ShipmentItemDTO> items) {
        itemService.mergeShipment(items);
        return OperationResult.Success("merge success!", null);
    }

    @PostMapping("/changeRootQuantity")
    @ApiOperation("changeRootQuantity")
    @PreAuthorize("isAuthenticated()")
    public OperationResult<String> changeRootQuantity(@RequestBody ChangeRootQuantityDTO changeRootQuantityDTO) {
        if(BigDecimal.ZERO.compareTo(changeRootQuantityDTO.getProportion()) < 0) {
            throw new UserFriendlyException("变更比例参数不能是负数");
        }
        shipmentService.changeRootQuantity(changeRootQuantityDTO);
        return OperationResult.Success("change success!", null);
    }
}
