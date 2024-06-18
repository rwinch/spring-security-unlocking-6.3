insert into message_user(id, first_name, last_name, email, password) values (0, 'Rob','Winch', 'rob@example.com', 'password');
insert into message_user(id, first_name, last_name, email, password) values (1, 'Josh','Long', 'josh@example.com', 'password');
insert into message_user(id, first_name, last_name, email, password) values (2, 'Eve','Il', 'eve@example.com', 'password');

insert into message(id, summary, text, from_id, to_id) values (100, 'Hi Josh', 'This message is from Rob', 0, 1);

insert into message(id, summary, text, from_id, to_id) values (110, 'Hi Rob', 'This message is from Josh', 1, 0);
