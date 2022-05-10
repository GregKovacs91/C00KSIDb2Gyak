create or replace procedure feltoltPalyak(id number, helyszin varchar, orszag varchar, futamnapja date, palyahossz number) is 

begin 

insert into palyak values(id, helyszin, orszag, futamnapja, palyahossz); 

end;
