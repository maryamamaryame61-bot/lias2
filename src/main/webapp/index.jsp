<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%@ page import="com.lias.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    // Initialiser les données de démonstration du laboratoire si nécessaire
    SampleData.init();

    int nbMembres = BaseMembres.getMembres().size();
    int nbPublications = BasePublications.getPublications().size();
    int nbEvenements = BaseEvenements.getEvenements().size();
    int nbEquipes = BaseEquipes.getEquipes().size();
    int nbProjets = BaseProjets.getProjets().size();

    ArrayList<Equipe> equipes = BaseEquipes.getEquipes();
    ArrayList<Publication> publications = BasePublications.getPublications();
    ArrayList<Evenement> evenements = BaseEvenements.getEvenements();

    request.setAttribute("pageTitle", "Accueil");
%>
<jsp:include page="includes/public_header.jsp" />

<!-- SECTION HERO BANNER -->
<section class="hero-section">
    <div class="container position-relative">
        <div class="row align-items-center">
            <div class="col-lg-8">
                <div class="hero-badge">
                    <i class="bi bi-mortarboard-fill text-warning"></i> Master Data Science & Big Data &bull; 2025-2026
                </div>
                <h1 class="hero-title">
                    Laboratoire d'Informatique &amp;<br>
                    <span>Systèmes Avancés (LIAS)</span>
                </h1>
                <p class="hero-subtitle">
                    Pôle d'excellence en recherche scientifique, intelligence artificielle, architectures Big Data et systèmes distribués à la Faculté des Sciences Ben M'Sik de Casablanca.
                </p>
                <div class="d-flex flex-wrap gap-3">
                    <a href="adhesion.jsp" class="btn btn-warning text-dark fw-bold px-4 py-2 rounded-3 shadow">
                        <i class="bi bi-send-fill me-2"></i> Déposer une candidature
                    </a>
                    <a href="presentation.jsp" class="btn btn-outline-light px-4 py-2 rounded-3">
                        <i class="bi bi-info-circle me-2"></i> Découvrir le laboratoire
                    </a>
                    <a href="login" class="btn btn-primary-lias px-4 py-2 rounded-3">
                        <i class="bi bi-lock-fill me-2"></i> Espace Membres
                    </a>
                </div>
            </div>
            <div class="col-lg-4 d-none d-lg-block text-center">
                <div class="p-4 bg-white bg-opacity-10 rounded-4 border border-white border-opacity-25 shadow-lg backdrop-blur">
                    <i class="bi bi-diagram-3-fill text-info" style="font-size: 5rem;"></i>
                    <h5 class="text-white mt-3 fw-bold">Recherche & Innovation</h5>
                    <p class="text-light small mb-0">Traçabilité des activités, mémoire institutionnelle et valorisation scientifique.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SECTION COMPTEURS CLÉS STATS -->
<section class="py-5" style="margin-top: -30px; position: relative; z-index: 10;">
    <div class="container">
        <div class="row g-4">
            <div class="col-md-3 col-6">
                <div class="stat-card">
                    <div class="icon-box bg-primary bg-opacity-10 text-primary">
                        <i class="bi bi-people-fill"></i>
                    </div>
                    <div class="stat-number"><%= nbMembres %></div>
                    <div class="stat-label">Chercheurs &amp; Membres</div>
                </div>
            </div>
            <div class="col-md-3 col-6">
                <div class="stat-card">
                    <div class="icon-box bg-success bg-opacity-10 text-success">
                        <i class="bi bi-journal-check"></i>
                    </div>
                    <div class="stat-number"><%= nbPublications %></div>
                    <div class="stat-label">Publications Scientifiques</div>
                </div>
            </div>
            <div class="col-md-3 col-6">
                <div class="stat-card">
                    <div class="icon-box bg-warning bg-opacity-10 text-warning">
                        <i class="bi bi-calendar-event"></i>
                    </div>
                    <div class="stat-number"><%= nbEvenements %></div>
                    <div class="stat-label">Événements &amp; Congrès</div>
                </div>
            </div>
            <div class="col-md-3 col-6">
                <div class="stat-card">
                    <div class="icon-box bg-info bg-opacity-10 text-info">
                        <i class="bi bi-diagram-3"></i>
                    </div>
                    <div class="stat-number"><%= nbEquipes %></div>
                    <div class="stat-label">Équipes de Recherche</div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SECTION PRÉSENTATION & OBJECTIFS -->
