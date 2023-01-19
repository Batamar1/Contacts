package ru.melnikov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.melnikov.dto.ContactDTO;
import ru.melnikov.entity.*;
import ru.melnikov.filter.ContactFilter;
import ru.melnikov.jpa.ContactRepository;
import ru.melnikov.jpa.spec.ContactSpecification;
import ru.melnikov.jpa.spec.Filter;
import ru.melnikov.jpa.spec.QueryOperator;
import ru.melnikov.jpa.spec.SearchCriteria;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;



    public Page<Contact> getContactByFilter(ContactFilter contactFilter, Pageable pageable) {

        String[] fio = contactFilter.getFIO().split(" ");
        var spec = ContactSpecification.departmentSpec(contactFilter)
                .or(ContactSpecification.emailSpec(contactFilter))
                .or(ContactSpecification.projectSpec(contactFilter));
        Specification<Contact> fioSpec = null;
        for (int i = 0; i < fio.length; i++) {
            switch (i) {
                case 0 -> fioSpec = ContactSpecification.lastNameSpec(fio[0]);
                case 1 -> fioSpec = fioSpec.and(ContactSpecification.firstNameSpec(fio[1]));
                case 2 -> fioSpec = fioSpec.and(ContactSpecification.patronymicSpec(fio[2]));
                default -> System.out.println("default");
            }
        }
        if(fioSpec != null) spec = spec.or(fioSpec);

        return contactRepository.findAll(spec, pageable);

        //        List<Filter> filters = new ArrayList<>();
//        filters.add(Filter.builder()
//                .field("department")
//                .operator(QueryOperator.IN)
//                .value(contactFilter.getIdDepartment().toString())
//                .build());

        // return contactRepository.findAll(getSpecificationFromFilters(filters), pageable);

        // return contactRepository.findContactByEmails_emailContainingAndDepartment_Id(contactFilter.getEmail(), contactFilter.getIdDepartment(), pageable);
        //return getContacts(contactFilter.getEmail(),null, null, pageable);
    }

    //    public Page<Contact> getContacts(Pageable pageable) {
//        return getContacts(null, null, null, pageable);
//    }

//    public Page<Contact> getContacts(String email, Integer departmentId, Integer projectId, Pageable pageable) {
//        Specification<Contact> spec = null;
//        if (email != null) {
//            ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("emails", ":", email));
//            spec = where(contactSpecification);
//        }
//        if (departmentId != null) {
//            //ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("id", ":", departmentId));
//            //    spec = Specification.where(contactSpecification);
//        }
//        if (projectId != null) {
//            //  ContactSpecification contactSpecification = new ContactSpecification(new SearchCriteria("id", ":", projectId));
//            //  spec = Specification.where(contactSpecification);
//        }
//
//        if (spec == null) {
//            return contactRepository.findAll(pageable);
//        } else {
//            return contactRepository.findAll(spec, pageable);
//        }
//    }

//    private Specification<Contact> getSpecificationFromFilters(List<Filter> filter) {
//        Specification<Contact> specification =
//                where(createSpecification(filter.remove(0)));
//        for (Filter input : filter) {
//            specification = specification.and(createSpecification(input));
//        }
//        return specification;
//    }
//
//    private Specification<Contact> createSpecification(Filter input) {
//        return switch (input.getOperator()) {
//            case EQUALS -> (root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get(input.getField()),
//                            castToRequiredType(root.get(input.getField()).getJavaType(),
//                                    input.getValue()));
//            case NOT_EQUALS -> (root, query, criteriaBuilder) ->
//                    criteriaBuilder.notEqual(root.get(input.getField()),
//                            castToRequiredType(root.get(input.getField()).getJavaType(),
//                                    input.getValue()));
//            case GREATER_THAN -> (root, query, criteriaBuilder) ->
//                    criteriaBuilder.gt(root.get(input.getField()),
//                            (Number) castToRequiredType(
//                                    root.get(input.getField()).getJavaType(),
//                                    input.getValue()));
//            case LESS_THAN -> (root, query, criteriaBuilder) ->
//                    criteriaBuilder.lt(root.get(input.getField()),
//                            (Number) castToRequiredType(
//                                    root.get(input.getField()).getJavaType(),
//                                    input.getValue()));
//            case LIKE -> (root, query, criteriaBuilder) ->
//                    criteriaBuilder.like(root.get(input.getField()),
//                            "%" + input.getValue() + "%");
//            case IN -> (root, query, criteriaBuilder) -> {
//                if (input.getValues() == null) return root.get(input.getField()).in(input.getValue());
//                else return root.get(input.getField()).in(input.getValues());
////                            .value(castToRequiredType(
////                                    root.get(input.getField()).getJavaType(),
////                                    ));
//            };
//            default -> throw new RuntimeException("Operation not supported yet");
//        };
//    }
//
//    private Object castToRequiredType(Class fieldType, String value) {
//        if (fieldType.isAssignableFrom(Double.class)) {
//            return Double.valueOf(value);
//        } else if (fieldType.isAssignableFrom(Integer.class)) {
//            return Integer.valueOf(value);
//        } else if (QueryOperator.class.isAssignableFrom(fieldType)) {
//            return QueryOperator.valueOf(fieldType, value);
//        }
//        return null;
//    }
//
//    private Object castToRequiredType(Class fieldType, List<String> value) {
//        List<Object> lists = new ArrayList<>();
//        for (String s : value) {
//            lists.add(castToRequiredType(fieldType, s));
//        }
//        return lists;
//    }

    public void saveContact(ContactDTO contactDTO) {
        var contact = new Contact();
        contact.setFirstName(contactDTO.getName());
        contact.setNumber(contactDTO.getNumber());
        contactRepository.save(contact);
    }



    public Page<Contact> findContactByEmail(String email, String depName, String projectName, Pageable pageable) {
        return contactRepository.findContactByEmails_EmailContainingAndDepartment_NameAndProjects_Name(email, depName, projectName, pageable);
    }
}
