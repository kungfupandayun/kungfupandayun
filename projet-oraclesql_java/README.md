1)Pour Regénere l'auto incrementation de id_parcours et id_trajet
DROP SEQUENCE indParcours;

CREATE SEQUENCE indParcours
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 9999999;

DROP SEQUENCE indIDTrajet;

CREATE SEQUENCE indIDTrajet
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 9999999;

2)Le main du projet est dans la class CoVoiturage