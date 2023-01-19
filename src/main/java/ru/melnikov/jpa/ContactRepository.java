package ru.melnikov.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.melnikov.entity.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, JpaSpecificationExecutor<Contact> {
    Page<Contact> findContactByEmails_emailContainingAndDepartment_Id(String email,Long idDepartment, Pageable pageable);

    Page<Contact> findContactByEmails_EmailContainingAndDepartment_NameAndProjects_Name(String email, String depName, String projectName, Pageable pageable);

    Page<Contact> findContactByEmails_EmailContainingOrDepartment_NameOrProjects_Name(String email, String depName, String projectName, Pageable pageable);


    Page<Contact> findContactByDepartment_Id(Long id, Pageable pageable);
}
