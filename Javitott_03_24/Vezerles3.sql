declare
mini number:=10;
maxi number :=100;
e number:=78;
begin
if e<maxi and e>mini then
DBMS_OUTPUT.PUT_LINE('beleesik a tartományba ');
else
DBMS_OUTPUT.PUT_LINE('nem esik bele a tartományba ');
end if;
END;