create table article (	a_id serial primary key,
	URL varchar(256) not null default '',
	saved_file_name varchar(256) not null default '',
	date_written date not null default '1970-01-20',
	date_saved date not null default '1970-01-20',
	author_1 varchar(256) not null default '',
	author_2 varchar(256) not null default '',
	author_3 varchar(256) not null default '',
	case_name varchar(256) not null default '',
	is_printed boolean not null default false,
	case_id integer not null default '0')


ALTER TABLE article ADD CONSTRAINT unique_url UNIQUE (URL)
