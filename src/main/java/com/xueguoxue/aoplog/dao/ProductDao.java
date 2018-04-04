package com.xueguoxue.aoplog.dao;

import com.xueguoxue.aoplog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductDao extends JpaRepository<Product,Long>, PagingAndSortingRepository<Product, Long> {
    public Optional<Product> findById(Long id);
    public void deleteById(Long id);

    @Modifying
    @Query("delete from Product  p where p.id = :id")
    public void delete(@Param(value ="id") Long id);

    @Modifying
    @Query("update Product p set p.name = :name where p.id = :id")
    public Integer updateName(@Param(value="name") String name, @Param(value="id") Long id);
}