create or replace procedure NevMod(masikHelyszin varchar, MID number) is 
begin 
update palyak set helyszin = masikHelyszin where id=MID; 
end;
