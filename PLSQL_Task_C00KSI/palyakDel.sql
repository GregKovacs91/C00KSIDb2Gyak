create or replace procedure palyakDel(IDD number) is 
begin 
delete from palyak where idd = id; 
commit; 
end;
