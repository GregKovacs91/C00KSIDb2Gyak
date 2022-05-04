declare
beosztas_R char(4) := 'root';
beosztas char(30);
begin
beosztas:=
CASE beosztas_R
WHEN 'root' THEN 'Rendszergazda (UNIX/LINUX)'
WHEN 'system' THEN 'Rendszergazda (WINDOWS)'
WHEN 'codejava' THEN 'Fejleszt? (DEVELOPER JAVA)'
WHEN 'codec' THEN 'Fejleszt? (DEVELOPER C)'
ELSE 'Nincs ilyen beosztás'
END;
DBMS_OUTPUT.PUT_LINE('a beosztás: ' || beosztas);
END;