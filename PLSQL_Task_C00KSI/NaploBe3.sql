create or replace trigger NaploBe3 after insert on csapat for each row 
begin 
insert into Naplo values('Besz�r�s', :new.csapatnev, sysdate); 
end;
