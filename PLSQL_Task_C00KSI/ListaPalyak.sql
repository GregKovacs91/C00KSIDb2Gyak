create or replace procedure listaPalyak as 
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
end;
