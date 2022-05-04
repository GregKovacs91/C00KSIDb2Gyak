
declare
a number:=3;
b number:=4;
c number:=5;
s number;
T number;
begin
s := (a+b+c)/2;
T := sqrt(s*(s-a)*(s-b)*(s-c));
DBMS_OUTPUT.PUT_LINE('a háromszög területe: ' || T);
END;