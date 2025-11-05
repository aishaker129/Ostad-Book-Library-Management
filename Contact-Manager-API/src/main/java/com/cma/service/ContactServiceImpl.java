package com.cma.service;


import com.cma.dto.ContactDto;
import com.cma.entity.Contact;
import com.cma.projection.ContactFirstNameEmail;
import com.cma.projection.ContactProjection;
import com.cma.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ResourcePatternResolver resourcePatternResolver;

    @Override
    public Contact createContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmail(contactDto.getEmail());
        contact.setPhoneNumber(contactDto.getPhoneNumber());
        contact.setCategory(contactDto.getCategory());
        contact.setIsActive(contactDto.getIsActive());
        contact.setCreatedDate(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    @Override
    public Contact findContactById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null) {
            return  null;
        }
        return contact;
    }

    @Override
    public List<Contact> findContactByName(String firstName) {
        List<Contact> findContact = contactRepository.findContactByFirstNameIgnoreCase(firstName);
        if(findContact.isEmpty()){
            return  null;
        }
        return findContact;
    }

    @Override
    public Long findContactByInActive() {
        Long count = contactRepository.countByIsActiveFalse();
        return count;
    }

    @Override
    public List<ContactFirstNameEmail> findNameEmailByCategory(String category) {
        return contactRepository.findFirstAndEmailByCategory(category);
    }

    @Override
    public Page<ContactProjection> findActiveContact(Pageable pageable) {
        return contactRepository.findByIsActiveTrueOrderByLastNameAsc(pageable);
    }

    @Override
    @Transactional
    public Long deleteContactBySamePrefix(String prefix) {
        return contactRepository.deleteByPhoneNumberStartsWith(prefix);
    }

    @Override
    public List<Contact> addMultipleContact(List<ContactDto> contactDtoList) {

        List<Contact> contactList = new ArrayList<>();
        for(ContactDto contactDto : contactDtoList){
            Contact contact = new Contact();
            contact.setFirstName(contactDto.getFirstName());
            contact.setLastName(contactDto.getLastName());
            contact.setEmail(contactDto.getEmail());
            contact.setPhoneNumber(contactDto.getPhoneNumber());
            contact.setCategory(contactDto.getCategory());
            contact.setIsActive(contactDto.getIsActive());
            contact.setCreatedDate(LocalDateTime.now());
            contactList.add(contact);

        }
        return contactRepository.saveAll(contactList);
    }

    @Override
    public List<Contact> findAllContact() {
        List<Contact> findContact = contactRepository.findAll();
        return findContact;
    }


}
