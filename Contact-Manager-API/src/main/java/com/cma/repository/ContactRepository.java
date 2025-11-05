package com.cma.repository;

import com.cma.entity.Contact;
import com.cma.projection.ContactFirstNameEmail;
import com.cma.projection.ContactProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    List<Contact> findContactByFirstNameIgnoreCase(String firstName);
    Long countByIsActiveFalse();

    @Query("SELECT c.firstName AS firstName, c.email AS email FROM Contact c WHERE c.category = :category")
    List<ContactFirstNameEmail> findFirstAndEmailByCategory(@Param("category") String category);
    Page<ContactProjection> findByIsActiveTrueOrderByLastNameAsc(Pageable pageable);
    Long deleteByPhoneNumberStartsWith(String phoneNumber);
}
