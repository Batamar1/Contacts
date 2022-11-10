package ru.melnikov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.melnikov.dto.ContactDTO;
import ru.melnikov.entity.Contact;
import ru.melnikov.jpa.ContactRepository;
import ru.melnikov.jpa.spec.ContactSpecification;
import ru.melnikov.jpa.spec.SearchCriteria;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public Page<Contact> getContacts(Pageable pageable) {
        return getContacts(null, null, null, pageable);
    }

    public Page<Contact> getContacts(String email, Pageable pageable) {
        return getContacts(email,null, null, pageable);
    }
    public Page<Contact> getContacts(String email, Integer departmentId, Integer projectId, Pageable pageable) {
        Specification<Contact> spec = null;
        if(email != null){
            ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("emails", ":", email));
            spec = Specification.where(contactSpecification);
        }
        if(departmentId != null){
            //ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("id", ":", departmentId));
        //    spec = Specification.where(contactSpecification);
        }
        if(projectId != null){
          //  ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("id", ":", projectId));
          //  spec = Specification.where(contactSpecification);
        }

        if(spec == null){
            return contactRepository.findAll(pageable);
        }else{
            return contactRepository.findAll(spec, pageable);
        }
    }

    public void saveContact(ContactDTO contactDTO) {
        var contact = new Contact();
        contact.setFirstName(contactDTO.getName());
        contact.setNumber(contactDTO.getNumber());
        contactRepository.save(contact);
    }

    public Page<Contact> findContactByEmail(String email, Pageable pageable){
        return contactRepository.findContactByEmails_emailContaining(email, pageable);
    }

    public Page<Contact> findContactByEmail(String email, String depName, String projectName, Pageable pageable){
        return contactRepository.findContactByEmails_EmailContainingAndDepartment_NameAndProjects_Name(email,depName, projectName, pageable);
    }
}
