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
public class ManagerTests {

    private final static Logger log = LoggerFactory.getLogger(ManagerTests.class);
    
    @Autowired
    private ManagerRepository managerRepository;

    private final Manager manager = new Manager(40, "jennypotts@gmail.com");  // "reference" Manager
    private final Location location = new Location("1 Grand Ave", "SLO", "CA"); 
    private final Owner owner = new Owner("Tony", "Stark", "tstark@gmail.com", new Date());
    
    @BeforeEach
    private void setup() {
		managerRepository.saveAndFlush(manager);
		manager.setLocation(location);
		managerRepository.saveAndFlush(manager);
		manager.setOwner(owner);
		managerRepository.saveAndFlush(manager);
    }
    
    @Test
    @Order(1)
    public void testManager1() {
		Manager manager2 = managerRepository.findByEmail("jennypotts@gmail.com");

		log.info(manager2.toString());
		
		assertNotNull(manager);
    }
    
    @Test
    @Order(2)
    public void testManager2() {
		Manager manager2 = managerRepository.findByEmail("jennypotts@gmail.com");
		assertNotNull(manager);
		assertEquals(manager2.getHoursPerWeek(), manager.getHoursPerWeek());
		assertEquals(manager2.getEmail(), manager.getEmail());
    }

    @Test
    @Order(3)
    public void testManager3() {
		Manager manager2 = managerRepository.findByEmail("jennypotts@gmail.com");
		assertNotNull(manager);
		assertEquals(manager2.getLocation(), manager.getLocation());
		assertEquals(manager2.getOwner(), manager.getOwner());
    }
}