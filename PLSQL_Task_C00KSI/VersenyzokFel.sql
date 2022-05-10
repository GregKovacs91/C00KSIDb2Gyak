create or replace procedure versenyzokFel(rajtszam number, nev varchar, futamok number, gyozelmek number, debutalas date, csapatnev VARCHAR) is 

begin  
insert into versenyzok values(rajtszam, nev, futamok, gyozelmek, debutalas, csapatnev); 
end;
