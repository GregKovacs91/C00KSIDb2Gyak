create or replace trigger NaploUp after update on versenyzok for each row 
begin 
insert into Naplo values('Modify', :new.rajtszam, sysdate); 
end;
