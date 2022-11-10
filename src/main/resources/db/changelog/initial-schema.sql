create table department
(
    id   bigserial
        primary key,
    name varchar(255)
);

alter table department
    owner to postgres;

create table project
(
    id   bigserial
        primary key,
    name varchar(255)
);

alter table project
    owner to postgres;

create table email
(
    id    bigserial
        primary key,
    email varchar(255)
);

alter table email
    owner to postgres;

create table contact
(
    id            bigserial
        primary key,
    first_name    varchar(255),
    last_name     varchar(255),
    patronymic    varchar(255),
    number        varchar(255),
    department_id bigint
        constraint fk4jijf38vu0fqfkgop0bi9rgc7
            references department
);

alter table contact
    owner to postgres;



create table contact_emails
(
    contact_id bigint not null
        constraint fkhxco8ferpny7ohg9plnyff96e
            references contact,
    emails_id  bigint not null
        constraint uk_q3sungihgn5hxnqo2f88exg5t
            unique
        constraint fktn6tant2u3snv018hy6harxfq
            references email
);

alter table contact_emails
    owner to postgres;



create table contact_projects
(
    contact_id  bigint not null
        constraint fkjf8aqqqeckejaci7fwh5tgsh7
            references contact,
    projects_id bigint not null
        constraint fk4xqcacojm0oafbvighylu3yis
            references project
);

alter table contact_projects
    owner to postgres;



