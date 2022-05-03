create or replace trigger NaploDel2 after delete on palyak for each row 
begin 
insert into Naplo values('Delete', :old.id, sysdate); 
end; 
