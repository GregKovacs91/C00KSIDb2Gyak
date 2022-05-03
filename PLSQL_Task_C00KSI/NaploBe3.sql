create or replace trigger NaploBe3 after insert on pilotak for each row 
begin 
insert into Naplo values('Beszúrás', :new.csapatnev, sysdate); 
end;
