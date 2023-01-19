package ru.melnikov.jpa.spec;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import ru.melnikov.entity.Contact;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;//Used i


}
