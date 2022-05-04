declare
    r autok.kor%type := 10;
begin
    delete from autok where kor>r;
end;
