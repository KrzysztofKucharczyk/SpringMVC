insert into book (id, title, authors, status) values (1, 'First book', 'Jan Kowalski', 'FREE');
insert into book (id, title, authors, status) values (5, 'First book', 'Zbigniew Nowak', 'MISSING');
insert into book (id, title, authors, status) values (2, 'Second book', 'Zbigniew Nowak', 'FREE');
insert into book (id, title, authors, status) values (3, 'Third book', 'Janusz Jankowski', 'FREE');
insert into book (id, title, authors, status) values (4, 'Scary book', 'Chuck Norris', 'LOAN');

insert into USER_AUTHENTICATION (id, username, password) values (1, 'admin', 'admin');
insert into USER_AUTHENTICATION (id, username, password) values (2, 'user', 'user');

insert into USER_AUTHORIZATION(role_id, user_id, role) values (1, 1, 'ROLE_ADMIN');
insert into USER_AUTHORIZATION(role_id, user_id, role) values (2, 2, 'ROLE_READER');