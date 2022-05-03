create or replace trigger NaploUp2 after update on palyak for each row 
begin 
insert into Naplo values('Modify', :new.id, sysdate); 
end;
