
declare 
a number; 
b number:=2; 
begin 
a := gyoztesVersenyzok(b); 
DBMS_OUTPUT.put_line('mennyi pil�ta van akinek ' ||a|| '-n�l/n�l t�bb gy?zelme van: ' || b); 
end;
