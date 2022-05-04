
declare
a number:=10;
b number:=81;
begin
if a>b then
dbms_output.put_line('A nagyobb ' || (a));
else
dbms_output.put_line('A nagyobb ' || (b));
end if;
end;