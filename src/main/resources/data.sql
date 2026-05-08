-- START TRANSACTION
-- Inserimento scarpe
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
       (2, "images/Shoes/air-max-270/white/air-max-270-white-4.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-2.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-3.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-1.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-5.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-6.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-7.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-8.avif"),
       (2, "images/Shoes/air-max-270/white/air-max-270-white-9.avif");

-- COMMIT; 