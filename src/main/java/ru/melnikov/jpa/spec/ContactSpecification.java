package ru.melnikov.jpa.spec;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import ru.melnikov.entity.Contact;
import ru.melnikov.entity.Email;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class ContactSpecification implements Specification<Contact> {
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            Root<Email> rootEmail = query.from(Email.class);
            //rootEmail.join(rootEmail.get())
            Join<Contact, Email> emailJoin =  root.join(String.valueOf(root.get("emails")));
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}

