package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class DemoCust {
    private final static Logger log = LoggerFactory.getLogger(DemoCust.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer = new Customer("Andrew", "Lor", "Salem", "OR");
    private final Customer customer2 = new Customer("Billy", "Lee", "Salem", "MA");
    // Date date = Calendar.getInstance().getTime();
    Date date = new Date();
    private final Transaction t = new Transaction(11.31, date);
    private final Transaction t2 = new Transaction(0.33, date);
    private final Transaction t3 = new Transaction(99.99, date);


    @BeforeEach
    private void setup() {
        customerRepository.saveAndFlush(customer);
        customer.addTransaction(t);
        customer.addTransaction(t2);
        customerRepository.saveAndFlush(customer);
        customerRepository.saveAndFlush(customer2);
        customer2.addTransaction(t3);
        customerRepository.saveAndFlush(customer2);
    }

    @Test
    @Order(1)
    public void testSaveCustomer() {
        Customer c2 = customerRepository.findByFirstName("Andrew");
        log.info(c2.toString());
        
        assertNotNull(customer);
        assertEquals(c2.getFirstName(), customer.getFirstName());
        assertEquals(c2.getLastName(), customer.getLastName());
    }

    @Test
    @Order(2)
    public void testFindByCity() {
        List<Customer> lst = customerRepository.findByCity("Salem");
        assertTrue(lst.size() == 2);
    }

    @Test
    @Order(3)
    public void testFindByState() {
        List<Customer> lst = customerRepository.findByState("MA");
        assertTrue(lst.size() == 1);
        assertEquals(lst.get(0).getFirstName(), customer2.getFirstName());
        assertEquals(lst.get(0).getLastName(), customer2.getLastName());
    }

    @Test
    @Order(4)
    public void testFindAll() {
        assertNotNull(customerRepository.findAll());
    }

    @Test
    @Order(5)
    public void testCustomerAndTransaction() {
        Customer c2 = customerRepository.findByFirstName("Andrew");
        Customer c3 = customerRepository.findByFirstName("Billy");
        log.info(c2.toString());
        log.info(c3.toString());

        assertNotNull(c2);
        assertNotNull(c3);
        assertEquals(c2.getTransactions().size(), 2);
        assertEquals(c3.getTransactions().size(), 1);
    }

    @Test
    @Order(6)
    public void testJPQLJoinWithTransaction() {
        Customer c = customerRepository.findByNameWithTransactionJpql("Billy");
        log.info(c.toString());

        c.addTransaction(new Transaction(10.22, new Date()));
        customerRepository.saveAndFlush(c);

        c = customerRepository.findByNameWithTransactionJpql("Billy");
        log.info(c.toString());

    }



}