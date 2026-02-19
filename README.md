# cryptography-programming-1

# Mini-AES - Advanced Encryption Standard (Norme de Chiffrement Avancée)

## Table des Matières

1. [Présentation du projet](#présentation-du-projet)
2. [Document officiel](#document-officiel)
3. [Installation et Utilisation](#installation-et-utilisation)
   - [Prérequis](#prérequis)
   - [Installation du projet](#installation-du-projet)
4. [Fonctionnalités](#fonctionnalités)

---

## Présentation du projet  

Ce projet implémente une version simplifiée de l'algorithme de chiffrement **AES (Advanced Encryption Standard)** en **Java**.  
L'objectif est d'étudier les principes fondamentaux du chiffrement AES tout en proposant une version réduite pour une meilleure compréhension.  

---

## Document Officiel

Veuillez consulter le document officiel pour une explication détaillée du projet :

- **PDF** : [mini_aes.pdf](https://github.com/user-attachments/files/19072153/mini_aes.pdf)

- **Images** : 
  - ![mini_aes_page-1](https://github.com/user-attachments/assets/0f3ed48b-f50d-4d77-849d-2467aa4d6b4c)
  - ![mini_aes_page-2](https://github.com/user-attachments/assets/299063fc-09f1-47d4-853b-32a3056489ab)

---

## Installation et Utilisation  

### Prérequis  
Avant de commencer, assurez-vous d'avoir installé les éléments suivants :  
- **Java JDK 11+**  
- **Eclipse IDE** (ou tout autre IDE compatible Java)  
- **Git** installé sur votre machine  

### Installation du projet  
1. Clonez ce dépôt Git :  
   ```sh
   git clone https://github.com/callbak/Mini-AES.git  
   cd Mini-AES

### Importer le projet dans Eclipse :

1. Ouvrez Eclipse.
2. Allez dans **File > Import > Existing Projects into Workspace**.
3. Sélectionnez le dossier du projet **Mini-AES**.
4. Cliquez sur **Finish**.

### Compilation et exécution :

1. Ouvrez le fichier principal : `src/mini_aes/aes.java`.
2. Exécutez-le en cliquant sur **Run** (ou en utilisant le raccourci `Ctrl + F11`).

---

## Fonctionnalités
- Chiffrement et déchiffrement basés sur MINI-AES.
- Explication détaillée des étapes du chiffrement.
- Possibilité d'expérimenter avec différentes clés et blocs de données.




# RSA - Implémentation et Étude

## Table des Matières

1. [Présentation du projet](#présentation-du-projet)
2. [Objectifs](#objectifs)
3. [Installation et Utilisation](#installation-et-utilisation)
   - [Prérequis](#prérequis)
   - [Installation](#installation)
   - [Compilation et exécution](#compilation-et-exécution)
4. [Fonctionnalités](#fonctionnalités)
5. [Travaux Demandés](#travaux-demandés)
6. [Références](#références)

---

## Présentation du projet  

Ce projet consiste en l'implémentation de l'algorithme **RSA (Rivest-Shamir-Adleman)** et de ses variantes.
L'objectif est d'explorer les concepts fondamentaux du chiffrement RSA, y compris **RSA-CRT**, **RSA avec padding**, et les notions de **malleability** et **indistinguishability**.

---

## Document Officiel

Veuillez consulter le document officiel pour une explication détaillée du projet :

- **PDF** : [rsa.pdf](https://github.com/user-attachments/files/19072386/rsa.pdf)

- **Images** : 
  - ![rsa_page-1](https://github.com/user-attachments/assets/c9765875-2d52-4ae6-8634-6968419f954e)
  - ![rsa_page-2](https://github.com/user-attachments/assets/3098bde8-6c0e-47b2-bd69-c4071e035ed5)

---

## Installation et Utilisation  

### Prérequis  
Avant de commencer, assurez-vous d'avoir installé les éléments suivants :  
- **Java JDK 11+**  
- **Eclipse IDE** (ou tout autre IDE compatible Java)  
- **Git** installé sur votre machine  

### Installation  
1. Clonez ce dépôt Git :  
   ```sh
   git clone https://github.com/callbak/RSA.git  
   cd RSA

### Importer le projet dans Eclipse :

1. Ouvrez Eclipse.
2. Allez dans **File > Import > Existing Projects into Workspace**.
3. Sélectionnez le dossier du projet **RSA**.
4. Cliquez sur **Finish**.

### Compilation et exécution :

1. Ouvrez le fichier principal : `src/rsa/Rsa.java`.
2. Exécutez-le en cliquant sur **Run** (ou en utilisant le raccourci `Ctrl + F11`).

---

## Travaux Demandés

### 1. Génération des Clés RSA  
- Générer la clé publique `(e, N)` et la clé privée `(d, N)`, en choisissant `e` tel que `GCD(e, φ(N)) = 1`.

### 2. Exponentiation Modulaire  
- Implémenter l'algorithme **Square-and-Multiply** pour le calcul rapide de `a^b mod n`.

### 3. Chiffrement et Déchiffrement  
- Implémenter les fonctions de chiffrement et de déchiffrement en utilisant l'exponentiation modulaire.

### 4. Déchiffrement avec le Théorème des Restes Chinois (CRT)  
- Déchiffrement optimisé en résolvant : M ≡ C^d mod p M ≡ C^d mod q

### 5. Étude de la Malléabilité  
- Montrer comment un attaquant peut modifier un message chiffré sans connaître la clé privée.

### 6. Récupération des Facteurs Premiers  
- Implémenter un algorithme permettant de retrouver `p` et `q` si `φ(N)` est connu.

[!Jusqu'à maintenant, j'ai arrêté mon implémentation à la 6ème question]

### 7. Attaque sur un Message Chiffré avec Clés Différentes  
- Montrer comment un attaquant peut retrouver `M` si le même message est envoyé à **Alice** et **Bob**.

### 8. Génération Sécurisée de Clés  
- Utiliser les classes `KeyPair`, `KeyPairGenerator`, `PublicKey`, et `PrivateKey` pour générer une paire de clés de **1024 bits**.

### 9. RSA avec Padding  
- Implémenter le chiffrement et le déchiffrement avec le schéma de **padding PKCS#1**.

### 10. Signature Numérique  
- Générer et vérifier une **signature numérique** en utilisant deux méthodes :
- **Avec `MessageDigest` et `Cipher`**.
- **Avec la classe `Signature`**.


