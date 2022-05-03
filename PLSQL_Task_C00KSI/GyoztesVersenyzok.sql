create or replace function gyoztesVersenyzok(gyozelem in number) return number is 
db number:=0; 
begin 
select count(*) into db from versenyzok where gyozelem<=gyozelmek; 
return db; 
end; 
