create or replace function futamokSzama(start in date, end in date) return number is 
db number:=0; 
cursor c is select * from palyak; 
begin 
open c; 
for c in (select * from palyak where futamnapja>kezdo and futamnapja<vegso) loop 
db:=db+1; 
end loop; 
close c; 
return db; 
end;
