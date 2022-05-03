create or replace procedure feltoltPalyak(id number, helyszin varchar, orszag varchar, futamnapja, palyahossz) is 
perror exception; 
begin 
if id<=0 or palyahossz<=0 then 
raise perror; 
else 
insert into versenyzok values(id, helyszin, orszag, futamnapja, palyahossz); 
commit; 
end if; 
exception 
when perror then 
dbms_output.put_line(A pálya azonósítója és a pálya hossza nem lehet nulla!'); 
end;
