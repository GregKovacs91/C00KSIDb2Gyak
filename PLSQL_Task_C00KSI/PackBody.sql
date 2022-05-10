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
dbms_output.put_line('Rajtszam: ' || cv.rajtszam || ' N�v: ' || cv.mennyiseg || ' Futamok sz�ma: ' || cv.futamok || ' Gy?zelmek: ' || cv.gyozelmek || ' Deb�t�l�s: ' || cv.debutalas); 
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
dbms_output.put_line('P�lya azonos�t�: ' || cv.id || ' P�lya N�v: ' || cv.helyszin || ' Orsz�g: ' || cv.orszag || ' Verseny napja: ' || cv.futamnapja || ' P�lya hossza: ' || cv.palyahossz); 
end loop; 
close cur; 
return listaP; 
end;
end;
