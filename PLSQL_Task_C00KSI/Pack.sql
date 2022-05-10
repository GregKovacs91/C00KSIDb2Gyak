create or replace package Pack is 
procedure NevMod(masikHelyszin varchar, MID number); 
procedure versenyzokMod(masikNev varchar2, MRajtszam number);
function CursorVersenyzok return char is listV char(70);
function Cursorpalyak return char is listaP char(70); 

end;
