package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Demo2 Created by Grant Matejka to test Owner class

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
public class Demo2 {

    private final static Logger log = LoggerFactory.getLogger(Demo0.class);
    
    @Autowired
    private OwnerRepository ownerRepository;

    private final Owner owner = new Owner("steve", "jobs", "steve@gmail.com", new Date());
    
    @BeforeEach
    private void setup() {
	   ownerRepository.saveAndFlush(owner);
    }
    
    @Test
    @Order(1)
    public void testSaveOwner() {
	Owner owner2 = ownerRepository.findByFirstName("steve");

	log.info(owner2.toString());
	
	assertNotNull(owner2);
	assertEquals(owner2.getFirstName(), owner.getFirstName());
	assertEquals(owner2.getLastName(), owner.getLastName());
    }
    
    @Test
    @Order(2)
    public void testDeleteOwner() {
	ownerRepository.delete(owner);
	ownerRepository.flush();
    }
    
    @Test
    @Order(3)
    public void testFindAllOwners() {
	assertNotNull(ownerRepository.findAll());
    }

}
