create table Cliente
(
    ClienteID int identity
        constraint Cliente_pk
            primary key nonclustered,
    Estado    nvarchar(11) not null
)
go

create table FabricanteAutomóvil
(
    FabricanteAutomóvilID     int identity
        constraint FabricanteAutomóvil_pk
            primary key nonclustered,
    NombreFabricanteAutomóvil nvarchar(50) not null
)
go

create table Automóvil
(
    AutomóvilID  int identity
        constraint Automóvil_pk
            primary key nonclustered,
    Modelo       nvarchar(50) not null,
    Año          varchar(4)   not null,
    Detalle      nvarchar(50),
    FabricanteID int          not null
        references FabricanteAutomóvil
            on delete cascade
)
go

create table FabricantePartes
(
    FabricanteParteID int identity
        constraint FabricantePartes_pk
            primary key nonclustered,
    NombreFabricante  nvarchar(50) not null
)
go

exec sp_addextendedproperty 'MS_Description', 'Fabricante de Partes', 'SCHEMA', 'dbo', 'TABLE', 'FabricantePartes'
go

create unique index FabricantePartes_NombreFabricante_uindex
    on FabricantePartes (NombreFabricante)
go

create table MarcaParte
(
    MarcaParteID int identity
        constraint MarcaParte_pk
            primary key nonclustered,
    Marca        nvarchar(50) not null
)
go

create unique index MarcaParte_Marca_uindex
    on MarcaParte (Marca)
go

create table Orden
(
    OrdenID    int identity
        constraint Orden_pk
            primary key nonclustered,
    MontoVenta decimal(20, 2) not null,
    MontoIVA   decimal(20, 2) not null,
    Fecha      date           not null,
    ClienteID  int            not null
        references Cliente
            on delete cascade
)
go

create table Organización
(
    Cedula    nvarchar(10) not null
        constraint Organización_pk
            primary key nonclustered,
    Nombre    nvarchar(50) not null,
    Direccion nvarchar(50),
    Ciudad    nvarchar(50),
    ClienteID int          not null
        references Cliente
            on delete cascade
)
go

create table Contacto
(
    Nombre       nvarchar(50) not null,
    Telefono     varchar(8)   not null,
    Cargo        nvarchar(50),
    Organizacion nvarchar(10) not null
        references Organización
            on delete cascade,
    primary key (Nombre, Telefono, Organizacion)
)
go

create unique index Organización_Nombre_uindex
    on Organización (Nombre)
go

create unique index Organización_ClienteID_uindex
    on Organización (ClienteID)
go

create table Parte
(
    ParteID      int identity
        constraint Parte_pk
            primary key nonclustered,
    NombreParte  nvarchar(50) not null,
    FabricanteID int
                              references FabricantePartes
                                  on delete set null,
    MarcaID      int
        references MarcaParte
            on delete cascade
)
go

exec sp_addextendedproperty 'MS_Description', 'Tabla que contiene información de las partes', 'SCHEMA', 'dbo', 'TABLE',
     'Parte'
go

create table Compone
(
    AutomovilID int not null
        references Automóvil,
    ParteID     int not null
        references Parte,
    primary key (AutomovilID, ParteID)
)
go

create table Detalle
(
    DetalleID int not null,
    Cantidad  int,
    OrdenID   int not null
        references Orden
            on delete cascade,
    ParteID   int
                  references Parte
                      on delete set null,
    primary key (DetalleID, OrdenID)
)
go

create table Persona
(
    Cedula    varchar(8)   not null
        constraint Persona_pk
            primary key nonclustered,
    Nombre    nvarchar(50) not null,
    Direccion nvarchar(50),
    Ciudad    nvarchar(50),
    ClienteID int          not null
        references Cliente
            on delete cascade
)
go

create unique index Persona_Cedula_uindex
    on Persona (Cedula)
go

create unique index Persona_ClienteID_uindex
    on Persona (ClienteID)
go

create table Proveedor
(
    Nombre             nvarchar(50) not null,
    ProveedorID        int identity
        constraint Proveedor_pk
            primary key nonclustered,
    [Direccion Exacta] nvarchar(50),
    Ciudad             nvarchar(50),
    Contacto           nvarchar(50)
)
go

exec sp_addextendedproperty 'MS_Description', 'Tabla de proveedores de partes', 'SCHEMA', 'dbo', 'TABLE', 'Proveedor'
go

create unique index Proveedor_Nombre_uindex
    on Proveedor (Nombre)
go

create table Provision
(
    ProveedorID  int            not null
        references Proveedor
            on delete cascade,
    ParteID      int            not null
        references Parte
            on delete cascade,
    PrecioCompra decimal(20, 2) not null,
    Ganancia     decimal(20, 2) not null,
    primary key (ProveedorID, ParteID)
)
go

create table TelefonoPersona
(
    Cliente  varchar(8) not null
        references Persona,
    Telefono varchar(8) not null,
    primary key (Cliente, Telefono)
)
go

create table TelefonoProveedor
(
    ProveedorID int         not null
        references Proveedor,
    Telefono    nvarchar(8) not null,
    primary key (ProveedorID, Telefono)
)
go

create table sysdiagrams
(
    name         sysname not null,
    principal_id int     not null,
    diagram_id   int identity
        primary key,
    version      int,
    definition   varbinary(max),
    constraint UK_principal_name
        unique (principal_id, name)
)
go

exec sp_addextendedproperty 'microsoft_database_tools_support', 1, 'SCHEMA', 'dbo', 'TABLE', 'sysdiagrams'
go


