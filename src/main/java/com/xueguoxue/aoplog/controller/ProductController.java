package com.xueguoxue.aoplog.controller;

import com.xueguoxue.aoplog.domain.Product;
import com.xueguoxue.aoplog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private Product product;

    @RequestMapping("/index")
    public List<Product> productList()
    {
        return productService.all();
    }

    @RequestMapping("/page")
    public Map<String, Object>  productAll(@Nullable @RequestParam(value="page") String page)
    {
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        Integer pageNumber = Integer.valueOf(page);
        Page<Product> productPage = productService.getProductPaginate(pageNumber, 10);
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("data", productPage.getContent());
        m.put("totalPages", productPage.getTotalPages());
        return m;
    }
}
