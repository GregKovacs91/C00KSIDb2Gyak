 DECLARE
 n number := 10;
 BEGIN
 FOR i IN 1..n LOOP
 dbms_output.put_line(TO_CHAR(i));
 END LOOP;
 END;
