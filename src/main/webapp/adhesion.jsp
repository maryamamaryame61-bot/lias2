<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setAttribute("pageTitle", "Demande d'Adhésion en Ligne");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Candidature &amp; Adhésion</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Rejoindre le Laboratoire LIAS</h1>
        <p class="lead text-light mb-0">Formulaire officiel de demande d'adhésion pour doctorants et enseignants-chercheurs</p>
    </div>
</div>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="custom-card p-4 p-md-5">
                <div class="text-center mb-4">
                    <span class="badge bg-warning text-dark px-3 py-2 rounded-pill fw-semibold mb-2">
                        <i class="bi bi-mortarboard-fill me-1"></i> Candidature &bull; Session 2025-2026
                    </span>
                    <h3 class="fw-bold text-dark">Formulaire de Soumission en Ligne</h3>
                    <p class="text-secondary small">
                        Conformément au cahier des charges, votre dossier sera transmis au Directeur du Laboratoire pour examen et validation.
                    </p>
                </div>

                <form method="post" action="ajouterDemande">
                    <div class="row g-3 mb-3">
                        <div class="col-md-12">
                            <label class="form-label-custom">Nom complet &amp; Prénom <span class="text-danger">*</span></label>
                            <input type="text" name="nom" class="form-control-custom" placeholder="Ex: Pr. / Dr. / M. Nom Prénom" required>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label-custom">Adresse Email Professionnelle ou Personnelle <span class="text-danger">*</span></label>
                        <input type="email" name="email" class="form-control-custom" placeholder="Ex: prenom.nom@univ-fsbm.ma" required>
                        <div class="form-text small">Vous recevrez la notification de validation du directeur à cette adresse.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label-custom">Profil / Statut postulé</label>
                        <select class="form-select-custom">
                            <option value="doctorant">Doctorant (Thèse de doctorat au LIAS)</option>
                            <option value="permanent">Enseignant-Chercheur Permanent</option>
                            <option value="associe">Chercheur Associé / Collaborateur externe</option>
                        </select>
                    </div>

                    <div class="mb-4">
                        <label class="form-label-custom">Lettre de motivation &amp; Thématique de recherche <span class="text-danger">*</span></label>
                        <textarea name="motivation" rows="5" class="form-control-custom" placeholder="Présentez votre parcours, vos diplômes (ex: Master Data Science & Big Data), vos intérêts de recherche et vos motivations à intégrer le laboratoire LIAS..." required></textarea>
                    </div>

                    <div class="alert alert-info d-flex align-items-center gap-2 small mb-4">
                        <i class="bi bi-info-circle-fill fs-5"></i>
                        <div>
                            <strong>Règle de gouvernance :</strong> Seul le <em>Directeur du Laboratoire en mandat actif</em> est habilité à valider votre candidature. En cas d'acceptation, votre compte membre sera créé automatiquement.
                        </div>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <a href="index.jsp" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i> Annuler
                        </a>
                        <button type="submit" class="btn btn-warning text-dark fw-bold px-4 py-2">
                            <i class="bi bi-send-fill me-1"></i> Soumettre ma demande
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
