create or replace package Pack as
procedure NevMod(masikHelyszin varchar, MID number) as 
begin 
update palyak set helyszin = masikHelyszin where id=MID; 
end;

procedure versenyzokMod(masikNev varchar2, MRajtszam number) as 
begin 
update versenyzok set nev = masikNev where id=MRajtszam; 
end;

function CursorVersenyzok return char is listV char(70); 
cursor cur is select * from Versenyzok; 
cv cur%rowtype; 
begin 
open cur; 
loop 
fetch cur into cv; 
exit when cur%notfound; 
dbms_output.put_line('Rajtszam: ' || cv.rajtszam || ' Név: ' || cv.mennyiseg || ' Futamok száma: ' || cv.futamok || ' Gy?zelmek: ' || cv.gyozelmek || ' Debütálás: ' || cv.debutalas); 
end loop; 
close cur; 
return listV; 
end; 

Cursorpalyak return char is listaP char(70); 
cursor cur is select * from palyak; 
cv cur%rowtype; 
begin 
open cur; 
loop 
fetch cur into cv; 
exit when cur%notfound; 
dbms_output.put_line('Pálya azonosító: ' || cv.id || ' Pálya Név: ' || cv.helyszin || ' Ország: ' || cv.orszag || ' Verseny napja: ' || cv.futamnapja || ' Pálya hossza: ' || cv.palyahossz); 
end loop; 
close cur; 
return listaP; 
end;
end;
