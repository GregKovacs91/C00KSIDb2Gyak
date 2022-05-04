declare

    db number:=0;

begin

    select count(rsz) into db from autok;
    dbms_output.put_line(db);

end;