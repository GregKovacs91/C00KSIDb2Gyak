BEGIN
DBMS_SCHEDULER.CREATE_JOB (
job_name => 'vasarlas_job',
job_type => 'STORED_PROCEDURE',
job_action => 'VDbKiir', --a tárolt eljárás neve
start_date => SYSTIMESTAMP,
repeat_interval => 'FREQ=MINUTELY; INTERVAL=1',
end_date => SYSTIMESTAMP + INTERVAL '30' day,
comments => 'Vasarlas');
END;