with numbers(num, max_factor) as (
  select level, trunc(level / 2) 
  from dual
  connect by level <= 100
)
select x.num as perfect, listagg(n.num, ',') within group (order by n.num) factors
from numbers n 
cross join numbers x 
where mod(x.num, n.num) = 0 and x.num > 1 and n.num <= x.max_factor
group by x.num
having sum(n.num) = x.num
/