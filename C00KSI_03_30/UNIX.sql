 DECLARE
 beosztas VARCHAR(10) := 'coder';
 BEGIN
 CASE
 WHEN beosztas = 'dev' THEN dbms_output.put_line('Fejleszto, tervezo');
 WHEN beosztas = 'root' THEN dbms_output.put_line('Rendszergazda (UNIX/LINUX)');
 WHEN beosztas = 'admin' THEN dbms_output.put_line('Rendszergazda (Windows)');
 WHEN beosztas = 'dba' THEN dbms_output.put_line('Adatbazis Adminisztrator');
 WHEN beosztas = 'coder' THEN dbms_output.put_line('Programozo');
 WHEN beosztas = 'sec' THEN dbms_output.put_line('Adminisztrativ');
 ELSE dbms_output.put_line('egyedi munkakor');
 END CASE;
 END;