create or replace trigger TM after update on vasarlo for each row
begin
insert into naplo5 values('M�dos�t�s', :new.VID||'_'||:new.NEV||'_'||:new.CIM, sysdate);
end;