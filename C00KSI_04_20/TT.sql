create or replace trigger TT after delete on vasarlo for each row
begin
insert into naplo5 values('T�rl�s', :new.VID||'_'||:new.NEV||'_'||:new.CIM, sysdate);
end;