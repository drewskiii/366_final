package csc366.jpademo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


// This class will be automatically implemented by Spring and made available as a "Bean" named personRepository
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    // query inferred from method name, code generated by Spring Framework
    Customer findByFirstName(String firstName);

    Customer findByLastName(String lastName);

    @Query("from Customer c where c.state = :state")
    List<Customer> findByState(@Param("state") String state);

    @Query("from Customer c where c.city = :city")
    List<Customer> findByCity(@Param("city") String city);

    // JPQL query  (validity check on application init)
    @Query("from Customer c where c.firstName = :name or c.lastName = :name")
    Customer findByNameJpql(@Param("name") String name);

    // JPQL query with join
    @Query("select c from Customer c join c.transaction trans where c.firstName = :name or c.lastName = :name")
    Customer findByNameWithTransactionJpql(@Param("name") String name);


    // @Query(value = "select c, count(c) from Customer c join c.transaction trans group by c order by count(c) DESC")
    // List<Customer> findMostTransactions();

    @Query(value = "select c.id, count(*) from Customer c join Transaction t on t.customer_id = c.id group by c.id order by count(*) DESC limit 1", nativeQuery = true)
    Customer findMostTransactions();

    @Query(value = "select c.id, sum(totalprice) as tot from customer c join transaction trans on trans.customer_id = c.id group by c.id order by tot DESC limit 1", nativeQuery = true)
    Customer findMostSpent();



    // @Query(value = "select new.mx from ("+
    //                 "select n.state, max(n.state_cnt) mx from " +
    //                     "(select count(*) as state_cnt, c.state as state " +
    //                     "from customer as c " +
    //                     "group by c.state) as n " + 
    //                 "group by n.state) as new", nativeQuery = true)
    @Query(value = "select n.state, max(n.state_cnt) mx from " +
                        "(select count(*) as state_cnt, c.state as state " +
                        "from customer as c " +
                        "group by c.state) as n " + 
                    "group by n.state " +
                    "order by mx desc " +
                    "limit 1", nativeQuery = true)
    // @Query(value = "select count(*) as state_cnt, c.state as state " +
    // "from customer as c " +
    // "group by c.state", nativeQuery = true)
    List<String> findMostState();
    // double findMostSpent();
    // JPQL query with join
    // @Query("select p from Person p join p.addresses addr where p.firstName = :name or p.lastName = :name")
    // Person findByNameWithAddressJpql(@Param("name") String name);
    
    // Native SQL query  (validity not checked, invalid SQL will cause runtime exception)
    // @Query(value = "select * from person as p where p.first_name = :name or p.last_name = :name", nativeQuery = true)
    // Person findByNameSql(@Param("name") String name);

    @Modifying
    @Query("update Customer c set c.firstName = :newName where c.firstName = :oldName")
    void updateName(@Param("oldName") String oldName, @Param("newName") String newName);
    

}