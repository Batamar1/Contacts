INSERT INTO public.department (id, name)
VALUES (DEFAULT, 'Тестирование');

INSERT INTO public.department (id, name)
VALUES (DEFAULT, 'Разработка');

INSERT INTO public.department (id, name)
VALUES (DEFAULT, 'Аналитика');

INSERT INTO public.email (id, email)
VALUES (DEFAULT, 'amelnikov@kaisha.ru');

INSERT INTO public.email (id, email)
VALUES (DEFAULT, 'vdergunov@kaisha.ru');

INSERT INTO public.email (id, email)
VALUES (DEFAULT, 'scorobaev@kaisha.ru');

INSERT INTO public.email (id, email)
VALUES (DEFAULT, 'amelnikov@work.ru');

INSERT INTO public.project (id, name)
VALUES (DEFAULT, 'Manabu');

INSERT INTO public.project (id, name)
VALUES (DEFAULT, 'Bunka');

INSERT INTO public.project (id, name)
VALUES (DEFAULT, 'Tenki');

INSERT INTO public.contact (id, first_name, last_name, patronymic, number, department_id)
VALUES (DEFAULT, 'Артем', 'Мельников', 'Егорович', '12321312', 2);

INSERT INTO public.contact (id, first_name, last_name, patronymic, number, department_id)
VALUES (DEFAULT, 'Вячеслав', 'Дергунов', 'Алексеевич', '21321312', 1);

INSERT INTO public.contact (id, first_name, last_name, patronymic, number, department_id)
VALUES (DEFAULT, 'Евгений', 'Скоробаев', 'Александрович', '33333333', 1);

INSERT INTO public.contact_emails (contact_id, emails_id)
VALUES (1, 1);

INSERT INTO public.contact_emails (contact_id, emails_id)
VALUES (1, 4);

INSERT INTO public.contact_emails (contact_id, emails_id)
VALUES (2, 2);

INSERT INTO public.contact_emails (contact_id, emails_id)
VALUES (3, 3);

INSERT INTO public.contact_projects (contact_id, projects_id)
VALUES (1, 1);

INSERT INTO public.contact_projects (contact_id, projects_id)
VALUES (1, 2);

INSERT INTO public.contact_projects (contact_id, projects_id)
VALUES (2, 1);

INSERT INTO public.contact_projects (contact_id, projects_id)
VALUES (3, 3);

INSERT INTO public.contact_projects (contact_id, projects_id)
VALUES (3, 2);





