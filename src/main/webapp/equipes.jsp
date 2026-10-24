<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%@ page import="com.lias.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    SampleData.init();
    ArrayList<Equipe> equipes = BaseEquipes.getEquipes();
    ArrayList<Membre> membres = BaseMembres.getMembres();
    request.setAttribute("pageTitle", "Équipes de Recherche");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Équipes</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Équipes de Recherche</h1>
        <p class="lead text-light mb-0">Découvrez les axes et les structures scientifiques du Laboratoire LIAS</p>
    </div>
</div>

<div class="container py-5">
    <div class="row g-4 mb-5">
        <% for(int i=0; i < equipes.size(); i++) { 
            Equipe eq = equipes.get(i);
        %>
        <div class="col-lg-4 col-md-6">
            <div class="feature-card">
                <div class="feature-icon">
                    <i class="bi bi-diagram-3-fill"></i>
                </div>
                <h4 class="fw-bold text-dark mb-2"><%= eq.getNom() %></h4>
                <div class="mb-3">
                    <span class="badge bg-primary bg-opacity-10 text-primary">Axe Stratégique</span>
                </div>
                <p class="text-secondary small mb-4 flex-grow-1"><%= eq.getDomaine() %></p>

                <div class="p-3 bg-light rounded-3 border mb-3">
                    <div class="small text-muted mb-1">Responsable d'équipe :</div>
                    <div class="fw-bold text-dark d-flex align-items-center gap-2">
                        <i class="bi bi-person-circle text-primary"></i> <%= eq.getResponsable() %>
                    </div>
                </div>

                <div class="d-flex justify-content-between align-items-center pt-2">
                    <span class="badge bg-success bg-opacity-10 text-success"><i class="bi bi-check-circle me-1"></i> Équipe Active</span>
                    <a href="publications.jsp?equipe=<%= eq.getNom() %>" class="btn btn-sm btn-outline-primary">Publications associées</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <!-- MEMBRES PAR ÉQUIPE -->
    <div class="custom-card p-4">
        <h4 class="fw-bold text-dark mb-3"><i class="bi bi-people-fill text-primary me-2"></i> Chercheurs &amp; Doctorants du LIAS</h4>
        <p class="text-muted small mb-4">Liste des membres rattachés aux différentes équipes et programmes de recherche.</p>

        <div class="table-responsive">
            <table class="table table-custom">
                <thead>
                    <tr>
                        <th>Nom &amp; Prénom</th>
                        <th>Email Professionnel</th>
                        <th>Statut Officiel</th>
                        <th>Rôle</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Membre m : membres) { 
                        String statutBadge = "badge-permanent";
                        if("associe".equalsIgnoreCase(m.getStatut())) statutBadge = "badge-associe";
                        else if("doctorant".equalsIgnoreCase(m.getStatut())) statutBadge = "badge-doctorant";
                        else if("retraite".equalsIgnoreCase(m.getStatut())) statutBadge = "badge-retraite";
                    %>
                    <tr>
                        <td>
                            <div class="d-flex align-items-center gap-2">
                                <i class="bi bi-person-fill text-muted fs-5"></i>
                                <div>
                                    <strong class="text-dark"><%= m.getNom() %> <%= m.getPrenom() %></strong>
                                </div>
                            </div>
                        </td>
                        <td><a href="mailto:<%= m.getEmail() %>" class="text-primary text-decoration-none"><%= m.getEmail() %></a></td>
                        <td><span class="badge-status <%= statutBadge %>"><%= m.getStatut() %></span></td>
                        <td>
                            <% if("retraite".equalsIgnoreCase(m.getStatut())) { %>
                                <span class="badge bg-light text-muted border">Compte Gelé</span>
                            <% } else { %>
                                <span class="badge bg-light text-dark border">Actif</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
