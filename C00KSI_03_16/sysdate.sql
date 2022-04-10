DECLARE 
  
    -- Variables to store area and perimeter
    area   NUMBER(6, 2)  ;  
    perimeter   NUMBER(6, 2)  ; 
      
    --Assigning the value of radius 
    radius NUMBER(1) := 3; 
      
    --Constant value of  PI 
    pi CONSTANT NUMBER(3, 2) := 3.14; 
      
BEGIN 
  
        --Formula for area and perimeter of a circle 
        area := pi * radius * radius; 
        perimeter := 2 * pi * radius;
        dbms_output.Put_line('Area = ' || area); 
        dbms_output.Put_line(' Perimeter = ' || perimeter); 
  
END; 