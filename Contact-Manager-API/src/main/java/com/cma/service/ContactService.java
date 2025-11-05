package com.cma.service;

import com.cma.dto.ContactDto;
import com.cma.entity.Contact;
import com.cma.projection.ContactFirstNameEmail;
import com.cma.projection.ContactProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {

    Contact createContact(ContactDto contactDto);
    Contact findContactById(Long id);
    List<Contact> findContactByName(String firstName);
    Long findContactByInActive();
    List<ContactFirstNameEmail> findNameEmailByCategory(String category);
    Page<ContactProjection> findActiveContact(Pageable pageable);
    Long deleteContactBySamePrefix(String prefix);
    List<Contact> addMultipleContact(List<ContactDto> contactDtoList);
    List<Contact> findAllContact();
}
