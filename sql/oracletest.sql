create sequence board_seq increment by 1 start with 1 nocycle nocache

create table member(
mno number(5) not null,
mname nvarchar2(10) not null, 
mid nvarchar2(10) primary key,
mpw nvarchar2(10) not null,
regidate date default sysdate not null
)

drop table member

insert into member (mno, mname, mid, mpw)
values (board_seq.nextval, '김기원', 'kkw'，'1234')
insert into member (mno, mname, mid, mpw)
values (board_seq.nextval, '김진우', 'kjw'，'1234')
insert into member (mno, mname, mid, mpw)
values (board_seq.nextval, '전민기', 'jmg'，'1234')
insert into member (mno, mname, mid, mpw)
values (board_seq.nextval, '김보령', 'kbr'，'1234')

select * from member