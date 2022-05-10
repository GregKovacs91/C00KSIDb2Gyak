create or replace function gyoztesVersenyzok(gyozelmek number) return number is 
db number:=0; 
begin 
select count(*) into db from versenyzok where gyozelmek>0; 
return db; 
end; 
