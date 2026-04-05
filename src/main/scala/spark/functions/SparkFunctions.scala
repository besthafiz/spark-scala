package spark.functions

/*
1. Column / General Functions
col() – Reference a column
lit() – Create a literal column
expr() – SQL expression
when() / otherwise() – Conditional logic
coalesce() – First non-null value
broadcast() – Broadcast join hint
2. String Functions
concat()
concat_ws()
length()
lower() / upper()
trim() / ltrim() / rtrim()
substring()
regexp_replace()
regexp_extract()
split()
instr()
3. Numeric Functions
abs()
round() / bround()
ceil() / floor()
sqrt()
pow()
exp() / log()
4. Date & Time Functions
current_date()
current_timestamp()
date_add() / date_sub()
datediff()
add_months()
months_between()
year() / month() / dayofmonth()
hour() / minute() / second()
to_date() / to_timestamp()
date_format()
5. Aggregation Functions

(Used with groupBy)

count()
sum()
avg()
min()
max()
collect_list()
collect_set()
first() / last()
6. Window Functions

(Used with Window)

row_number()
rank()
dense_rank()
lag()
lead()
ntile()
7. Array Functions
array()
array_contains()
explode()
posexplode()
size()
sort_array()
array_union()
array_intersect()
8. Map Functions
create_map()
map_keys()
map_values()
map_from_arrays()
9. JSON Functions
to_json()
from_json()
get_json_object()
json_tuple()
10. Null Handling
isnull()
isnotnull()
na.fill() (DataFrame API)
na.drop()
11. Sorting & Ranking Helpers
asc() / desc()
 */
object SparkFunctions {

}
