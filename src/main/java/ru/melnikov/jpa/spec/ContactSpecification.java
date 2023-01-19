package ru.melnikov.jpa.spec;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import ru.melnikov.entity.Contact;
import ru.melnikov.entity.Contact_;
import ru.melnikov.entity.Email;
import ru.melnikov.entity.Project;
import ru.melnikov.filter.ContactFilter;

import javax.persistence.criteria.*;


public class ContactSpecification {
    public static Specification<Contact> departmentSpec(ContactFilter contactFilter) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return root.get(Contact_.department).in(contactFilter.getIdDepartment());
            }
        };
    }

    public static Specification<Contact> emailSpec(ContactFilter contactFilter) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                Join<Email, Contact> emailContactJoin = root.join("emails");
                return criteriaBuilder.like(emailContactJoin.get("email"), "%" + contactFilter.getEmail() + "%");
            }
        };
    }

    public static Specification<Contact> projectSpec(ContactFilter contactFilter) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                Join<Project, Contact> emailContactJoin = root.join("projects");
                return criteriaBuilder.equal(emailContactJoin.get("id"), contactFilter.getIdProject());
            }
        };
    }

    public static Specification<Contact> lastNameSpec(String lastName) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.like(root.get(Contact_.lastName), "%" + lastName + "%");
            }
        };
    }

    public static Specification<Contact> firstNameSpec(String firstName) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.like(root.get(Contact_.firstName), "%" + firstName + "%");
            }
        };
    }

    public static Specification<Contact> patronymicSpec(String patronymic) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.like(root.get(Contact_.patronymic), "%" + patronymic + "%");
            }
        };
    }
}

