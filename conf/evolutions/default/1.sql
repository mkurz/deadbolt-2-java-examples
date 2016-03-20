# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table authorised_user (
  id                            bigint not null,
  user_name                     varchar(255),
  constraint pk_authorised_user primary key (id)
);
create sequence authorised_user_seq;

create table authorised_user_security_role (
  authorised_user_id            bigint not null,
  security_role_id              bigint not null,
  constraint pk_authorised_user_security_role primary key (authorised_user_id,security_role_id)
);

create table authorised_user_user_permission (
  authorised_user_id            bigint not null,
  user_permission_id            bigint not null,
  constraint pk_authorised_user_user_permission primary key (authorised_user_id,user_permission_id)
);

create table security_role (
  id                            bigint not null,
  name                          varchar(255),
  constraint pk_security_role primary key (id)
);
create sequence security_role_seq;

create table user_permission (
  id                            bigint not null,
  permission_value              varchar(255),
  constraint pk_user_permission primary key (id)
);
create sequence user_permission_seq;

alter table authorised_user_security_role add constraint fk_authorised_user_security_role_authorised_user foreign key (authorised_user_id) references authorised_user (id) on delete restrict on update restrict;
create index ix_authorised_user_security_role_authorised_user on authorised_user_security_role (authorised_user_id);

alter table authorised_user_security_role add constraint fk_authorised_user_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_authorised_user_security_role_security_role on authorised_user_security_role (security_role_id);

alter table authorised_user_user_permission add constraint fk_authorised_user_user_permission_authorised_user foreign key (authorised_user_id) references authorised_user (id) on delete restrict on update restrict;
create index ix_authorised_user_user_permission_authorised_user on authorised_user_user_permission (authorised_user_id);

alter table authorised_user_user_permission add constraint fk_authorised_user_user_permission_user_permission foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;
create index ix_authorised_user_user_permission_user_permission on authorised_user_user_permission (user_permission_id);


# --- !Downs

alter table authorised_user_security_role drop constraint if exists fk_authorised_user_security_role_authorised_user;
drop index if exists ix_authorised_user_security_role_authorised_user;

alter table authorised_user_security_role drop constraint if exists fk_authorised_user_security_role_security_role;
drop index if exists ix_authorised_user_security_role_security_role;

alter table authorised_user_user_permission drop constraint if exists fk_authorised_user_user_permission_authorised_user;
drop index if exists ix_authorised_user_user_permission_authorised_user;

alter table authorised_user_user_permission drop constraint if exists fk_authorised_user_user_permission_user_permission;
drop index if exists ix_authorised_user_user_permission_user_permission;

drop table if exists authorised_user;
drop sequence if exists authorised_user_seq;

drop table if exists authorised_user_security_role;

drop table if exists authorised_user_user_permission;

drop table if exists security_role;
drop sequence if exists security_role_seq;

drop table if exists user_permission;
drop sequence if exists user_permission_seq;

