
declare
a number:= 3;
b number:= 4;
c number:= 5;
begin
if a<b+c and b<a+c and c<b+a then
DBMS_OUTPUT.PUT_LINE('szerkezthet� bel�l�k h�romsz�g');
end if;
end;