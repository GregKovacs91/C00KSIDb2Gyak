create or replace procedure listaVersenyzok as 
cursor cur is select * from versenyzok; 
cv cur%rowtype; 
begin 
open cur; 
loop 
fetch cur into cv; 
exit when cur%notfound; 
dbms_output.put_line('Rajtszam: ' || cv.rajtszam || ' N�v: ' || cv.mennyiseg || ' Futamok sz�ma: ' || cv.futamok || ' Gy?zelmek: ' || cv.gyozelmek || ' Deb�t�l�s: ' || cv.debutalas); 
end loop; 
close cur; 
end;
