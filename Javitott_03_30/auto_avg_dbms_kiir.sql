declare

    atlag number;

BEGIN

    select avg(ar) into atlag from autok;
    DBMS_OUTPUT.put_line(atlag);

END;