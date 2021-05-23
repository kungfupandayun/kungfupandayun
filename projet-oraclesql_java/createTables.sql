CREATE TABLE Utilisateur (
	email VARCHAR(70) NOT NULL,
	nom VARCHAR(30) NOT NULL,
	prenom VARCHAR(30) NOT NULL,
	ville VARCHAR(50) NOT NULL,
	mdp VARCHAR(50) NOT NULL CHECK (LENGTH(mdp) >= 8),
	solde DECIMAL(10, 4)  NOT NULL CHECK (solde >= 0),
	PRIMARY KEY (email)
);

CREATE TABLE Vehicule (
	immatriculation VARCHAR(30) NOT NULL,
	marque VARCHAR(50) NOT NULL,
	modele VARCHAR(50) NOT NULL,
	energie_utilisee VARCHAR(30) NOT NULL CHECK (energie_utilisee IN ('Essence', 'Diesel', 'Electrique')),
	puiss_fiscale INT  NOT NULL CHECK (puiss_fiscale > 0),
	nb_place INT  NOT NULL CHECK (nb_place > 0),
	PRIMARY KEY (immatriculation)
);

CREATE TABLE PossedeVehicule (
	immatriculation VARCHAR(30) NOT NULL,
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY (immatriculation, email),
	CONSTRAINT fk_PossedeVehiculeImmatriculation
	    FOREIGN KEY (immatriculation)
	    REFERENCES Vehicule (immatriculation)
	    ON DELETE cascade,
	CONSTRAINT fk_PossedeVehiculeEmail
	    FOREIGN KEY (email)
	    REFERENCES Utilisateur (email)
	    ON DELETE cascade
);

CREATE TABLE Trajet (
	id_trajet NUMBER(10) PRIMARY KEY,
	date_trajet  VARCHAR(10) NOT NULL,
	heure_trajet  VARCHAR(10) NOT NULL,
	temps_trajet DATE NOT NULL,
	place_dispo_depart VARCHAR(50) NOT NULL
);

DROP SEQUENCE indIDTrajet;
CREATE SEQUENCE indIDTrajet
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 9999999;


CREATE TABLE ConducteurVehiculeTrajet (
	email VARCHAR(50) NOT NULL,
	immatriculation VARCHAR(30) NOT NULL,
	id_trajet NUMBER(10) NOT NULL,
 	PRIMARY KEY (email, immatriculation, id_trajet),
 	CONSTRAINT fk_ConducteurVehiculeTrajet_email
 	    FOREIGN KEY (email)
 	    REFERENCES Utilisateur (email)
 	    ON DELETE cascade,
    CONSTRAINT fk_ConducteurVehiculeTrajet_immatriculation
        FOREIGN KEY (immatriculation)
        REFERENCES Vehicule (immatriculation)
        ON DELETE cascade,
    CONSTRAINT fk_ConducteurVehiculeTrajet_id_trajet
        FOREIGN KEY (id_trajet)
        REFERENCES Trajet (id_trajet)
        ON DELETE cascade
);

CREATE TABLE Tronçon (
	id_tronçon NUMBER(10) NOT NULL,
	id_trajet NUMBER(10) NOT NULL,
	long_depart NUMBER(20, 6),
	lat_depart NUMBER(20, 6),
	ville_depart VARCHAR(50),
	long_arrivee NUMBER(20, 6),
	lat_arrivee NUMBER(20, 6),
	ville_arrivee VARCHAR(50),
	dist_parcourue NUMBER NOT NULL CHECK (dist_parcourue > 0),
	duree_tronçon NUMBER NOT NULL,
	etat_tronçon_conducteur  VARCHAR(30) NOT NULL CHECK (etat_tronçon_conducteur IN ('Depart', 'Arrivee', 'EnAttente')),
	PRIMARY KEY (id_tronçon, id_trajet),
	CONSTRAINT FK_TronçonTrajet 
	    FOREIGN KEY (id_trajet) 
	    REFERENCES Trajet(id_trajet)
	    ON DELETE cascade
);

CREATE TABLE Parcours (
	id_parcours NUMBER(10) NOT NULL,
	dateheure_départ DATE NOT NULL,
	heure_départ VARCHAR(20) NOT NULL,
	PRIMARY KEY (id_parcours)
);

DROP SEQUENCE indParcours;
CREATE SEQUENCE indParcours
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 9999999;


CREATE TABLE UtilisateurParcours (
	id_parcours NUMBER(10) NOT NULL,
	email VARCHAR(50) NOT NULL,
	PRIMARY KEY (id_parcours, email),
	CONSTRAINT fk_UtilisateurParcours_email
        FOREIGN KEY (email)
        REFERENCES Utilisateur (email)
        ON DELETE cascade,
    CONSTRAINT fk_UtilisateurParcours_id_parcours
        FOREIGN KEY (id_parcours)
        REFERENCES Parcours (id_parcours)
        ON DELETE cascade
);

CREATE TABLE EtatTronçonPassenger (
    etat_tronçon_passenger VARCHAR(30) PRIMARY KEY,
    CONSTRAINT EtatTronçonPassengerInvalide check (etat_tronçon_passenger IN  ('Montee', 'Descente', 'EnAttente')));
);

CREATE TABLE ParcoursTronçon (
	id_parcours NUMBER(10) NOT NULL,
	id_trajet NUMBER(10) NOT NULL,
	id_tronçon NUMBER(10) NOT NULL,
	etat_tronçon_passenger VARCHAR(30),
	PRIMARY KEY (id_parcours, id_trajet, id_tronçon),
	CONSTRAINT fk_ParcoursTronçon_parcours
        FOREIGN KEY (id_parcours)
        REFERENCES Parcours (id_parcours)
        ON DELETE cascade,
	CONSTRAINT fk_ParcoursTronçon_trajet_tronçon
        FOREIGN KEY (id_trajet, id_tronçon)
        REFERENCES Tronçon (id_trajet , id_tronçon)
        ON DELETE cascade,
    CONSTRAINT fk_ParcoursTronçon_trajet_etat
        FOREIGN KEY (etat_tronçon_passenger)
        REFERENCES EtatTronçonPassenger (etat_tronçon_passenger)
        ON DELETE SET NULL
);


CREATE TABLE TronçonTempsAtt (
	id_tronçon NUMBER(10) NOT NULL,
	id_trajet NUMBER(10) NOT NULL,
	temps_attente INT NOT NULL,
	PRIMARY KEY (id_tronçon, id_trajet),
	CONSTRAINT fk_TronçonTempsAtt_tronçon
        FOREIGN KEY (id_trajet, id_tronçon)
        REFERENCES Tronçon (id_trajet , id_tronçon)
        ON DELETE cascade
);

