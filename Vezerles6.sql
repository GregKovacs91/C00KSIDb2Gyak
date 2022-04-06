DECLARE
 a number := 3;
 b number := 4;
 c number := 5;
 s number;
 t number;
 BEGIN
 IF (a > b+c) or (b > a+c) or (c > a+b) THEN
 dbms_output.put_line('A '||a||' '||b||' '||c||' szamharmas nem alkot
haromszoget!!!');
 ELSE
 s := (a+b+c)/2;
 t := SQRT(s*(s-a)*(s-b)*(s-c));
 dbms_output.put_line('A '||a||' '||b||' '||c||' szamharmas haromszoget alkot,
aminek a terulete '||t||'!!!');