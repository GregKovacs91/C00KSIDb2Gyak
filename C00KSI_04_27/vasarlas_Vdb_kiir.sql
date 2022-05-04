create or replace procedure VDbKiir is
cursor cur is select * from vasarlas;
cv cur%rowtype;
begin
open cur;
loop
fetch cur into cv;
exit when cur%notfound;
end loop;
dbms_output.put_line(cur%rowcount);
close cur;
end;