<section class="py-5">
    <div class="container">
        <div class="row align-items-center g-5">
            <div class="col-lg-6">
                <span class="badge bg-primary bg-opacity-10 text-primary px-3 py-2 rounded-pill fw-semibold mb-3">À Propos du LIAS</span>
                <h2 class="fw-bold mb-3 text-dark">Une vision moderne de la recherche scientifique</h2>
                <p class="text-secondary">
                    Le laboratoire LIAS regroupe des enseignants-chercheurs permanents, des membres associés et des doctorants engagés dans des projets novateurs. Notre mission est de centraliser la gestion, assurer une traçabilité rigoureuse de nos activités et capitaliser la mémoire du laboratoire.
                </p>
                <div class="row g-3 mt-2">
                    <div class="col-sm-6">
                        <div class="d-flex align-items-start gap-2">
                            <i class="bi bi-check-circle-fill text-success fs-5"></i>
                            <div>
                                <h6 class="fw-bold mb-1">Traçabilité intégrale</h6>
                                <p class="text-muted small">Historique complet des rôles, statuts et mandats.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="d-flex align-items-start gap-2">
                            <i class="bi bi-check-circle-fill text-success fs-5"></i>
                            <div>
                                <h6 class="fw-bold mb-1">Mémoire institutionnelle</h6>
                                <p class="text-muted small">Archivage des PV, documents et rapports d'activité.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="d-flex align-items-start gap-2">
                            <i class="bi bi-check-circle-fill text-success fs-5"></i>
                            <div>
                                <h6 class="fw-bold mb-1">Rapport annuel automatique</h6>
                                <p class="text-muted small">Production synthétique pour la dotation budgétaire.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="d-flex align-items-start gap-2">
                            <i class="bi bi-check-circle-fill text-success fs-5"></i>
                            <div>
                                <h6 class="fw-bold mb-1">Distribution équitable</h6>
                                <p class="text-muted small">Gestion transparente du matériel de pointe.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="card border-0 shadow-lg rounded-4 overflow-hidden">
                    <div class="card-header bg-primary text-white p-4">
                        <h5 class="mb-0 fw-bold"><i class="bi bi-bullseye me-2"></i> Axes Stratégiques de Recherche</h5>
                    </div>
                    <div class="card-body p-4 bg-white">
                        <ul class="list-unstyled mb-0">
                            <li class="d-flex gap-3 mb-4">
                                <div class="bg-primary bg-opacity-10 text-primary p-3 rounded-circle d-flex align-items-center justify-content-center" style="width: 50px; height: 50px;">
                                    <i class="bi bi-cpu fs-5"></i>
                                </div>
                                <div>
                                    <h6 class="fw-bold mb-1">Intelligence Artificielle &amp; Big Data</h6>
                                    <p class="text-muted small mb-0">Algorithmes d'apprentissage profond, NLP, vision par ordinateur et analyse prédictive sur flux massifs.</p>
                                </div>
                            </li>
                            <li class="d-flex gap-3 mb-4">
                                <div class="bg-info bg-opacity-10 text-info p-3 rounded-circle d-flex align-items-center justify-content-center" style="width: 50px; height: 50px;">
                                    <i class="bi bi-cloud-arrow-up fs-5"></i>
                                </div>
                                <div>
                                    <h6 class="fw-bold mb-1">Systèmes Distribués &amp; Cloud Computing</h6>
                                    <p class="text-muted small mb-0">Architectures microservices, conteneurisation, sécurité des données et edge computing.</p>
                                </div>
                            </li>
                            <li class="d-flex gap-3">
                                <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-circle d-flex align-items-center justify-content-center" style="width: 50px; height: 50px;">
                                    <i class="bi bi-shield-lock fs-5"></i>
                                </div>
                                <div>
                                    <h6 class="fw-bold mb-1">IoT &amp; Cybersécurité des Systèmes</h6>
                                    <p class="text-muted small mb-0">Protocoles sécurisés pour objets connectés, détection d'intrusions et résilience informatique.</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- SECTION ÉQUIPES DE RECHERCHE -->
