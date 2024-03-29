-- board.sql
drop table board;

create table board(
  num number(8) primary key, -- 글번호
  userid varchar2(30) not null, --작성자 아이디
  subject varchar2(200), -- 제목
  content varchar2(2000), --글 내용
  wdate timestamp default systimestamp, --작성일
  readnum number(8) default 0, --조회수

);

drop sequence board_seq;

create sequence board_seq
start with 1 increment by 1 nocache;