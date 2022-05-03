create or replace procedure versenyzokDel(rajtszamDel number) is 
begin 
delete from versenyzok where rajtszamDel = rajtszam; 
commit; 
end;
