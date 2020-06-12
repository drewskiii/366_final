package csc366.jpademo;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ShipmentOrderRepository extends JpaRepository<ShipmentOrder, Long> {
    ShipmentOrder findByDateShipped(Date dateShipped);
}