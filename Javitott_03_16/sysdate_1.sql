declare
l_seed char(100);
begin
l_seed := to_char(SYSTIMESTAMP, 'YYYY-MM-DD');
dbms_output.put_line((l_seed));
end;