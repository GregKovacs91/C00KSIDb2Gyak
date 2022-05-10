create or replace procedure feltoltCsapat(csapatnev char, futamokszama number, gyozelmek number, podiumok number, bajnoksagok number, debutalas date) is 

begin 
insert into csapat values(csapatnev, futamokszama, gyozelmek, podiumok, bajnoksagok, debutalas); 
end;
