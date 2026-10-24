<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%@ page import="com.lias.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    SampleData.init();
    ArrayList<Evenement> evenements = BaseEvenements.getEvenements();
    request.setAttribute("pageTitle", "Événements Scientifiques");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Événements</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Événements Scientifiques &amp; Congrès</h1>
        <p class="lead text-light mb-0">Conférences, séminaires doctoraux et workshops organisés par le Laboratoire LIAS</p>
    </div>
</div>

<div class="container py-5">
    <div class="row g-4 mb-4">
        <% for(Evenement ev : evenements) { 
            String badgeClass = "bg-primary";
            if("Workshop".equalsIgnoreCase(ev.getType())) badgeClass = "bg-success";
            else if("Séminaire".equalsIgnoreCase(ev.getType())) badgeClass = "bg-warning text-dark";
        %>
        <div class="col-lg-4 col-md-6">
            <div class="custom-card h-100 p-4 d-flex flex-column">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <span class="badge <%= badgeClass %> px-3 py-1"><%= ev.getType() %></span>
                    <span class="small text-muted"><i class="bi bi-clock me-1"></i> <%= ev.getDate() %></span>
                </div>
                <h5 class="fw-bold text-dark mb-2"><%= ev.getTitre() %></h5>
                <p class="text-secondary small mb-4 flex-grow-1">
                    Manifestation scientifique rassemblant chercheurs, industriels et étudiants de Master Data Science &amp; Big Data.
                </p>
                <div class="pt-3 border-top d-flex justify-content-between align-items-center">
                    <span class="small text-muted"><i class="bi bi-geo-alt text-danger me-1"></i> Faculté des Sciences</span>
                    <a href="calendrier.jsp" class="btn btn-sm btn-outline-primary"><i class="bi bi-calendar3 me-1"></i> Voir calendrier</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
