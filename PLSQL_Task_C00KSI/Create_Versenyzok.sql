create table versenyzok(rajtszam number(2) primary key, nev varchar2(20) not null, 
futamok number(3) check(futamok>0), gyozelmek number(3), debutalas date, 
csapat varchar(30) foreign key (csapat) references Csapat(csapat));