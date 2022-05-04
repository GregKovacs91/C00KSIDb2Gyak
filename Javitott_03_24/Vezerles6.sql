
declare
a number:= 3;
b number:= 4;
c number:= 5;
begin
if a<b+c and b<a+c and c<b+a then
DBMS_OUTPUT.PUT_LINE('szerkezthetö belölük háromszög');
end if;
end;