create or replace procedure palyaDateMod(MID number,Mfutamnapja date) is 
begin 
update palyak set futamnapja = Mfutamnapja where id=MID; 
commit; 
end;
