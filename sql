select * from buboard;
--------------------------------------
--프로시저 
declare
 i number := 1; 
 begin
 while i<=1000 loop
 insert into buboard(bno,title ,content,writer) values
 ((select nvl(max(bno)+1,1) from buboard), '제목'||i, '내용'||i,'kim');
 i := i+1;
 end loop; 
 end;
 /
 -------------------------------
 select count(*) from buboard;
 select * from buboard;
 commit;
