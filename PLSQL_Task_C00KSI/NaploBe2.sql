create or replace trigger NaploBe2 after insert on palyak for each row 
begin 
insert into Naplo values('Besz�r�s', :new.id, sysdate); 
end; 
