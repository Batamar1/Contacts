package ru.melnikov.filter;

import lombok.Data;

@Data
public class ContactFilter {
    private String FIO;
    private String email;
    private Long idDepartment;
    private Long idProject;
}
