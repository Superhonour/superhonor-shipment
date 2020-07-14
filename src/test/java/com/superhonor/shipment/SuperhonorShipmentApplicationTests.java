package com.superhonor.shipment;

import com.alibaba.fastjson.JSON;
import com.superhonor.shipment.dto.ChangeRootQuantityDTO;
import com.superhonor.shipment.dto.ShipmentDTO;
import com.superhonor.shipment.dto.ShipmentItemDTO;
import com.superhonor.shipment.dto.ShipmentSplitDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
class SuperhonorShipmentApplicationTests {

    private static final String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNTE1MDUxMTc0MCIsImV4cCI6MTU5NTE0Nzg4NywiaWF0IjoxNTk0NTQzMDg3fQ.Z0dBuXRqZJFbsr0udi_HQDxzITwPOs-FPu53R-7xN2M39IWKlebIRpwE_a29kf7aUjdFJ7ohyGg70hz-hhQ2yw";

    /**
     * 模拟mvc测试对象
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addShipmentTest() throws Exception {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setSupplierid(1L);
        shipmentDTO.setTotal(new BigDecimal("100"));
        List <ShipmentItemDTO> items = new ArrayList <>();
        ShipmentItemDTO shipmentItem1 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("100"));
        items.add(shipmentItem1);
        shipmentDTO.setItems(items);
        String jsonString = JSON.toJSONString(shipmentDTO);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/shipment/addShipment")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("正确", status == 200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void splitShipmentTest() throws Exception {
        ShipmentSplitDTO shipmentSplitDTO = new ShipmentSplitDTO();
        shipmentSplitDTO.setShipmentItemId(1L);
        ArrayList <ShipmentItemDTO> items = new ArrayList <>();
        shipmentSplitDTO.setItems(items);
        ShipmentItemDTO shipmentItem1 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("20"));
        items.add(shipmentItem1);
        ShipmentItemDTO shipmentItem2 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("30"));
        items.add(shipmentItem2);
        ShipmentItemDTO shipmentItem3 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("50"));
        items.add(shipmentItem3);

        String jsonString = JSON.toJSONString(shipmentSplitDTO);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/shipment/splitShipment")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("正确", status == 200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void mergeShipmentTest() throws Exception {
        List <ShipmentItemDTO> items = new ArrayList <>();
        ShipmentItemDTO shipmentItem1 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("30"));
        shipmentItem1.setId(3L);
        items.add(shipmentItem1);
        ShipmentItemDTO shipmentItem2 = new ShipmentItemDTO();
        shipmentItem1.setAmount(new BigDecimal("50"));
        items.add(shipmentItem2);
        shipmentItem1.setId(4L);
        String jsonString = JSON.toJSONString(items);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/shipment/mergeShipment")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("正确", status == 200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void changeRootQuantityTest() throws Exception {
        ChangeRootQuantityDTO changeRootQuantityDTO = new ChangeRootQuantityDTO();
        changeRootQuantityDTO.setProportion(new BigDecimal("2"));
        changeRootQuantityDTO.setShipmentId(1L);
        String jsonString = JSON.toJSONString(changeRootQuantityDTO);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/shipment/changeRootQuantity")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertTrue("正确", status == 200);

        System.out.println("输出 " + mvcResult.getResponse().getContentAsString());
    }

}
