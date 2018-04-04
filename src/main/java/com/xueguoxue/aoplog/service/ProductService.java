package com.xueguoxue.aoplog.service;

import com.xueguoxue.aoplog.dao.ProductDao;
import com.xueguoxue.aoplog.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional
    public void delete(Long id)
    {
        productDao.delete(id);
    }

    public List<Product> all()
    {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return productDao.findAll(sort);
    }

    @Transactional
    public Integer updateName(String name, Long id)
    {
        return productDao.updateName(name, id);
    }

    @Cacheable(cacheNames = "getProductpaginate")
    public Page<Product> getProductPaginate(Integer pageNumber, Integer pageSize)
    {
        PageRequest pageRequest = this.buildPageRequest(pageNumber, pageSize);
        Page<Product> products = productDao.findAll(pageRequest);
        System.out.println("goes here...");
        return products;
    }

    /**
     * Build page request parameter
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    private PageRequest buildPageRequest(Integer pageNumber, Integer pageSize)
    {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Sort sortId = new Sort(Sort.Direction.DESC, "id");
//        Sort sortBuyPrice = new Sort(Sort.Direction.DESC, "buyPrice");

        List<Sort.Order> sortList = new ArrayList<>();
        Sort.Order orderId = Sort.Order.desc("id");
        Sort.Order orderBuyPrice = Sort.Order.asc("buyPrice");
        // price 升序， id 倒序
        sortList.add(orderBuyPrice);
        sortList.add(orderId);
        Sort sort = Sort.by(sortList);
//        return PageRequest.of(pageNumber-1, pageSize, sort);
        return PageRequest.of(pageNumber-1, pageSize, sort);
//        return PageRequest.of(pageNumber-1, pageSize, Sort.Direction.DESC, "buyPrice", "id");
//        return PageRequest.of(pageNumber-1, pageSize, Sort.Direction.DESC, "id");
    }
}
