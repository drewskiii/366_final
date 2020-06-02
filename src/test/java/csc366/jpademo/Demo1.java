package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Demo0: Add, list, and remove Person & Address instances

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
public class Demo1 {

    private final static Logger log = LoggerFactory.getLogger(Demo1.class);
    
    @Autowired
    private PersonRepository personRepository;

    private final Person person = new Person("test", "test", "test@calpoly.edu");  // "reference" person
    private final Address addrCP = new Address("1 Grand Ave", "SLO", "CA", "93407"); 
    
    @BeforeEach
    private void setup() {
	personRepository.saveAndFlush(person);
	person.addAddress(addrCP);
	personRepository.saveAndFlush(person);
    }
	 
	 /* For reference, needs to be removed
    @Test
    @Order(1)
    public void testPersonAndAddress() {
	Person person2 = personRepository.findByFirstName("test");

	log.info(person2.toString());
	
	assertNotNull(person);
	assertEquals(person2.getAddresses().size(), 1);
    }
    
    @Test
    @Order(2)
    public void testPersonAddressQuery() {
	Person person2 = personRepository.findByFirstName("test");
	assertNotNull(person);
	assertEquals(person2.getFirstName(), person.getFirstName());
	assertEquals(person2.getLastName(), person.getLastName());
    }


    @Test
    @Order(3)
    public void testRemoveAddress() {
	Person p = personRepository.findByFirstName("test");
        Address a = new ArrayList<Address>(p.getAddresses()).get(0);  // get on address
	p.removeAddress(a);
	personRepository.save(p);
        log.info(p.toString());
    }

    @Test
    @Order(4)
    public void testRemoveAddressAndFlush() {
	Person p = personRepository.findByFirstName("test");
        Address a = new ArrayList<Address>(p.getAddresses()).get(0);  // get on address
	p.removeAddress(a);
	personRepository.saveAndFlush(p);
        log.info(p.toString());
    }
    
    @Test
    @Order(5)
    public void testJpqlJoin() {
	Person p = personRepository.findByNameWithAddressJpql("test");
	log.info(p.toString());

	p.addAddress(new Address("2 Grand Ave", "SLO", "CA", "93407-0002"));
	personRepository.saveAndFlush(p);

	p = personRepository.findByNameWithAddressJpql("test");
	log.info(p.toString());
    }*/

}
