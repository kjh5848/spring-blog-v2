insert into user_tb(username, password, email, created_at)
values ('qqq', '1234', 'qqq@nate.com', now()),
       ('www', '1234', 'www@nate.com', now()),
       ('eee', '1234', 'eee@nate.com', now());

insert into board_tb(user_id, title, content, created_at)
values (1, '제목1', '내용1', now()),
       (1, '제목2', '내용2', now()),
       (2, '제목3', '내용3', now()),
       (3, '제목4', '내용4', now());