# Configurer le projet pour se connecter à la base de données

- Vous pouvez utiliser l'outil inclus dans Intellij IDEA pour définir l'url et l'accès (Onglet Database)

# Définir les fonctionnalités suivantes :

- Implémenter l'affichage d'une liste de tutoriels
  JE VEUX visualiser la liste des tutoriels enregistrés dans ma base de données

- Implémenter l'affichage d'un tutoriel
  JE VEUX pouvoir cliquer sur un tutoriel dans la liste, et visualiser le détail de celui-ci dans une page différente

- Implémenter l'ajout d'un tutoriel via un formulaire thymeleaf
  JE VEUX pouvoir ajouter un tutoriel depuis un formulaire d'ajout, sur une page différente

- Implémenter la suppression d'un tutorial via un bouton de suppression
  JE VEUX pouvoir supprimer un tutoriel depuis la liste des tutoriels

# Définitions :

## Un tutoriel est constitué de :

- Un titre (title)
- Une description (description)
- Un contenu (content)
- Une date de création (createAt) (date, heures, minutes)
- Un auteur (author) de type User
- Une categorie (category)
- Une liste de commentaires (comments) de type Comment

## Un User est constitué de :

- Un email (email)
- Un prénom (firstName)
- Un nom (lastName)
- Une liste de roles (Role) de type roles

## Un role est constitué de :

- d'un nom de role (roleName) de type RoleEnum

## Un roleEnum est constitué de :

- ROLE_USER
- ROLE_ADMIN
