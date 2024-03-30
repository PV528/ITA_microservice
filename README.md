# Sistem za upravljanje avtomobilov
## Opis Projekta
Projekt predstavlja sistem za upravljanje avtomobilov. Zgrajen je s programskim jezikom Java s pomočjo Spring Boota. Omogoča RESTful API-je za upravljanje podatkov o avtomobilih. Omogoča pridobivanje, vstavljanje, posodabljanje in brisanje avtomobilov iz sistema. Za shranjevanje podatkov se uporablja MongoDB baza. Sistem je zasnovan kot mikrostoritev, kar omogoča njegovo integracijo v večji sistem upravljanja vozil. API končne točke so dokumentirane z uporabo OpenAPI (Swagger) standarda za olajšano uporabo.
## Lastnosti
+ CRUD operacije (pridobivanje, ustvarjanje, posodabljanje, brisanje) za podatke o avtomobilih.
+ Povezava in trajno shranjevanje podatkov v MongoDB podatkovno bazo.
+ Izpisovanje dnevnikov delovanja (logov) za lažji nadzor delovanja.
+ Izvedba Unit testov za testiranje pravilnega delovanja končnih točk in repozitorija
+ kontejnerizacija za Docker (Dockerfile in docker-compose).
+ Dodan Open API (Swagger). 
