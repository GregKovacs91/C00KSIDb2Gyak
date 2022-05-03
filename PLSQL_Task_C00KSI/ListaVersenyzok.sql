create or replace procedure listaVersenyzok as 
cursor cur is select * from versenyzok; 
cv cur%rowtype; 
begin 
open cur; 
loop 
fetch cur into cv; 
exit when cur%notfound; 
dbms_output.put_line('Rajtszam: ' || cv.rajtszam || ' Név: ' || cv.mennyiseg || ' Futamok száma: ' || cv.futamok || ' Gy?zelmek: ' || cv.gyozelmek || ' Debütálás: ' || cv.debutalas); 
end loop; 
close cur; 
end;
