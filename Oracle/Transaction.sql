create table account(
  ano varchar(20) primary key,
  aowner varchar(10) not null,
  abalance number not null
);

--Rollback: 마지막 commit까지 되돌린다=현재 작업을 취소한다.
insert into account values('111-111', '홍길동', 1000000);
insert into account values('222-222', '스프링', 0);

--commit(현재 작업을 영구적으로 저장한다(반영한다))
select * from account;
commit;
rollback;

--기능 단위의 트랜잭션 처리
update account set abalance=abalance-10000 where ano='111-111';
update account set abalance=abalance+10000 where ano='222-222';
select * from account;
rollback;
select * from account;