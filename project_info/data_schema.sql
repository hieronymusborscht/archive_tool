create table article (

a_id serial primary key,
URL varchar(256) not null default '',
saved_file_name varchar(256) not null default '',
visitor_id date not null default '1970-01-20',
author_1 varchar(256) not null default '',
author_2 varchar(256) not null default '',
author_3 varchar(256) not null default '',
case_name varchar(256) not null default '',
case_id integer not null default '0')
