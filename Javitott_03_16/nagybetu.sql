declare
t varchar(30):='Kov�cs S�ndor Gergo';
begin
select upper(t) into t from dual;
dbms_output.put_line((t));
select lower(t) into t from dual;
dbms_output.put_line((t));
end;