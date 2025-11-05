package com.cma.controller;

import com.cma.dto.ContactDto;
import com.cma.entity.Contact;
import com.cma.projection.ContactFirstNameEmail;
import com.cma.projection.ContactProjection;
import com.cma.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDto contactDto) {
        Contact contact = contactService.createContact(contactDto);
        if (contact != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(contact);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactService.findContactById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contact);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<Contact>> getContactByFirstName(@RequestParam String term) {
        List<Contact> contact = contactService.findContactByName(term);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contact);
    }
    @GetMapping("/count-inactive")
    public ResponseEntity<Long> countInactiveContacts() {
        Long count = contactService.findContactByInActive();
        if (count == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(count);
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<ContactFirstNameEmail>> getContactByCategory(@RequestParam String name) {
        List<ContactFirstNameEmail> list = contactService.findNameEmailByCategory(name);
        if (list == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/active/page")
    public ResponseEntity<Page<ContactProjection>> getActiveContacts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastName").descending());
        Page<ContactProjection> contactProjections = contactService.findActiveContact(pageable);
        if(contactProjections.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contactProjections);
    }

    @DeleteMapping("/delete-by-prefix")
    public ResponseEntity<String> deleteContactByPrefix(@RequestParam String prefix) {
        Long count = contactService.deleteContactBySamePrefix(prefix);
        if(count == 0){
            return ResponseEntity.ok("No contact fond with prefix " + prefix);
        }
        return ResponseEntity.ok("Deleted " + count + " contacts");
    }

    @PostMapping("/mass-contacts")
    public ResponseEntity<List<Contact>> saveContact(@RequestBody List<ContactDto> contactDto) {
        List<Contact> contactResponseDtoList = contactService.addMultipleContact(contactDto);
        if(contactResponseDtoList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contactResponseDtoList);
    }

    @GetMapping("/all-contacts")
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contactList = contactService.findAllContact();
        if(contactList.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(contactList);
    }
}
