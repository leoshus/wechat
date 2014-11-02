DROP TABLE IF EXISTS wechat_user;
create table wechat_user(
   user_id varchar(40) not null primary key,
   openid varchar(30) not null,
   createtime varchar(30)
) default charset=utf8;

DROP TABLE IF EXISTS WECHAT_BIND_USER;
create table wechat_bind_user(
	id varchar(40) not null primary key,
	openId varchar(30) not null,
	wechatName varchar(20) default null,
	wechatCardType varchar(2) default null,
	wechatCardNo varchar(30) default null,
	wechatSex varchar(2) default null,
	wechatBirth varchar(10) default null,
	wechatPhone varchar(15) default null,
	createDate varchar(20) default null,
	updateDate varchar(20) default null
)default charset=utf8;

drop table if exists wechat_access_token;
create table wechat_access_token(
	id varchar(40) not null primary key,
	service_provider varchar(20) not null,
	token varchar(30) not null,
	create_time varchar(20) default null
)default charset=utf8;