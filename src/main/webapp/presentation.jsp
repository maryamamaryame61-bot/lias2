<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%
    SampleData.init();
    request.setAttribute("pageTitle", "Présentation du Laboratoire");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Présentation</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Présentation du Laboratoire LIAS</h1>
        <p class="lead text-light mb-0">Laboratoire d'Informatique &amp; Systèmes Avancés &bull; Faculté des Sciences Ben M'Sik</p>
    </div>
</div>

<div class="container py-5">
    <div class="row g-5">
        <div class="col-lg-8">
            <div class="custom-card p-4 mb-4">
                <h4 class="fw-bold text-primary mb-3"><i class="bi bi-bank2 me-2"></i> Identité &amp; Mission</h4>
                <p class="text-secondary leading-relaxed">
                    Le <strong>Laboratoire d'Informatique et Systèmes Avancés (LIAS)</strong> est une structure de recherche rattachée à la <strong>Faculté des Sciences Ben M'Sik – Université Hassan II de Casablanca</strong>. Il fédère des enseignants-chercheurs, des chercheurs associés et des doctorants autour de thématiques prioritaires au cœur de la transition numérique : la Science des Données, l'Intelligence Artificielle générative et prédictive, les architectures Big Data, ainsi que la sécurité des systèmes distribués et embarqués.
                </p>
                <p class="text-secondary leading-relaxed">
                    Conformément aux directives universitaires, le LIAS a pour vocation de structurer la gouvernance scientifique, d'assurer une traçabilité totale de ses activités administratives et scientifiques, et de capitaliser la mémoire du laboratoire pour les générations futures de chercheurs.
                </p>
            </div>

            <div class="custom-card p-4 mb-4">
                <h4 class="fw-bold text-primary mb-3"><i class="bi bi-check2-circle me-2"></i> Objectifs Stratégiques du Système</h4>
                <div class="row g-3">
                    <div class="col-md-6">
                        <div class="border rounded-3 p-3 bg-light h-100">
                            <h6 class="fw-bold text-dark"><i class="bi bi-1-circle text-primary me-2"></i> Centralisation &amp; Traçabilité</h6>
                            <p class="text-muted small mb-0">Centraliser la gestion globale et garantir la traçabilité intégrale de chaque décision, publication et mouvement de matériel.</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="border rounded-3 p-3 bg-light h-100">
                            <h6 class="fw-bold text-dark"><i class="bi bi-2-circle text-primary me-2"></i> Mémoire Institutionnelle</h6>
                            <p class="text-muted small mb-0">Archivage rigoureux des documents, ordres du jour, procès-verbaux (PV) et décisions de gouvernance.</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="border rounded-3 p-3 bg-light h-100">
                            <h6 class="fw-bold text-dark"><i class="bi bi-3-circle text-primary me-2"></i> Cycle de Vie des Membres</h6>
                            <p class="text-muted small mb-0">Gestion fine des statuts (permanents, associés, doctorants, retraités, anciens) et des mandats électifs sans perte de données.</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="border rounded-3 p-3 bg-light h-100">
                            <h6 class="fw-bold text-dark"><i class="bi bi-4-circle text-primary me-2"></i> Rapport Annuel Automatisé</h6>
                            <p class="text-muted small mb-0">Génération automatique du rapport d'activité annuel, condition sine qua non de la dotation budgétaire ministérielle.</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="custom-card p-4">
                <h4 class="fw-bold text-primary mb-3"><i class="bi bi-people-fill me-2"></i> Typologie des Utilisateurs (Cahier des Charges &sect; 2)</h4>
                <div class="table-responsive">
                    <table class="table table-bordered table-custom">
                        <thead>
                            <tr>
                                <th>Profil</th>
                                <th>Description &amp; Rattachement</th>
                                <th>Niveau d'Accès</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><span class="badge bg-secondary">Visiteurs (Public)</span></td>
                                <td>Grand public, étudiants, partenaires externes</td>
                                <td>Accès libre : Présentation, équipes, publications, événements, candidature en ligne</td>
                            </tr>
                            <tr>
                                <td><span class="badge-status badge-permanent">Membres Permanents</span></td>
                                <td>Enseignants-chercheurs titulaires LIAS</td>
                                <td>Accès complet selon rôle (Directeur, Chef d'équipe, Membre)</td>
                            </tr>
                            <tr>
                                <td><span class="badge-status badge-associe">Membres Associés</span></td>
                                <td>Collaborateurs externes d'autres laboratoires</td>
                                <td>Consultation, participation aux événements, publications selon droits</td>
                            </tr>
                            <tr>
                                <td><span class="badge-status badge-doctorant">Doctorants</span></td>
                                <td>Étudiants inscrits en thèse au LIAS</td>
                                <td>Gestion des publications personnelles (accès restreint aux modules internes)</td>
                            </tr>
                            <tr>
                                <td><span class="badge-status badge-retraite">Retraités &amp; Anciens</span></td>
                                <td>Professeurs émérites ou chercheurs ayant quitté le labo</td>
                                <td>Compte gelé ou désactivé avec historique et traçabilité conservés</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="custom-card p-4 mb-4">
                <h5 class="fw-bold text-dark mb-3"><i class="bi bi-shield-check text-success me-2"></i> Gouvernance Actuelle</h5>
                <ul class="list-unstyled mb-0">
                    <li class="mb-3 pb-3 border-bottom">
                        <small class="text-muted d-block text-uppercase fw-semibold">Directeur du Laboratoire</small>
                        <strong class="text-dark">Pr. Hassan El Idrissi</strong>
                        <div class="small text-muted">Mandat 2024 - 2027</div>
                    </li>
                    <li class="mb-3 pb-3 border-bottom">
                        <small class="text-muted d-block text-uppercase fw-semibold">Vice-Directrice</small>
                        <strong class="text-dark">Dr. Fatima Zahra Bennani</strong>
                        <div class="small text-muted">Coordination scientifique</div>
                    </li>
                    <li>
                        <small class="text-muted d-block text-uppercase fw-semibold">Responsable des Thèses</small>
                        <strong class="text-dark">Pr. Mohamed Alami</strong>
                        <div class="small text-muted">Master DSBD &amp; Doctorat</div>
                    </li>
                </ul>
            </div>

            <div class="custom-card p-4 bg-light border-0">
                <h5 class="fw-bold text-dark mb-2">Rejoindre nos équipes</h5>
                <p class="small text-muted mb-3">Le laboratoire accepte les candidatures de doctorants et de chercheurs associés tout au long de l'année.</p>
                <a href="adhesion.jsp" class="btn btn-warning text-dark fw-bold btn-sm w-100">
                    <i class="bi bi-send-fill me-1"></i> Soumettre ma candidature
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
