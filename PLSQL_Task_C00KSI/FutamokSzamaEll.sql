declare 
a date := TO_DATE('2002/04/01', 'YYYY/MM/DD'); 
b date:= TO_DATE('2002/07/01', 'YYYY/MM/DD'); 
c number; 
begin 
c:=futamokSzama(a,b); 
DBMS_OUTPUT.put_line(a || ' �s ' || b || ' k�z�tt megrendezett futamok sz�ma: ' || c); 
end;
