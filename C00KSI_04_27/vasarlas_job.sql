declare
    jobno number ;
begin

dbms_job.submit (jobno , ' sp1;', (sysdate+1/3600),'sysdate+ 1');
commit;
dbms_output.put_line('A job sz�ma: ' || jobno);
end;