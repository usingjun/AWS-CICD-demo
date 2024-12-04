package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final AdminDAO adminDAO;

    //전체리스트갖고오기
    public List<ProductTO> getAllProducts() {
        return adminDAO.getAllProducts();

    }
}
