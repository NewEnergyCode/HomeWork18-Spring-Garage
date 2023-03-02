CREATE TABLE "Car_v4"
(
    carid  INTEGER,
    brand  text,
    model  text,
    userid INTEGER
);

INSERT INTO "Car_v4" (carid, brand, model, userid)
SELECT carid, brand, model, userid
FROM "Cars";


