package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.ProductTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {

    List<ProductTO> selectAllProduct();
}
