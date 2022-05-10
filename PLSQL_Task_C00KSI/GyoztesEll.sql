
declare 
a number; 
b number:=2; 
begin 
a := gyoztesVersenyzok(b); 
DBMS_OUTPUT.put_line('mennyi pilóta van akinek ' ||a|| '-nál/nél több gy?zelme van: ' || b); 
end;
