--call next value for hibernate_sequence;  -- id값 증가시켜주는 코드
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (1, 'woosong1', 'woosong11@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (2, 'woosong2', 'woosong22@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (3, 'woosong3', 'woosong33@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (4, 'woosong4', 'woosong44@naver.com', now(), now());

--call next value for hibernate_sequence;
insert into user (`id`, `name`, `email`, `created_at`, `updated_at`)
values (5, 'woosong1', 'woosong55@naver.com', now(), now());

insert into publisher(`id`, `name`) values(1, '패스트캠퍼스');
insert into book (`id`, `name`, `publisher_id`, `deleted`, `status`) values (1, 'JPA CASCADE', 1, false, 100) ;
insert into book (`id`, `name`, `publisher_id`, `deleted`, `status`) values (2, '다음은 SPRING SECURITY', 1, false, 200) ;
insert into book (`id`, `name`, `publisher_id`, `deleted`, `status`) values (3, 'Soft Delete', 1, true, 100) ;
