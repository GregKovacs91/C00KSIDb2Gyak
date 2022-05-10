create sequence seq3
    START WITH 25
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 99999;
create or replace trigger palyaid before insert on palyak for each row
begin
:new.id := seq3.nextval;
end;