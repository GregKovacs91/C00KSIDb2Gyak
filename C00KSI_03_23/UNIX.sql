DECLARE
 a number := 3;
 b number := 4;
 c number := 5;
 BEGIN
 IF (a > b+c) or (b > a+c) or (c > a+b) THEN
 dbms_output.put_line('A '||a||' '||b||' '||c||' szamharmas nem alkot
haromszoget!!!');
 ELSE
 dbms_output.put_line('A '||a||' '||b||' '||c||' szamharmas haromszoget
alkot!!!');
 END IF;
 END;