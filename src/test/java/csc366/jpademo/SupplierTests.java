package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Demo0: Add, list, and remove Supplier instances

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"spring.jpa.hibernate.ddl-auto=update",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class SupplierTests {

    private final static Logger log = LoggerFactory.getLogger(SupplierTests.class);
    
    @Autowired
    private SupplierRepository supplierRepository;

    private final Supplier supplier = new Supplier("9 Supplier Rd");  // "reference" supplier
    private final ShipmentOrder shipmentOrder1 = new ShipmentOrder(new Date(49239093));
    private final ShipmentOrder shipmentOrder2 = new ShipmentOrder(new Date(24239093));
    private final Location location1 = new Location("8 Location Rd", "Redwood City", "California");
    private final Location location2 = new Location("7 Location Rd", "San Mateo", "California");
    
    @BeforeEach
    private void setup() {
        supplierRepository.saveAndFlush(supplier);
        supplier.addShipmentOrder(shipmentOrder1);
        supplierRepository.saveAndFlush(supplier);
        supplier.addShipmentOrder(shipmentOrder2);
        supplierRepository.saveAndFlush(supplier);
        supplier.addLocation(location1);
        supplierRepository.saveAndFlush(supplier);
        supplier.addLocation(location2);
        supplierRepository.saveAndFlush(supplier);
    }
    
    @Test
    @Order(1)
    public void testSupplier1() {
        Supplier supplier2 = supplierRepository.findByAddress("9 Supplier Rd");

        // log.info(supplier2.toString());
        
        assertNotNull(supplier);
        assertEquals(supplier2.getId(), supplier.getId());
        assertEquals(supplier2.getAddress(), supplier.getAddress());
    }

    @Test
    @Order(2)
    public void testSupplier2() {
        Supplier supplier2 = supplierRepository.findByAddress("9 Supplier Rd");

        log.info(supplier2.toString());
        
        assertNotNull(supplier);
        assertEquals(supplier2.getId(), supplier.getId());
        assertEquals(supplier2.getShipmentOrders(), supplier.getShipmentOrders());
    }

    @Test
    @Order(3)
    public void testSupplier3() {
        Supplier supplier2 = supplierRepository.findByAddress("9 Supplier Rd");

        log.info(supplier2.toString());
        
        assertNotNull(supplier);
        assertEquals(supplier2.getId(), supplier.getId());
        assertEquals(supplier2.getLocations(), supplier.getLocations());
    }
}