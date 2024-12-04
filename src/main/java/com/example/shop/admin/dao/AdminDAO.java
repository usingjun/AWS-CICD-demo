package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@RequiredArgsConstructor
@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;

    public List<ProductTO> getAllProducts() {

        return adminMapper.selectAllProduct();
    }


}
