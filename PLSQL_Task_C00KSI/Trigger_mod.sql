create or replace trigger trigger_mode
before update on versenyzok for each row
declare
begin
if :new.rajtszam not between 0 and 100 then
dbms_output.put_line('ne adjon meg 0 vagy 100-n�l nagyobb rajtsz�mot a pil�t�nak ');
:new.rajtszam:=:old.rajtszam;
end if;
end;