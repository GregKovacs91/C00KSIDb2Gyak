create or replace trigger NaploDel3 after delete on pilotak for each row 
begin 
insert into Naplo values('Delete', :old.csapatnev, sysdate); 
end;
