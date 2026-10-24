<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lias.data.*" %>
<%@ page import="com.lias.model.*" %>
<%@ page import="java.util.ArrayList" %>
<%
    SampleData.init();
    ArrayList<Publication> publications = BasePublications.getPublications();
    request.setAttribute("pageTitle", "Publications Scientifiques");
%>
<jsp:include page="includes/public_header.jsp" />

<div class="bg-primary text-white py-5">
    <div class="container">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb breadcrumb-dark mb-2">
                <li class="breadcrumb-item"><a href="index.jsp" class="text-white text-opacity-75 text-decoration-none">Accueil</a></li>
                <li class="breadcrumb-item active text-white" aria-current="page">Publications</li>
            </ol>
        </nav>
        <h1 class="fw-bold mb-2">Publications Scientifiques du LIAS</h1>
        <p class="lead text-light mb-0">Revue, conférences internationales et articles scientifiques produits par nos chercheurs</p>
    </div>
</div>

<div class="container py-5">
    <div class="custom-card p-4 mb-4">
        <div class="row g-3 align-items-center">
            <div class="col-md-6">
                <div class="position-relative">
                    <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                    <input type="text" id="pubSearch" class="form-control ps-5 table-search-input" data-table="pubTable" placeholder="Rechercher par titre, auteur, équipe, année...">
                </div>
            </div>
            <div class="col-md-6 text-md-end">
                <span class="badge bg-primary fs-6 px-3 py-2"><%= publications.size() %> publications indexées</span>
            </div>
        </div>
    </div>

    <div class="custom-card p-4">
        <div class="table-responsive">
            <table class="table table-custom align-middle" id="pubTable">
                <thead>
                    <tr>
                        <th style="width: 50%;">Titre de la Publication</th>
                        <th>Auteurs</th>
                        <th>Année</th>
                        <th>Équipe</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Publication p : publications) { %>
                    <tr>
                        <td>
                            <div class="fw-bold text-dark"><i class="bi bi-file-earmark-text text-primary me-2"></i><%= p.getTitre() %></div>
                            <small class="text-muted">Laboratoire LIAS &bull; Faculté des Sciences Ben M'Sik</small>
                        </td>
                        <td>
                            <span class="text-secondary"><i class="bi bi-person me-1"></i><%= p.getAuteur() %></span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border px-2 py-1"><%= p.getAnnee() %></span>
                        </td>
                        <td>
                            <span class="badge bg-info bg-opacity-10 text-info px-2 py-1"><%= p.getEquipe() %></span>
                        </td>
                        <td>
                            <button class="btn btn-sm btn-outline-primary" onclick="alert('Téléchargement du preprint pour : <%= p.getTitre().replace("'", "\\'") %>');">
                                <i class="bi bi-download me-1"></i> PDF
                            </button>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="includes/public_footer.jsp" />
