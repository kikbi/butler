create database test;
drop table if exists test.issue;
create table test.issue(
                             id bigint primary key,
                             login_name varchar(16) not null ,
                             password varchar(16) not null
);
