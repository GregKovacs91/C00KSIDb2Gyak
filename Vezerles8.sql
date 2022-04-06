 DECLARE
 n number := 10;
 f0 number := 0; -- f_{n-2}
 f1 number := 1; -- f_{n-1}
 fa number := 2; -- f_{n}
 BEGIN
 FOR i IN 1..n LOOP
 fa := f0 + f1;
 f0 := f1;
 f1 := fa;
 dbms_output.put_line(TO_CHAR(fa));
 END LOOP;
 END;