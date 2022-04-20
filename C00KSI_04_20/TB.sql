create or replace trigger TB after insert on vasarlo for each row
begin
insert into naplo5 values('Beszúrás', :new.VID||'_'||:new.NEV||'_'||:new.CIM, sysdate);
end;