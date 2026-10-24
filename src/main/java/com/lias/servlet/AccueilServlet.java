package com.lias.servlet;

import java.io.IOException;

import com.lias.data.*;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Accueil & Navigation", "accueil"));

        html.append("""
            <div class="mb-4">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <h2 class="fw-bold text-dark mb-1">Portail de Gestion Interne &bull; LIAS</h2>
                        <p class="text-secondary small mb-0">Faculté des Sciences Ben M'Sik &bull; Accès centralisé aux 21 modules du laboratoire</p>
                    </div>
                    <a href="dashboard" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-speedometer2 me-1"></i> Voir le Tableau de Bord
                    </a>
                </div>
            </div>

            <!-- DOMAINE 1: RESSOURCES HUMAINES & GOUVERNANCE -->
            <div class="mb-5">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-people-fill me-2"></i> 1. Ressources Humaines, Mandats & Gouvernance</h5>
                <div class="row g-3">
                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-people fs-4 text-primary"></i>
                                <h6 class="fw-bold text-dark mb-0">Gestion des Membres</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Permanents, associés, doctorants, retraités et anciens. Profils et données temporelles.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="lister" class="btn btn-sm btn-outline-primary flex-fill"><i class="bi bi-list"></i> Lister</a>
                                <a href="ajouter" class="btn btn-sm btn-primary-lias flex-fill"><i class="bi bi-plus-lg"></i> Ajouter</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-person-check fs-4 text-warning"></i>
                                <h6 class="fw-bold text-dark mb-0">Demandes d'Adhésion</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Candidatures en ligne de doctorants et chercheurs. Validation réservée au Directeur en mandat.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerDemandes" class="btn btn-sm btn-outline-warning text-dark flex-fill"><i class="bi bi-card-checklist"></i> Examiner</a>
                                <a href="ajouterDemande" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-plus"></i> Déposer</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-award fs-4 text-success"></i>
                                <h6 class="fw-bold text-dark mb-0">Mandats de Gouvernance</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Directeur en mandat actif, Vice-directeur, Chefs d'équipe. Périodes et historique conservé.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerMandats" class="btn btn-sm btn-outline-success flex-fill"><i class="bi bi-list"></i> Mandats</a>
                                <a href="ajouterMandat" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-plus"></i> Nouveau</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-diagram-3 fs-4 text-info"></i>
                                <h6 class="fw-bold text-dark mb-0">Équipes de Recherche</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Structure scientifique du LIAS : axes, responsables et rattachement des chercheurs.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerEquipes" class="btn btn-sm btn-outline-info flex-fill"><i class="bi bi-diagram-3"></i> Équipes</a>
                                <a href="ajouterEquipe" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-plus"></i> Créer</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-person-badge fs-4 text-secondary"></i>
                                <h6 class="fw-bold text-dark mb-0">Rôles & Responsabilités</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Séparation stricte entre statut et responsabilité. Évolution sans perte de données.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerRoles" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-list"></i> Rôles</a>
                                <a href="ajouterRole" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-plus"></i> Affecter</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-clock-history fs-4 text-danger"></i>
                                <h6 class="fw-bold text-dark mb-0">Historique des Affiliations</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Traçabilité des arrivées, départs, et retours des chercheurs au sein du laboratoire.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerAffiliations" class="btn btn-sm btn-outline-danger flex-fill"><i class="bi bi-clock"></i> Historique</a>
                                <a href="ajouterAffiliation" class="btn btn-sm btn-outline-secondary flex-fill"><i class="bi bi-plus"></i> Ajouter</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- DOMAINE 2: PRODUCTION SCIENTIFIQUE -->
            <div class="mb-5">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-journal-text me-2"></i> 2. Production Scientifique & Activités</h5>
                <div class="row g-3">
                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-journal-check fs-4 text-primary"></i>
                                <h6 class="fw-bold text-dark mb-0">Publications</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Indexation des articles, revues et communications. Filtre par auteur, année et équipe.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerPublications" class="btn btn-sm btn-outline-primary flex-fill">Consulter</a>
                                <a href="ajouterPublication" class="btn btn-sm btn-primary-lias flex-fill">Ajouter</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-calendar-event fs-4 text-warning"></i>
                                <h6 class="fw-bold text-dark mb-0">Événements Scientifiques</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Organisation de conférences, séminaires doctoraux, workshops et colloques.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerEvenements" class="btn btn-sm btn-outline-warning text-dark flex-fill">Événements</a>
                                <a href="ajouterEvenement" class="btn btn-sm btn-outline-secondary flex-fill">Planifier</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-kanban fs-4 text-info"></i>
                                <h6 class="fw-bold text-dark mb-0">Projets de Recherche</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Suivi des projets nationaux & internationaux (budgets, responsables, livrables).</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerProjets" class="btn btn-sm btn-outline-info flex-fill">Projets</a>
                                <a href="ajouterProjet" class="btn btn-sm btn-outline-secondary flex-fill">Ajouter</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-calendar3 fs-4 text-success"></i>
                                <h6 class="fw-bold text-dark mb-0">Calendrier Temporel</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Visualisation calendaire interactive des échéances et activités du laboratoire.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerCalendrier" class="btn btn-sm btn-outline-success flex-fill">Voir Calendrier</a>
                                <a href="ajouterCalendrier" class="btn btn-sm btn-outline-secondary flex-fill">Ajouter date</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-8 col-sm-12">
                        <div class="custom-card p-3 h-100 d-flex flex-column bg-light">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-file-earmark-bar-graph fs-4 text-danger"></i>
                                <h6 class="fw-bold text-dark mb-0">Rapport d'Activité Annuel (Cahier des Charges &sect; 11)</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Génération annuelle automatique synthétisant événements, publications et moyens. Condition indispensable pour l'obtention du budget annuel.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerRapports" class="btn btn-sm btn-danger flex-fill"><i class="bi bi-printer me-1"></i> Générer / Exporter le Rapport Annuel</a>
                                <a href="ajouterRapport" class="btn btn-sm btn-outline-secondary flex-fill">Nouvelle édition</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- DOMAINE 3: LOGISTIQUE, DOCUMENTS, CONVENTIONS & MATÉRIEL -->
            <div class="mb-5">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-box-seam me-2"></i> 3. Moyens, Matériels, Documents &amp; Partenariats</h5>
                <div class="row g-3">
                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-cpu fs-4 text-primary"></i>
                                <h6 class="fw-bold text-dark mb-0">Gestion du Matériel</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Suivi des arrivages et distribution équitable. Traçabilité ("qui a reçu, qui n'a pas reçu").</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerMateriels" class="btn btn-sm btn-outline-primary flex-fill">Parc Matériel</a>
                                <a href="ajouterMateriel" class="btn btn-sm btn-primary-lias flex-fill">Arrivage</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-cart-plus fs-4 text-warning"></i>
                                <h6 class="fw-bold text-dark mb-0">Besoins en Matériel</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Expression des besoins par les membres, arbitrage et validation par la direction.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerBesoins" class="btn btn-sm btn-outline-warning text-dark flex-fill">Besoins</a>
                                <a href="ajouterBesoin" class="btn btn-sm btn-outline-secondary flex-fill">Demander</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-folder2-open fs-4 text-info"></i>
                                <h6 class="fw-bold text-dark mb-0">Archivage Documentaire</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Demandes de financement, programmes, attestations, rapports administratifs.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerDocuments" class="btn btn-sm btn-outline-info flex-fill">Documents</a>
                                <a href="ajouterDocument" class="btn btn-sm btn-outline-secondary flex-fill">Déposer</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-file-earmark-text fs-4 text-secondary"></i>
                                <h6 class="fw-bold text-dark mb-0">Procès-Verbaux (PV)</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Réunions du conseil de labo, ordre du jour, archivage des décisions prises.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerPV" class="btn btn-sm btn-outline-secondary flex-fill">Voir PVs</a>
                                <a href="ajouterPV" class="btn btn-sm btn-outline-secondary flex-fill">Créer PV</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-handshake fs-4 text-success"></i>
                                <h6 class="fw-bold text-dark mb-0">Conventions & Partenaires</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Partenariats institutionnels et industriels, durées et conventions signées.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="ListerConventionsServlet" class="btn btn-sm btn-outline-success flex-fill">Conventions</a>
                                <a href="ajouterConvention" class="btn btn-sm btn-outline-secondary flex-fill">Ajouter</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 col-sm-6">
                        <div class="custom-card p-3 h-100 d-flex flex-column">
                            <div class="d-flex align-items-center gap-2 mb-2">
                                <i class="bi bi-chat-dots fs-4 text-primary"></i>
                                <h6 class="fw-bold text-dark mb-0">Messagerie & Alertes</h6>
                            </div>
                            <p class="text-muted small flex-grow-1">Communication interne, échanges d'équipe et alertes instantanées du système.</p>
                            <div class="d-flex gap-2 pt-2 border-top">
                                <a href="listerMessages" class="btn btn-sm btn-outline-primary flex-fill">Messages</a>
                                <a href="listerNotifications" class="btn btn-sm btn-outline-secondary flex-fill">Alertes</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
