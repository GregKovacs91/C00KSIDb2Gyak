create or replace procedure versenyzokFel(rajtszam number, nev varchar, futamok number, gyozelmek number, debutalas date) is 
perror exception; 
begin 
if rajtszam<=0 or futamok<=0 then 
raise perror; 
else 
insert into versenyzok values(rajtszam, nev, futamok, gyozelmek, debutalas); 
commit; 
end if; 
exception 
when perror then 
dbms_output.put_line('A rajtszám és a futamok száma nem lehet nulla !'); 
end;
