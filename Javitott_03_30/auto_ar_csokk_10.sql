create view vkor as select rsz, kor from autok;

declare
    x number(2) := 7;
begin
    update autok set ar=ar*0.9 where rsz in (select rsz from vkor where kor > x);
end;