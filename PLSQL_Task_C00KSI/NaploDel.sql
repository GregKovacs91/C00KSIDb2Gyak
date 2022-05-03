create or replace trigger NaploDel after delete on versenyzok for each row 
begin 
insert into Naplo values('Delete', :old.rajtszam, sysdate); 
end; 
