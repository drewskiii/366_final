package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
	"spring.main.banner-mode=off",
	"logging.level.root=ERROR",
	"logging.level.csc366=DEBUG",

	"spring.jpa.hibernate.ddl-auto=update",
	"spring.datasource.url=jdbc:mysql://mysql.labthreesixsix.com/csc366",
	"spring.datasource.username=jpa",
	"spring.datasource.password=demo",
	
	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE", // display prepared statement parameters
	"spring.jpa.properties.hibernate.format_sql=true",
	"spring.jpa.show-sql=false",   // prevent duplicate logging
	"spring.jpa.properties.hibernate.show_sql=false",	
	
	"logging.pattern.console= %d{yyyy-MM-dd HH:mm:ss} - %msg%n"
})
@TestMethodOrder(OrderAnnotation.class)
public class LocationTests {

    private final static Logger log = LoggerFactory.getLogger(LocationTests.class);
    
    @Autowired
    private LocationRepository locationRepository;

    private final Manager manager = new Manager(40, "jennypotts@gmail.com");  // "reference" Manager
    private final Location location = new Location("1 Grand Ave", "SLO", "CA"); 
    
    @BeforeEach
    private void setup() {
		locationRepository.saveAndFlush(location);
		location.setManager(manager);
		locationRepository.saveAndFlush(location);
    }
    
    @Test
    @Order(1)
    public void testLocation1() {
		Location location2 = locationRepository.findByAddress("1 Grand Ave");

		log.info(location2.toString());
		
		assertNotNull(location);
    }
    
    @Test
    @Order(2)
    public void testLocation2() {
		Location location2 = locationRepository.findByAddress("1 Grand Ave");
		assertNotNull(location);
		assertEquals(location2.getAddress(), location.getAddress());
		assertEquals(location2.getCity(), location.getCity());
		assertEquals(location2.getState(), location.getState());
    }

    @Test
    @Order(3)
    public void testLocation3() {
		Location location2 = locationRepository.findByAddress("1 Grand Ave");
		assertNotNull(location);
		assertEquals(location2.getManager(), location.getManager());
    }
}