package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByAddress(String address);
}