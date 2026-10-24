<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String currentUri = request.getRequestURI();
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= (request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") + " | " : "") %>Laboratoire LIAS - FSBM Casablanca</title>
    <!-- Google Fonts: Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
    <!-- Bootstrap 5.3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%= contextPath %>/css/style.css">
</head>
<body>

<!-- BARRE SUPÉRIEURE ACADÉMIQUE -->
<div class="py-2 bg-dark text-white border-bottom border-secondary small">
    <div class="container d-flex justify-content-between align-items-center">
        <div class="d-flex align-items-center gap-3">
            <span><i class="bi bi-mortarboard me-1 text-warning"></i> Faculté des Sciences Ben M'Sik &bull; Casablanca</span>
            <span class="d-none d-md-inline text-secondary">|</span>
            <span class="d-none d-md-inline"><i class="bi bi-geo-alt me-1 text-info"></i> Université Hassan II de Casablanca</span>
        </div>
        <div class="d-flex align-items-center gap-3">
            <a href="mailto:contact.lias@univ-fsbm.ma" class="text-light text-decoration-none"><i class="bi bi-envelope me-1"></i> lias@univ-fsbm.ma</a>
            <span class="text-secondary">|</span>
            <a href="<%= contextPath %>/login" class="text-warning fw-semibold text-decoration-none"><i class="bi bi-lock-fill me-1"></i> Espace Membre</a>
        </div>
    </div>
</div>

<!-- NAVIGATION PRINCIPALE PUBLIQUE -->
<nav class="navbar navbar-expand-lg public-navbar">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center gap-2" href="<%= contextPath %>/index.jsp">
            <img src="<%= contextPath %>/images/lias-logo.svg" alt="LIAS Logo" height="48">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#publicNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="publicNav">
            <ul class="navbar-nav ms-auto align-items-lg-center gap-1">
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("index.jsp") || currentUri.endsWith("/LIAS/") ? "active" : "" %>" href="<%= contextPath %>/index.jsp">
                        <i class="bi bi-house me-1"></i> Accueil
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("presentation.jsp") ? "active" : "" %>" href="<%= contextPath %>/presentation.jsp">
                        <i class="bi bi-info-circle me-1"></i> Le Laboratoire
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("equipes.jsp") ? "active" : "" %>" href="<%= contextPath %>/equipes.jsp">
                        <i class="bi bi-people me-1"></i> Équipes
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("publications.jsp") ? "active" : "" %>" href="<%= contextPath %>/publications.jsp">
                        <i class="bi bi-journal-text me-1"></i> Publications
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("evenements.jsp") ? "active" : "" %>" href="<%= contextPath %>/evenements.jsp">
                        <i class="bi bi-calendar-event me-1"></i> Événements
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= currentUri.endsWith("calendrier.jsp") ? "active" : "" %>" href="<%= contextPath %>/calendrier.jsp">
                        <i class="bi bi-calendar3 me-1"></i> Calendrier
                    </a>
                </li>
                <li class="nav-item ms-lg-2">
                    <a class="btn btn-outline-lias btn-sm" href="<%= contextPath %>/adhesion.jsp">
                        <i class="bi bi-person-plus me-1"></i> Candidater / Adhésion
                    </a>
                </li>
                <li class="nav-item ms-lg-2">
                    <a class="btn btn-primary-lias btn-sm" href="<%= contextPath %>/login">
                        <i class="bi bi-box-arrow-in-right me-1"></i> Connexion
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
