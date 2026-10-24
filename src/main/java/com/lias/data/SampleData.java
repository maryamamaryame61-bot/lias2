package com.lias.data;

import com.lias.model.*;

public class SampleData {

    private static boolean initialized = false;

    public static synchronized void init() {
        if (initialized) return;

        // 1. Initialiser la BD SQLite (tables)
        try {
            InitialisationBD.creerTables();
        } catch (Exception ignored) {}

        // 2. Équipes de recherche
        if (BaseEquipes.getEquipes().isEmpty()) {
            BaseEquipes.ajouter(new Equipe(
                "Science des Données & Intelligence Artificielle (SDIA)",
                "Pr. Mohamed Alami",
                "Machine Learning, Deep Learning, Traitement du Langage Naturel (NLP)"
            ));
            BaseEquipes.ajouter(new Equipe(
                "Génie Logiciel & Systèmes d'Information Avancés (GLSIA)",
                "Dr. Fatima Zahra Bennani",
                "Architectures Cloud, Microservices, DevOps, Systèmes Décisionnels"
            ));
            BaseEquipes.ajouter(new Equipe(
                "Systèmes Embarqués, IoT & Sécurité (SEIS)",
                "Pr. Hassan El Idrissi",
                "Internet des Objets, Cybersécurité, Cryptographie Appliquée"
            ));
        }

        // 3. Membres du laboratoire (différents statuts demandés dans le cahier des charges)
        if (BaseMembres.getMembres().isEmpty()) {
            BaseMembres.ajouter(new Membre("El Idrissi", "Hassan", "h.elidrissi@univ-fsbm.ma", "permanent"));
            BaseMembres.ajouter(new Membre("Alami", "Mohamed", "m.alami@univ-fsbm.ma", "permanent"));
            BaseMembres.ajouter(new Membre("Bennani", "Fatima Zahra", "fz.bennani@univ-fsbm.ma", "permanent"));
            BaseMembres.ajouter(new Membre("Tazi", "Youssef", "y.tazi@inra.ma", "associe"));
            BaseMembres.ajouter(new Membre("Mansouri", "Amina", "a.mansouri@etu.univ-fsbm.ma", "doctorant"));
            BaseMembres.ajouter(new Membre("Chraibi", "Mehdi", "m.chraibi@etu.univ-fsbm.ma", "doctorant"));
            BaseMembres.ajouter(new Membre("Berrada", "Abdellah", "a.berrada@univ-fsbm.ma", "retraite"));
        }

        // 4. Publications scientifiques
        if (BasePublications.getPublications().isEmpty()) {
            BasePublications.ajouter(new Publication(
                "Deep Learning Architectures for Moroccan Dialect Sentiment Analysis",
                "Amina Mansouri, Pr. Mohamed Alami",
                2025,
                "SDIA"
            ));
            BasePublications.ajouter(new Publication(
                "Scalable Stream Processing in Modern Big Data Architectures",
                "Pr. Mohamed Alami",
                2024,
                "SDIA"
            ));
            BasePublications.ajouter(new Publication(
                "Resilient Microservices Orchestration in Multi-Cloud Environments",
                "Dr. Fatima Zahra Bennani",
                2025,
                "GLSIA"
            ));
            BasePublications.ajouter(new Publication(
                "Edge-AI & IoT Framework for Predictive Maintenance in Smart Agriculture",
                "Mehdi Chraibi, Pr. Hassan El Idrissi",
                2026,
                "SEIS"
            ));
            BasePublications.ajouter(new Publication(
                "Zero-Trust Security Models in Distributed Data Lakes",
                "Pr. Hassan El Idrissi, Dr. Youssef Tazi",
                2026,
                "SEIS"
            ));
        }

        // 5. Événements scientifiques
        if (BaseEvenements.getEvenements().isEmpty()) {
            BaseEvenements.ajouter(new Evenement(
                "Journée Scientifique Big Data & IA 2026",
                "Séminaire",
                "2026-04-15"
            ));
            BaseEvenements.ajouter(new Evenement(
                "Workshop International sur le Cloud & DevOps",
                "Workshop",
                "2026-05-20"
            ));
            BaseEvenements.ajouter(new Evenement(
                "Conférence Annuelle du Laboratoire LIAS (JLL-26)",
                "Conférence",
                "2026-06-12"
            ));
        }

        // 6. Demandes d'adhésion (Cahier des charges Section 6)
        if (BaseDemandes.getDemandes().isEmpty()) {
            DemandeAdhesion d1 = new DemandeAdhesion(
                "Salma Ouazzani",
                "s.ouazzani@gmail.com",
                "Titulaire d'un Master DSBD (Ben M'Sik), je souhaite préparer une thèse de doctorat sur les modèles de fondation multimodaux pour la santé."
            );
            DemandeAdhesion d2 = new DemandeAdhesion(
                "Dr. Karim Ziani",
                "k.ziani@ensem.ac.ma",
                "Enseignant-chercheur à l'ENSEM, souhaite collaborer en tant que membre associé sur les thématiques Big Data & Systèmes Embarqués."
            );
            d2.accepter();
            BaseDemandes.ajouter(d1);
            BaseDemandes.ajouter(d2);
        }

        // 7. Matériels du laboratoire (Cahier des charges Section 14)
        if (BaseMateriels.getMateriels().isEmpty()) {
            BaseMateriels.ajouter(new Materiel(
                "Serveur GPU Haute Performance (NVIDIA A100 80GB)",
                2,
                "2026-01-15",
                "Équipe SDIA (IA & Deep Learning)"
            ));
            BaseMateriels.ajouter(new Materiel(
                "Stations de calcul Dell Precision i9 / 64 Go RAM",
                6,
                "2026-02-02",
                "Doctorants LIAS"
            ));
            BaseMateriels.ajouter(new Materiel(
                "Kits de prototypage IoT & Capteurs LoRaWAN",
                15,
                "2026-02-20",
                "Équipe SEIS"
            ));
        }

        // 8. Besoins en matériel (Cahier des charges Section 15)
        if (BaseBesoins.getBesoins().isEmpty()) {
            BaseBesoins.ajouter(new BesoinMateriel("Mehdi Chraibi", "Disque SSD Externe NVMe 2To", 1));
            BaseBesoins.ajouter(new BesoinMateriel("Amina Mansouri", "Casque Réalité Augmentée pour tests UI", 1));
            BaseBesoins.ajouter(new BesoinMateriel("Pr. Mohamed Alami", "Switch 10Gbps pour Rack Big Data", 2));
        }

        // 9. Documents (Cahier des charges Section 10)
        if (BaseDocuments.getDocuments().isEmpty()) {
            BaseDocuments.ajouter(new DocumentLabo("Règlement Intérieur du Laboratoire LIAS", "Administratif", "2025-09-01", "reglement_lias.pdf"));
            BaseDocuments.ajouter(new DocumentLabo("Formulaire de Mission et Financement Recherche", "Demande de financement", "2026-01-10", "mission_form.pdf"));
            BaseDocuments.ajouter(new DocumentLabo("Guide d'usage des Clusters de Calcul", "Procédure technique", "2026-02-01", "guide_cluster.pdf"));
        }

        // 10. Conventions & collaborations (Cahier des charges Section 16)
        if (BaseConventions.getConventions().isEmpty()) {
            BaseConventions.ajouter(new Convention(
                "Centre National de l'Énergie, des Sciences et des Techniques Nucléaires (CNESTEN)",
                "Recherche conjointe sur le traitement massif de données radiologiques",
                "2025-10-15",
                "3 ans"
            ));
            BaseConventions.ajouter(new Convention(
                "Technopark Casablanca & Startups IA",
                "Incubation de projets d'étudiants Master & valorisation scientifique",
                "2026-01-08",
                "2 ans"
            ));
        }

        // 11. Procès-Verbaux PV (Cahier des charges Section 17)
        if (BasePV.getPvs().isEmpty()) {
            BasePV.ajouter(new PV(
                "Réunion du Conseil de Laboratoire - Rentrée 2025-2026",
                "2025-09-25",
                "1. Répartition des budgets de recherche, 2. Accueil des nouveaux doctorants, 3. Préparation du colloque annuel"
            ));
            BasePV.ajouter(new PV(
                "Commission d'arbitrage matériel et équipement",
                "2026-01-20",
                "Attribution équitable des stations de travail et serveurs GPU"
            ));
        }

        // 12. Mandats de gouvernance (Cahier des charges Section 5.3)
        if (BaseMandats.getMandats().isEmpty()) {
            BaseMandats.ajouter(new Mandat(
                "Pr. Hassan El Idrissi",
                "Dr. Fatima Zahra Bennani",
                "Pr. Mohamed Alami",
                "2024-09-01",
                "2027-08-31"
            ));
        }

        // 13. Rôles et responsabilités (Cahier des charges Section 7)
        if (BaseRoles.getRoles().isEmpty()) {
            BaseRoles.ajouter(new RoleResponsabilite("Pr. Hassan El Idrissi", "Directeur du Laboratoire", "2024-09-01"));
            BaseRoles.ajouter(new RoleResponsabilite("Dr. Fatima Zahra Bennani", "Vice-Directrice", "2024-09-01"));
            BaseRoles.ajouter(new RoleResponsabilite("Pr. Mohamed Alami", "Chef d'équipe SDIA", "2024-09-01"));
        }

        // 14. Affiliations temporelles (Cahier des charges Section 4.3)
        if (BaseAffiliations.getAffiliations().isEmpty()) {
            BaseAffiliations.ajouter(new Affiliation("Pr. Mohamed Alami", "SDIA", "2020-09-01", "En cours"));
            BaseAffiliations.ajouter(new Affiliation("Dr. Fatima Zahra Bennani", "GLSIA", "2022-01-15", "En cours"));
            BaseAffiliations.ajouter(new Affiliation("Dr. Youssef Tazi", "SDIA (Associé)", "2023-09-01", "2025-08-31"));
        }

        // 15. Projets de recherche
        if (BaseProjets.getProjets().isEmpty()) {
            BaseProjets.ajouter(new Projet("Plateforme Big Data Santé Maroc", "Pr. Mohamed Alami", "350 000 MAD"));
            BaseProjets.ajouter(new Projet("Smart Campus IoT & Éco-Énergie", "Pr. Hassan El Idrissi", "220 000 MAD"));
        }

        // 16. Calendrier
        if (BaseCalendrier.getEvents().isEmpty()) {
            BaseCalendrier.ajouter(new CalendrierEvent("Soutenance Thèse IA", "2026-04-28", "Amphithéâtre 1, FSBM"));
            BaseCalendrier.ajouter(new CalendrierEvent("Séminaire Interne Équipe SDIA", "2026-05-08", "Salle Réunion LIAS"));
        }

        // 17. Notifications (Cahier des charges Section 19)
        if (BaseNotifications.getNotifications().isEmpty()) {
            BaseNotifications.ajouter(new Notification("Nouvelle candidature", "Salma Ouazzani a déposé une demande d'adhésion.", "2026-03-10"));
            BaseNotifications.ajouter(new Notification("Arrivage Matériel", "2 Serveurs NVIDIA A100 ont été réceptionnés.", "2026-01-15"));
        }

        // 18. Rapports annuels (Cahier des charges Section 11)
        if (BaseRapports.getRapports().isEmpty()) {
            BaseRapports.ajouter(new RapportAnnuel(2025, "Bilan scientifique 2024-2025 : 18 publications, 3 soutenances de thèses, 2 conférences internationales organisées. Budget consommé conforme aux prévisions."));
        }

        initialized = true;
    }
}