<section class="py-5 bg-white border-top border-bottom">
    <div class="container">
        <div class="d-flex justify-content-between align-items-end mb-4">
            <div>
                <span class="badge bg-info bg-opacity-10 text-info px-3 py-2 rounded-pill fw-semibold mb-2">Structure Scientifique</span>
                <h3 class="fw-bold text-dark mb-0">Équipes de Recherche</h3>
            </div>
            <a href="equipes.jsp" class="btn btn-outline-lias btn-sm">
                Voir toutes les équipes <i class="bi bi-arrow-right ms-1"></i>
            </a>
        </div>

        <div class="row g-4">
            <% for(int i=0; i < Math.min(3, equipes.size()); i++) { 
                Equipe eq = equipes.get(i);
            %>
            <div class="col-lg-4 col-md-6">
                <div class="feature-card">
                    <div class="feature-icon">
                        <i class="bi bi-diagram-3-fill"></i>
                    </div>
                    <h5 class="fw-bold mb-2 text-dark"><%= eq.getNom() %></h5>
                    <p class="text-muted small mb-3 flex-grow-1"><%= eq.getDomaine() %></p>
                    <div class="pt-3 border-top d-flex align-items-center justify-content-between">
                        <div class="small text-muted">
                            <i class="bi bi-person-fill text-primary me-1"></i> Resp : <span class="fw-semibold text-dark"><%= eq.getResponsable() %></span>
                        </div>
                        <span class="badge bg-light text-secondary border">Actif</span>
                    </div>
                </div>
            </div>
            <% } %>
        </div>
    </div>
</section>

<!-- SECTION DERNIÈRES PUBLICATIONS ET ÉVÉNEMENTS -->
<section class="py-5">
    <div class="container">
        <div class="row g-5">
            <!-- Dernières Publications -->
            <div class="col-lg-7">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <span class="badge bg-success bg-opacity-10 text-success px-3 py-1 rounded-pill fw-semibold mb-1">Publications Récentes</span>
                        <h4 class="fw-bold text-dark mb-0">Production Scientifique</h4>
                    </div>
                    <a href="publications.jsp" class="btn btn-sm btn-outline-secondary">Toutes les publications</a>
                </div>

                <div class="d-flex flex-column gap-3">
                    <% for(int i=0; i < Math.min(3, publications.size()); i++) { 
                        Publication p = publications.get(i);
                    %>
                    <div class="card border-0 shadow-sm rounded-3 p-3 hover-shadow transition">
                        <div class="d-flex justify-content-between align-items-start mb-2">
                            <span class="badge bg-primary bg-opacity-10 text-primary fw-semibold"><%= p.getEquipe() %></span>
                            <span class="badge bg-light text-dark border"><%= p.getAnnee() %></span>
                        </div>
                        <h6 class="fw-bold text-dark mb-1"><%= p.getTitre() %></h6>
                        <p class="text-muted small mb-0"><i class="bi bi-person me-1"></i> <%= p.getAuteur() %></p>
                    </div>
                    <% } %>
                </div>
            </div>

            <!-- Prochains Événements -->
            <div class="col-lg-5">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <div>
                        <span class="badge bg-warning bg-opacity-10 text-warning px-3 py-1 rounded-pill fw-semibold mb-1">Agenda</span>
                        <h4 class="fw-bold text-dark mb-0">Événements à Venir</h4>
                    </div>
                    <a href="evenements.jsp" class="btn btn-sm btn-outline-secondary">Tous</a>
                </div>

                <div class="d-flex flex-column gap-3">
                    <% for(int i=0; i < Math.min(3, evenements.size()); i++) { 
                        Evenement ev = evenements.get(i);
                    %>
                    <div class="card border-0 shadow-sm rounded-3 p-3">
                        <div class="d-flex align-items-center gap-3">
                            <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-3 text-center" style="min-width: 65px;">
                                <i class="bi bi-calendar3 fs-4"></i>
                            </div>
                            <div class="flex-grow-1">
                                <span class="badge bg-light text-secondary border mb-1"><%= ev.getType() %></span>
                                <h6 class="fw-bold text-dark mb-1"><%= ev.getTitre() %></h6>
                                <p class="text-muted small mb-0"><i class="bi bi-clock me-1"></i> Date : <%= ev.getDate() %></p>
                            </div>
                        </div>
                    </div>
                    <% } %>
                </div>

                <!-- Card Rejoindre -->
                <div class="card bg-primary text-white border-0 rounded-4 mt-4 p-4 shadow">
                    <h5 class="fw-bold mb-2"><i class="bi bi-mortarboard me-2"></i> Rejoindre le Laboratoire ?</h5>
                    <p class="small text-light mb-3">Vous êtes doctorant ou enseignant-chercheur souhaitant collaborer avec le LIAS ? Déposez votre candidature en ligne.</p>
                    <a href="adhesion.jsp" class="btn btn-warning text-dark fw-bold btn-sm">
                        <i class="bi bi-person-plus-fill me-1"></i> Remplir la demande d'adhésion
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="includes/public_footer.jsp" />
