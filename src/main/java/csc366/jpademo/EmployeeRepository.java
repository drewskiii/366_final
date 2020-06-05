package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   @Query("from Employee e join e.location loc where loc = :location")
   Employee findByLocationJpql(@Param("location") String location);

   @Query("from Employee e join e.position pos where pos = :position")
   Employee findByPositionJpql(@Param("position") String position);
}
