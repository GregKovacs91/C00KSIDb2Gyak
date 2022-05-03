create or replace trigger NaploUp3 after update on pilotak for each row 
begin 
insert into Naplo values('Modify', :new. csapatnev, sysdate); 
end;
