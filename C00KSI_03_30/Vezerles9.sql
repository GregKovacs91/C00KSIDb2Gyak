DECLARE
 n number := 10;
 gyok number;
 gyoke boolean := true;
 BEGIN
 gyok := ROUND(SQRT(n));
 FOR i IN 2..gyok LOOP
 IF (mod(n,i) = 0) and (gyoke = true) THEN
 gyoke := false;
 dbms_output.put_line(n||' oszthato '||i||'-vel');
 END IF;
 END LOOP;