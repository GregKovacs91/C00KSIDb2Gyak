create or replace function Cursorpalyak return char is listaP char(70); 
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
