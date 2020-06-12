package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class EmployeeTests {

   private final static Logger log = LoggerFactory.getLogger(ManagerTests.class);

   @Autowired
   private EmployeeRepository employeeRepository;

   private final Location location = new Location("1 Grand Ave", "SLO", "CA");
   private final Owner owner = new Owner("Tony", "Stark", "tstark@gmail.com", new Date());
   private final Position position = new Position("Barista", 14.99);
   private final Employee employee = new Employee("Johanna", "Barista", 30, location, owner, position);

   @BeforeEach
   private void setup() {
      employeeRepository.saveAndFlush(employee);
   }

   @Test
   @Order(1)
   public void testEmployeeByLastName() {
      Employee employee2 = employeeRepository.findByLastName("Barista");

      log.info(employee2.toString());

      assertNotNull(employee2);
   }

   @Test
   @Order(2)
   public void testEmployeeGetFields() {
      Employee employee2 = employeeRepository.findByLastName("Barista");
      assertNotNull(employee2);
      assertEquals(employee2.getId(), employee.getId());
      assertEquals(employee2.getHours(), employee.getHours());
      assertEquals(employee2.getFirstName(), employee.getFirstName());
      assertEquals(employee2.getLastName(), employee.getLastName());
   }

   @Test
   @Order(3)
   public void testEmployeeGetObjectFields() {
      Employee employee2 = employeeRepository.findByLastName("Barista");
      assertNotNull(employee2);
      assertEquals(employee2.getPosition(), employee.getPosition());
      assertEquals(employee2.getLocation(), employee.getLocation());
      assertEquals(employee2.getPaidBy(), employee.getPaidBy());
   }
}
