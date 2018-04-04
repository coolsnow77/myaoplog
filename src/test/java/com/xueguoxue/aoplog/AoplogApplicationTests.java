package com.xueguoxue.aoplog;

import com.xueguoxue.aoplog.dao.ProductDao;
import com.xueguoxue.aoplog.domain.Product;
import com.xueguoxue.aoplog.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AoplogApplicationTests {
	@Autowired
	ProductDao productDao;

	@Autowired
    ProductService productService;

	@Test
	public void testInsert() {
        IntStream.range(1, 10).forEach(
                (item)->{
                    System.out.println();
                    Product product = new Product();
                    product.setName("dell computer");
                    product.setOnlineTime(new Date());
                    product.setBuyPrice(new BigDecimal("29.5"));
                    product.setCategory("computer");
                    product.setDetail("this is a dell notebook");
                    product.setUpdateTime(new Date());
                    productDao.save(product);
                    System.out.println("new product id:"+product.getId());
                }
        );
	}

	@Test
	public void testUpdate(){
		Optional<Product> productOptional = productDao.findById(2L);
		productOptional.ifPresent((product)->{
//            Product product = productOptional.get();
            product.setName("test-update");
            product.setBuyPrice(new BigDecimal("23.5"));
            product.setOnlineTime(new Date());
            productDao.save(product);
        });

	}

	@Test
	public void testDelete(){
		productDao.deleteById(2L);
	}

	@Test
	public void testDelete2(){
		productService.delete(3L);
	}

	@Test
    public void updateProductNameTest()
    {
        assertThat(productService.updateName("productName", 4L));
    }
}
