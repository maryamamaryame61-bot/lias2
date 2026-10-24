<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%@ page import="com.lias.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    SampleData.init();
    ArrayList<Evenement> evenements = BaseEvenements.getEvenements();
    ArrayList<CalendrierEvent> calEvents = BaseCalendrier.getEvents();
    request.setAttribute("pageTitle", "Calendrier Scientifique");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Calendrier</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Calendrier des Activités</h1>
        <p class="lead text-light mb-0">Planning des soutenances, conférences, séminaires et réunions scientifiques</p>
    </div>
</div>

<div class="container py-5">
    <div class="row g-4">
        <div class="col-lg-8">
            <div class="custom-card p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h5 class="fw-bold text-dark mb-0"><i class="bi bi-calendar-week text-primary me-2"></i> Vue Temporelle &bull; Année Universitaire 2025-2026</h5>
                    <span class="badge bg-light text-secondary border">Calendrier Officiel</span>
                </div>

                <div class="list-group list-group-flush">
                    <% for(CalendrierEvent ce : calEvents) { %>
                    <div class="list-group-item px-0 py-3 d-flex align-items-center gap-3">
                        <div class="bg-primary bg-opacity-10 text-primary p-3 rounded-3 text-center" style="min-width: 90px;">
                            <div class="small fw-bold text-uppercase"><%= ce.getDate() %></div>
                        </div>
                        <div class="flex-grow-1">
                            <h6 class="fw-bold text-dark mb-1"><%= ce.getTitre() %></h6>
                            <p class="text-muted small mb-0"><i class="bi bi-geo-alt me-1 text-danger"></i> <%= ce.getDescription() %></p>
                        </div>
                        <span class="badge bg-success bg-opacity-10 text-success">Planifié</span>
                    </div>
                    <% } %>

                    <% for(Evenement ev : evenements) { %>
                    <div class="list-group-item px-0 py-3 d-flex align-items-center gap-3">
                        <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-3 text-center" style="min-width: 90px;">
                            <div class="small fw-bold text-uppercase"><%= ev.getDate() %></div>
                        </div>
                        <div class="flex-grow-1">
                            <span class="badge bg-light text-secondary border small mb-1"><%= ev.getType() %></span>
                            <h6 class="fw-bold text-dark mb-1"><%= ev.getTitre() %></h6>
                            <p class="text-muted small mb-0"><i class="bi bi-building me-1"></i> Laboratoire LIAS, FSBM</p>
                        </div>
                        <span class="badge bg-primary bg-opacity-10 text-primary">Scientifique</span>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>

        <div class="col-lg-4">
            <div class="custom-card p-4 bg-light border-0">
                <h6 class="fw-bold text-dark mb-3"><i class="bi bi-info-circle text-primary me-2"></i> Légende &amp; Périodes</h6>
                <ul class="list-unstyled small text-muted mb-0">
                    <li class="mb-2"><span class="badge bg-primary me-2">&bull;</span> Conférences Internationales</li>
                    <li class="mb-2"><span class="badge bg-success me-2">&bull;</span> Soutenances de Thèses</li>
                    <li class="mb-2"><span class="badge bg-warning text-dark me-2">&bull;</span> Séminaires d'équipe</li>
                    <li class="mb-2"><span class="badge bg-info text-dark me-2">&bull;</span> Workshops &amp; Formations</li>
                </ul>
            </div>

            <div class="custom-card p-4 mt-4">
                <h6 class="fw-bold text-dark mb-2">Ajouter un événement ?</h6>
                <p class="small text-muted mb-3">Réservé aux membres autorisés et à la direction du laboratoire.</p>
                <a href="login" class="btn btn-outline-primary btn-sm w-100">
                    <i class="bi bi-lock me-1"></i> Connexion Espace Membre
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
