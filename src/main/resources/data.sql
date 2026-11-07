-- Inserimento scarpe
-- Nike Air 270
INSERT INTO scarpe (nome, categoria, prezzo, descrizione, immagine_cover, nuovi_arrivi, best_seller)
VALUES ("Nike Air Max 270", "Lifestyle", 129.99, "Le Nike Air Max 270 offrono un comfort incredibile grazie all'ammortizzazione Air Max. Ideali per l'uso quotidiano e l'attività sportiva.", "images/Shoes/air-max-270/black/air-max-270-black-1.png", true, 5);

-- Per le taglie (essendo @ElementCollection)
INSERT INTO scarpa_taglie (scarpa_id, taglie_disponibili) VALUES (1, 38), (1, 39), (1, 40), (1, 41), (1, 42), (1, 43), (1, 44), (1, 45);

-- Per i colori disponibili
INSERT INTO scarpa_colori (scarpa_id, colori_disponibili) VALUES (1, "nero"), (1, "bianco");

INSERT INTO immagini_colore (scarpa_id, colore) VALUES (1, "nero"), (1, "bianco");

-- Per le immagini delle scarpe in base al colore
INSERT INTO url_images (img_colore_id, url)
VALUES (1, "images/Shoes/air-max-270/black/air-max-270-black-1.png"),
       (1, "images/Shoes/air-max-270/black/air-max-270-black-3.avif"),
       (1, "images/Shoes/air-max-270/black/air-max-270-black-4.avif"),
       (1, "images/Shoes/air-max-270/black/air-max-270-black-2.avif"),
       (1, "images/Shoes/air-max-270/black/air-max-270-black-5.avif"),
       (1, "images/Shoes/air-max-270/black/air-max-270-black-6.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-1.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-2.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-3.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-4.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-5.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-6.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-7.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-8.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-9.avif");
----

-- Nike React Infinity Run Flyknit
INSERT INTO scarpe (nome, categoria, prezzo, descrizione, immagine_cover, nuovi_arrivi, best_seller)
VALUES ("Nike React Infinity Run Flyknit", "Running", 159.99, "Le Nike React Infinity Run Flyknit sono progettate per la massima ammortizzazione e stabilità durante la corsa. Con tomaia in Flyknit per un comfort superiore.", "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-1.png", false, 1);

-- Taglie
INSERT INTO scarpa_taglie (scarpa_id, taglie_disponibili) VALUES (2, 38), (2, 39), (2, 40), (2, 41), (2, 42), (2, 43), (2, 44), (2, 45);

-- Colori
INSERT INTO scarpa_colori (scarpa_id, colori_disponibili) VALUES (2, "nero-giallo"), (2, "grigio"), (2, "rosso");

-- Immagini colore
INSERT INTO immagini_colore (scarpa_id, colore) VALUES (2, "nero-giallo"), (2, "grigio"), (2, "rosso");

INSERT INTO url_images (img_colore_id, url)
VALUES (3, "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-1.png"),
       (3, "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-2.png"),
       (3, "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-3.png"),
       (3, "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-4.png"),
       (3, "images/Shoes/react-infinity-run-flyknit/black-yellow/react-infinity-run-flyknit-black-yellow-5.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-1.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-2.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-3.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-4.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-5.png"),
       (4, "images/Shoes/react-infinity-run-flyknit/grey/react-infinity-run-flyknit-grey-6.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-1.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-2.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-3.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-4.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-5.png"),
       (5, "images/Shoes/react-infinity-run-flyknit/red/react-infinity-run-flyknit-red-6.png");
----

-- Air Force 1
INSERT INTO scarpe (nome, categoria, prezzo, descrizione, immagine_cover, nuovi_arrivi, best_seller)
VALUES ("Nike Air Force 1", "Lifestyle", 109.99, "Le Nike Air Force 1 sono un''icona streetwear, caratterizzate da un design classico e un comfort eccezionale.", "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-1.png", false, 5);

-- Per le taglie (essendo @ElementCollection)
INSERT INTO scarpa_taglie (scarpa_id, taglie_disponibili) VALUES (3, 38), (3, 39), (3, 40), (3, 41), (3, 42), (3, 43), (3, 44), (3, 45);

-- Per i colori disponibili
INSERT INTO scarpa_colori (scarpa_id, colori_disponibili) VALUES (3, "bianco"), (3, "nero");

INSERT INTO immagini_colore (scarpa_id, colore) VALUES (3, "bianco"), (3, "nero");

-- Per le immagini delle scarpe in base al colore
INSERT INTO url_images (img_colore_id, url)
VALUES (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-1.png"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-2.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-3.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-4.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-5.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-6.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-7.avif"),
       (6, "images/Shoes/air-force-1/low/white/air-force-1-low-07-triple-white-8.avif"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-1.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-2.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-3.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-4.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-5.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-6.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-7.png"),
       (7, "images/Shoes/air-force-1/low/black/air-force-1-low-07-triple-black-8.png");

-----