package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.dto.ProductUpdateRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    int updateProduct(ProductUpdateRequest productUpdateRequest);
    List<ProductTO> selectAllProduct();
    Long updateOrderDelivery(OrderDeliveryRequest orderDelivery);
}
