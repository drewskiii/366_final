package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.GregorianCalendar;
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

// Demo0: Add, list, and remove ShipmentOrder instances

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
public class ShipmentOrderTests {

    private final static Logger log = LoggerFactory.getLogger(ShipmentOrderTests.class);
    
    @Autowired
    private ShipmentOrderRepository shipmentOrderRepository;

    private final ShipmentOrder shipmentOrder = new ShipmentOrder(new Date(49239093));  // "reference" shipment order
    private final Product product = new Product("potatoes", 1);
    private final Location location = new Location("8 Location Rd", "Redwood City", "California");
    private final Supplier supplier = new Supplier("9 Supplier Rd");
    
    @BeforeEach
    private void setup() {
        shipmentOrderRepository.saveAndFlush(shipmentOrder);
        shipmentOrder.setProduct(product);
        shipmentOrderRepository.saveAndFlush(shipmentOrder);
        shipmentOrder.setLocation(location);
        shipmentOrderRepository.saveAndFlush(shipmentOrder);
        shipmentOrder.setSupplier(supplier);
        shipmentOrderRepository.saveAndFlush(shipmentOrder);
    }
    
    @Test
    @Order(1)
    public void testShipmentOrder1() {
        ShipmentOrder shipmentOrder2 = shipmentOrderRepository.findByDateShipped(new Date(49239093));

        // log.info(shipmentOrder2.toString());
        
        assertNotNull(shipmentOrder);
        assertEquals(shipmentOrder2.getId(), shipmentOrder.getId());
        assertEquals(shipmentOrder2.getDateShipped(), shipmentOrder.getDateShipped());
    }

    @Test
    @Order(2)
    public void testShipmentOrder2() {
        ShipmentOrder shipmentOrder2 = shipmentOrderRepository.findByDateShipped(new Date(49239093));

        // log.info(shipmentOrder2.toString());
        
        assertNotNull(shipmentOrder);
        assertEquals(shipmentOrder2.getId(), shipmentOrder.getId());
        assertEquals(shipmentOrder2.getProduct(), shipmentOrder.getProduct());
    }

    @Test
    @Order(3)
    public void testShipmentOrder3() {
        ShipmentOrder shipmentOrder2 = shipmentOrderRepository.findByDateShipped(new Date(49239093));

        // log.info(shipmentOrder2.toString());
        
        assertNotNull(shipmentOrder);
        assertEquals(shipmentOrder2.getId(), shipmentOrder.getId());
        assertEquals(shipmentOrder2.getLocation(), shipmentOrder.getLocation());
    }

    @Test
    @Order(4)
    public void testShipmentOrder4() {
        ShipmentOrder shipmentOrder2 = shipmentOrderRepository.findByDateShipped(new Date(49239093));

        // log.info(shipmentOrder2.toString());
        
        assertNotNull(shipmentOrder);
        assertEquals(shipmentOrder2.getId(), shipmentOrder.getId());
        assertEquals(shipmentOrder2.getSupplier(), shipmentOrder.getSupplier());
    }
}