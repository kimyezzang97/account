-- yml에서 defer-datasource-initialization: true를 해줬기에 sql을 실행할 수 있다. (테이블 생성 이후로 쿼리를 실행 하기 위해)
insert into account_user(id, name, created_at, updated_at)
values(1, 'Pororo', now(), now()),
    (2, 'Lupy', now(), now()),
    (3, 'Eddie', now(), now());


