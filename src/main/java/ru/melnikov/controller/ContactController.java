package ru.melnikov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.melnikov.dto.ContactDTO;
import ru.melnikov.entity.Contact;
import ru.melnikov.service.ContactService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/contacts") //produces = APPLICATION_JSON_VALUE
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping(value = "/")
    public Page<Contact> getContact(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Contact> contactPage = contactService.getContacts(email, pageRequest);
        return contactPage;
    }

    @GetMapping(value = "/email/{page}")
    public Page<Contact> getContactByEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Contact> contactPage = contactService.findContactByEmail(email,pageRequest);
      //  Page<Contact> contactPage = contactService.getContacts(email, pageRequest);

        return contactPage;
    }

    @PostMapping(value = "/")
    public String saveContact(@RequestBody ContactDTO contact){
        contactService.saveContact(contact);
        return "Success";
    }
}
