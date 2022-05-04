declare
a number:=7;
oszto integer := 2;
prim boolean := true;
begin
loop
if mod(a,oszto)=0 then
prim := false;
end if;
oszto := oszto+1;
exit when prim=false or oszto>a/2;
end loop;
dbms_output.put_line(' prímszám: ' || case when prim = true then 'igen' else 'nem' end);
end;