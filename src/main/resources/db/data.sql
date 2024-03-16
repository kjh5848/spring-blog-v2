insert into user_tb(username, password, email, created_at)
values
    ('qqq', '1234', 'qqq@nate.com', now()),
    ('www', '1234', 'www@nate.com', now()),
    ('eee', '1234', 'eee@nate.com', now());

insert into board_tb(user_id, title, content, created_at)
values
    (1, '제목1', '내용1', now()),
    (1, '제목2', '내용2', now()),
    (2, '제목3', '내용3', now()),
    (3, '제목4', '내용4', now());


insert into reply_tb(user_id, board_id, content, created_at)
values
    (1, 1,'1번이 제목1에 남김', now()),
    (1, 2,'1번이 제목2에 남김', now()),
    (1, 3,'1번이 제목3에 남김', now()),
    (2, 1,'2번이 제목1에 남김', now()),
    (2, 2,'2번이 제목2에 남김', now()),
    (2, 3,'2번이 제목3에 남김', now());



