create or replace trigger NaploBe after insert on versenyzok for each row 
begin 
insert into Naplo values('Beszúrás', :new.rajtszam, sysdate); 
end; 
