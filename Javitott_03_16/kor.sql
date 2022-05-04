declare
pi constant number:=3.14;
r number:=12;
t number;
begin
t := r*r*pi;
dbms_output.put_line((t));
end;