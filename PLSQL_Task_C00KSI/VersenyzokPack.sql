create or replace package versenyzokPack is 
function futamokSzama(start in date, end in date) return number; 
procedure versenyzokMod(masikNev varchar2, MRajtszam number);
end versenyzokPack;

create or replace package body versenyzokPack is 
function futamokSzama(kezdo in date, vegso in date) return number is 
cursor c is select * from palyak; 
begin 
open c; 
for c in (select * from palyak where futamnapja>kezdo and futamnapja<vegso) loop 
db:=db+1; 
end loop; 
close c; 
return db; 
end futamokSzama;

procedure versenyzokMod(masikNev varchar2, MRajtszam number) is 
begin 
update versenyzok set nev = masikNev where id=MRajtszam; 
end mennyMOD;
end versenyzokPack
