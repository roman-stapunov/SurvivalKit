create table if not exists "__EFMigrationsHistory"
(
	"MigrationId" varchar(150) not null
		constraint "PK___EFMigrationsHistory"
			primary key,
	"ProductVersion" varchar(32) not null
);

alter table "__EFMigrationsHistory" owner to xgb_survivaln;

create table if not exists "AspNetRoles"
(
	"Id" text not null
		constraint "PK_AspNetRoles"
			primary key,
	"Name" text,
	"NormalizedName" text,
	"ConcurrencyStamp" text
);

alter table "AspNetRoles" owner to xgb_survivaln;

create unique index if not exists "RoleNameIndex"
	on "AspNetRoles" ("NormalizedName");

create table if not exists "AspNetUsers"
(
	"Id" text not null
		constraint "PK_AspNetUsers"
			primary key,
	"UserName" text,
	"NormalizedUserName" text,
	"Email" text,
	"NormalizedEmail" text,
	"PasswordHash" text,
	"SecurityStamp" text,
	"ConcurrencyStamp" text,
	"PhoneNumber" text,
	"LockoutEnd" text,
	"AccessFailedCount" integer not null,
	"EmailConfirmed" boolean not null,
	"PhoneNumberConfirmed" boolean not null,
	"TwoFactorEnabled" boolean not null,
	"LockoutEnabled" boolean not null
);

alter table "AspNetUsers" owner to xgb_survivaln;

create index if not exists "EmailIndex"
	on "AspNetUsers" ("NormalizedEmail");

create unique index if not exists "UserNameIndex"
	on "AspNetUsers" ("NormalizedUserName");

create table if not exists "AspNetRoleClaims"
(
	"Id" integer not null
		constraint "PK_AspNetRoleClaims"
			primary key,
	"RoleId" text not null
		constraint "FK_AspNetRoleClaims_AspNetRoles_RoleId"
			references "AspNetRoles"
				on delete cascade,
	"ClaimType" text,
	"ClaimValue" text
);

alter table "AspNetRoleClaims" owner to xgb_survivaln;

create index if not exists "IX_AspNetRoleClaims_RoleId"
	on "AspNetRoleClaims" ("RoleId");

create table if not exists "AspNetUserClaims"
(
	"Id" integer not null
		constraint "PK_AspNetUserClaims"
			primary key,
	"UserId" text not null
		constraint "FK_AspNetUserClaims_AspNetUsers_UserId"
			references "AspNetUsers"
				on delete cascade,
	"ClaimType" text,
	"ClaimValue" text
);

alter table "AspNetUserClaims" owner to xgb_survivaln;

create index if not exists "IX_AspNetUserClaims_UserId"
	on "AspNetUserClaims" ("UserId");

create table if not exists "AspNetUserLogins"
(
	"LoginProvider" text not null,
	"ProviderKey" text not null,
	"ProviderDisplayName" text,
	"UserId" text not null
		constraint "FK_AspNetUserLogins_AspNetUsers_UserId"
			references "AspNetUsers"
				on delete cascade,
	constraint "PK_AspNetUserLogins"
		primary key ("LoginProvider", "ProviderKey")
);

alter table "AspNetUserLogins" owner to xgb_survivaln;

create index if not exists "IX_AspNetUserLogins_UserId"
	on "AspNetUserLogins" ("UserId");

create table if not exists "AspNetUserRoles"
(
	"UserId" text not null
		constraint "FK_AspNetUserRoles_AspNetUsers_UserId"
			references "AspNetUsers"
				on delete cascade,
	"RoleId" text not null
		constraint "FK_AspNetUserRoles_AspNetRoles_RoleId"
			references "AspNetRoles"
				on delete cascade,
	constraint "PK_AspNetUserRoles"
		primary key ("UserId", "RoleId")
);

alter table "AspNetUserRoles" owner to xgb_survivaln;

create index if not exists "IX_AspNetUserRoles_RoleId"
	on "AspNetUserRoles" ("RoleId");

create table if not exists "AspNetUserTokens"
(
	"UserId" text not null
		constraint "FK_AspNetUserTokens_AspNetUsers_UserId"
			references "AspNetUsers"
				on delete cascade,
	"LoginProvider" text not null,
	"Name" text not null,
	"Value" text,
	constraint "PK_AspNetUserTokens"
		primary key ("UserId", "LoginProvider", "Name")
);

alter table "AspNetUserTokens" owner to xgb_survivaln;

create table if not exists "User"
(
	name varchar(255) not null,
	email varchar(255) not null,
	phonenumber varchar(255),
	password varchar(255) not null,
	roleid integer not null,
	username varchar(255) not null,
	id serial not null
		constraint user_pk
			primary key
);

alter table "User" owner to xgb_survivaln;

create unique index if not exists user_username_uindex
	on "User" (username);

create table if not exists role
(
	id serial not null
		constraint role_pk
			primary key,
	role varchar(255) not null
);

alter table role owner to xgb_survivaln;

create unique index if not exists role_id_uindex
	on role (id);

create table if not exists service
(
	id serial not null
		constraint service_pk
			primary key,
	serviceproviderid integer not null,
	servicename varchar(255) not null,
	description text,
	imageurl varchar(255),
	price double precision not null,
	rewardcoins integer not null
);

alter table service owner to xgb_survivaln;

