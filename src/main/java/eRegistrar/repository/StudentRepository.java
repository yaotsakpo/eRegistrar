package eRegistrar.repository;

import eRegistrar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findAllByCgpaEquals(Double overdueFee);

    List<Student> findAllByEnrollmentDateEquals(LocalDate datePublished);

    List<Student> findAllByStudentNumberContainingOrFirstNameContainingOrMiddleNameContainingOrLastNameContainingOrderByStudentNumber(String studentNumber, String firstName, String middleName, String lastName);
}
