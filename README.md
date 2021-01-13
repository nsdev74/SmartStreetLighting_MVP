# Smart Street Lighting MVP
Minimum Value Product for 2020-2021 Master INTENSE Université Côte d'Azur

Dans le cadre d'innovation pour les Smart cities, ce projet consiste à afficher un tableau de bord permettant de consulter l'état des different luminaires placer autour de la ville afin de faciliter la localisation des postes en panne et même de signaler quel est le composant endommager pour accélérer les étapes de diagnostic et réparation.


![Demo](https://s2.gifyu.com/images/Demo593a50d786ef8422.gif)



Installation:

-Le client dashboard n'a pas besoin de modification pour usage avec mockend, pour usage avec le backend inclus, suffit de changer l'adresse de la requête GET avec "http://localhost:8080/Postes", avec le port étant celui de l'application springboot.

-Pour usage complet, utiliser le backend inclut, modifier l'adresse du frontend, le simulateur est configuré pour l'adresse "http://localhost:8080/Postes" et n'aura donc pas besoin de modification **si vous utilisez le même port**.

-Pas besoin de compiler le simulateur, le fichier executable est disponible sur [ce lien](SimulationClient/SimulateurDeCapteurs/bin/Debug/SimulateurDeCapteurs.exe).





Technologies utilisees:


-Leaflet.js
-Spring
-Windows Form Application C# .Net
-REST

A ameliorer:


-Rafraichir la carte dynamiquement avec AJAX.

-Améliorer l'affichage et faciliter le repérage des luminaires non fonctionnels selon la demo suivante:

![UpdateDemo](https://s2.gifyu.com/images/SSLmvptry.gif)
