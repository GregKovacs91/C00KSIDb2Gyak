create or replace procedure versenyzokMod(masikNev varchar2, MRajtszam number) is 
begin 
update versenyzok set nev = masikNev where rajtszam=MRajtszam; 
end;
