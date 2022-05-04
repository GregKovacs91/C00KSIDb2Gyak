declare
n number := 10;
a number:=0;
b number:=1;
c number;
begin
for i in 1..n loop
dbms_output.put_line(a);
c := a + b;
a := b;
b := c;
end loop;
end;