# JobApplication
 ** Back-end: JobApplication projet Spring Boot
  - http://localhost:8082/api/auth/signup : Créer  un utilisateur
  - role:comp => role company
  - role: user => role utilisateur
  - 
  
  
    {
      "email": "test@gmail.com",
      "password": "test12345678",
      "address":"test",
      "firstName":"test",
      "lastName":"test",
      "country":"test",
      "phone":"2133279808",
      "roles":["comp"]
   }
- http://localhost:8082/api/auth/signin: pour s'authentifier
-

   {
     "email": "test@gmail.com",
     "password": "test12345678"
    }
 - http://localhost:8082/JobOffers : list les annonces d’emploi
 - http://localhost:8082/JobOffers/idUtilisateur: Ajouter une annonce d’emploi
 - http://localhost:8082/JobOffers/idAnnonce: Details d'une annonce d’emploi
 - http://localhost:8082/CandidateApp/upload: Upload d'un fichier
 - http://localhost:8082/CandidateApp/download/idFichier: Téléchargement d'un fichier
 - http://localhost:8082/CandidateApp/idAnnonce/idFichier: postuler à une offre d’emploi
 - Database: MongoDB:
 -  mongodb+srv://job:job@jobsdb.820z67s.mongodb.net/DB?retryWrites=true&w=majority
 
