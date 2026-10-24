<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String footerContextPath = request.getContextPath();
%>
<!-- PIED DE PAGE -->
<footer class="bg-dark text-white pt-5 pb-4 mt-5 border-top border-secondary">
    <div class="container">
        <div class="row g-4">
            <div class="col-lg-4 col-md-6">
                <div class="d-flex align-items-center gap-2 mb-3">
                    <img src="<%= footerContextPath %>/images/lias-logo.svg" alt="LIAS Logo" height="42" class="bg-white p-1 rounded">
                </div>
                <p class="text-secondary small mb-3">
                    Laboratoire de recherche d'excellence en Informatique, Science des Données, Intelligence Artificielle et Systèmes Complexes.
                </p>
                <div class="text-secondary small">
                    <p class="mb-1"><i class="bi bi-geo-alt text-warning me-2"></i> Faculté des Sciences Ben M'Sik, Av. Driss El Harti, BP 7955, Sidi Othmane, Casablanca</p>
                    <p class="mb-1"><i class="bi bi-envelope text-warning me-2"></i> lias@univ-fsbm.ma</p>
                    <p class="mb-0"><i class="bi bi-telephone text-warning me-2"></i> +212 522 70 46 71</p>
                </div>
            </div>

            <div class="col-lg-2 col-md-6">
                <h6 class="text-white text-uppercase fw-bold mb-3 small tracking-wide">Accès Rapide</h6>
                <ul class="list-unstyled text-secondary small">
                    <li class="mb-2"><a href="<%= footerContextPath %>/index.jsp" class="text-secondary text-decoration-none hover-white">Accueil</a></li>
                    <li class="mb-2"><a href="<%= footerContextPath %>/presentation.jsp" class="text-secondary text-decoration-none hover-white">Présentation</a></li>
                    <li class="mb-2"><a href="<%= footerContextPath %>/equipes.jsp" class="text-secondary text-decoration-none hover-white">Équipes de recherche</a></li>
                    <li class="mb-2"><a href="<%= footerContextPath %>/publications.jsp" class="text-secondary text-decoration-none hover-white">Publications</a></li>
                    <li class="mb-2"><a href="<%= footerContextPath %>/evenements.jsp" class="text-secondary text-decoration-none hover-white">Événements & Séminaires</a></li>
                </ul>
            </div>

            <div class="col-lg-3 col-md-6">
                <h6 class="text-white text-uppercase fw-bold mb-3 small tracking-wide">Recherche & Formation</h6>
                <ul class="list-unstyled text-secondary small">
                    <li class="mb-2"><span class="text-light">Master Data Science & Big Data</span> (2025-2026)</li>
                    <li class="mb-2"><span class="text-light">Intelligence Artificielle & Deep Learning</span></li>
                    <li class="mb-2"><span class="text-light">Systèmes Distribués & Cloud</span></li>
                    <li class="mb-2"><a href="<%= footerContextPath %>/adhesion.jsp" class="text-warning text-decoration-none fw-semibold"><i class="bi bi-arrow-right-short"></i> Postuler au Laboratoire</a></li>
                </ul>
            </div>

            <div class="col-lg-3 col-md-6">
                <h6 class="text-white text-uppercase fw-bold mb-3 small tracking-wide">Espace Professionnel</h6>
                <p class="text-secondary small mb-3">Accès réservé aux chercheurs permanents, associés, doctorants et administration du laboratoire.</p>
                <a href="<%= footerContextPath %>/login" class="btn btn-outline-light btn-sm w-100 mb-2">
                    <i class="bi bi-shield-lock me-1"></i> Connexion Espace Membre
                </a>
                <a href="<%= footerContextPath %>/adhesion.jsp" class="btn btn-warning btn-sm w-100 text-dark fw-semibold">
                    <i class="bi bi-send-fill me-1"></i> Demande d'adhésion en ligne
                </a>
            </div>
        </div>

        <hr class="border-secondary my-4">

        <div class="d-flex flex-column flex-md-row justify-content-between align-items-center small text-secondary">
            <div>
                &copy; 2025-2026 <strong>Laboratoire LIAS</strong> &bull; Faculté des Sciences Ben M'Sik &bull; Université Hassan II Casablanca.
            </div>
            <div class="mt-2 mt-md-0">
                <span>Projet JEE &bull; Master Data Science & Big Data</span>
            </div>
        </div>
    </div>
</footer>

<!-- Bootstrap JS Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="<%= footerContextPath %>/js/main.js"></script>
</body>
</html>
