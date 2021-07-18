package com.company.repository;

import com.company.entity.Order;
import com.company.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByCustomerUuid(String uuid);

    Optional<Order> findByUuid(String uuid);

    @Modifying
    @Query("UPDATE Order o SET o.status=:status, o.lastModifiedDate=:lastModifiedDate WHERE o.uuid=:uuid")
    void updateOrderStatus(@Param("uuid") String uuid,
                           @Param("status") OrderStatus status,
                           @Param("lastModifiedDate") Instant lastModifiedDate);

}
