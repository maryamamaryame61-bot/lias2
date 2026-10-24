package com.lias.ui;

import jakarta.servlet.http.HttpServletRequest;

public class PageRenderer {

    /**
     * Génère l'entête HTML complète avec navigation, sidebar et topbar
     */
    public static String renderHeader(HttpServletRequest request, String pageTitle, String activeMenu) {
        String contextPath = request.getContextPath();
        String user = (request.getSession(false) != null && request.getSession(false).getAttribute("user") != null)
                ? (String) request.getSession(false).getAttribute("user")
                : "Invité / Chercheur";

        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n");
        sb.append("<html lang=\"fr\">\n<head>\n");
        sb.append("<meta charset=\"UTF-8\">\n");
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        sb.append("<title>").append(pageTitle).append(" | Laboratoire LIAS - FSBM</title>\n");
        sb.append("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n");
        sb.append("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n");
        sb.append("<link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap\" rel=\"stylesheet\">\n");
        sb.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
        sb.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css\">\n");
        sb.append("<link rel=\"stylesheet\" href=\"").append(contextPath).append("/css/style.css\">\n");
        sb.append("</head>\n<body>\n");

        sb.append("<div class=\"dashboard-wrapper\">\n");

        // SIDEBAR
        sb.append("  <aside class=\"sidebar\">\n");
        sb.append("    <div class=\"sidebar-header\">\n");
        sb.append("      <a href=\"").append(contextPath).append("/dashboard\" class=\"sidebar-brand\">\n");
        sb.append("        <i class=\"bi bi-cpu-fill text-info fs-4\"></i>\n");
        sb.append("        <span>LIAS</span> LAB\n");
        sb.append("      </a>\n");
        sb.append("    </div>\n");

        sb.append("    <div class=\"sidebar-section-title\">Général</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/dashboard\" class=\"").append(isActive(activeMenu, "dashboard")).append("\"><i class=\"bi bi-speedometer2\"></i> Tableau de bord</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/accueil\" class=\"").append(isActive(activeMenu, "accueil")).append("\"><i class=\"bi bi-grid-fill\"></i> Menu Général</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/rechercheGlobale\" class=\"").append(isActive(activeMenu, "recherche")).append("\"><i class=\"bi bi-search\"></i> Recherche globale</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/index.jsp\" target=\"_blank\"><i class=\"bi bi-globe\"></i> Portail Public <i class=\"bi bi-box-arrow-up-right ms-auto small\"></i></a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-section-title\">Ressources Humaines</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/lister\" class=\"").append(isActive(activeMenu, "membres")).append("\"><i class=\"bi bi-people\"></i> Gestion Membres</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerDemandes\" class=\"").append(isActive(activeMenu, "demandes")).append("\"><i class=\"bi bi-person-check\"></i> Demandes d'adhésion</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerEquipes\" class=\"").append(isActive(activeMenu, "equipes")).append("\"><i class=\"bi bi-diagram-3\"></i> Équipes de recherche</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerMandats\" class=\"").append(isActive(activeMenu, "mandats")).append("\"><i class=\"bi bi-award\"></i> Gouvernance & Mandats</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerRoles\" class=\"").append(isActive(activeMenu, "roles")).append("\"><i class=\"bi bi-person-badge\"></i> Rôles & Responsabilités</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerAffiliations\" class=\"").append(isActive(activeMenu, "affiliations")).append("\"><i class=\"bi bi-clock-history\"></i> Historique Affiliations</a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-section-title\">Activités Scientifiques</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerPublications\" class=\"").append(isActive(activeMenu, "publications")).append("\"><i class=\"bi bi-journal-text\"></i> Publications</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerEvenements\" class=\"").append(isActive(activeMenu, "evenements")).append("\"><i class=\"bi bi-calendar-event\"></i> Événements & Congrès</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerCalendrier\" class=\"").append(isActive(activeMenu, "calendrier")).append("\"><i class=\"bi bi-calendar3\"></i> Calendrier</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerProjets\" class=\"").append(isActive(activeMenu, "projets")).append("\"><i class=\"bi bi-kanban\"></i> Projets de recherche</a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-section-title\">Documentation & Décisions</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerDocuments\" class=\"").append(isActive(activeMenu, "documents")).append("\"><i class=\"bi bi-folder2-open\"></i> Documents & Archives</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerPV\" class=\"").append(isActive(activeMenu, "pv")).append("\"><i class=\"bi bi-file-earmark-text\"></i> Procès-Verbaux (PV)</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerRapports\" class=\"").append(isActive(activeMenu, "rapports")).append("\"><i class=\"bi bi-file-earmark-bar-graph\"></i> Rapport Annuel</a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-section-title\">Moyens & Partenariats</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerMateriels\" class=\"").append(isActive(activeMenu, "materiels")).append("\"><i class=\"bi bi-cpu\"></i> Parc Matériel</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerBesoins\" class=\"").append(isActive(activeMenu, "besoins")).append("\"><i class=\"bi bi-cart-plus\"></i> Besoins en matériel</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/ListerConventionsServlet\" class=\"").append(isActive(activeMenu, "conventions")).append("\"><i class=\"bi bi-handshake\"></i> Conventions & Partenaires</a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-section-title\">Communication</div>\n");
        sb.append("    <ul class=\"sidebar-menu\">\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerMessages\" class=\"").append(isActive(activeMenu, "messages")).append("\"><i class=\"bi bi-chat-dots\"></i> Messagerie interne</a></li>\n");
        sb.append("      <li><a href=\"").append(contextPath).append("/listerNotifications\" class=\"").append(isActive(activeMenu, "notifications")).append("\"><i class=\"bi bi-bell\"></i> Notifications</a></li>\n");
        sb.append("    </ul>\n");

        sb.append("    <div class=\"sidebar-footer\">\n");
        sb.append("      <a href=\"").append(contextPath).append("/logout\" class=\"text-danger text-decoration-none small fw-semibold d-flex align-items-center gap-2\">\n");
        sb.append("        <i class=\"bi bi-box-arrow-right\"></i> Déconnexion\n");
        sb.append("      </a>\n");
        sb.append("    </div>\n");
        sb.append("  </aside>\n");

        // MAIN WRAPPER
        sb.append("  <div class=\"main-wrapper\">\n");

        // TOPBAR
        sb.append("    <header class=\"topbar\">\n");
        sb.append("      <div class=\"d-flex align-items-center gap-3\">\n");
        sb.append("        <button class=\"btn btn-sm btn-outline-secondary d-md-none\" id=\"sidebarToggle\"><i class=\"bi bi-list\"></i></button>\n");
        sb.append("        <form method=\"post\" action=\"").append(contextPath).append("/rechercheGlobale\" class=\"topbar-search\">\n");
        sb.append("          <i class=\"bi bi-search\"></i>\n");
        sb.append("          <input type=\"text\" name=\"motcle\" class=\"form-control form-control-sm\" placeholder=\"Recherche globale (membres, publis, events...)\">\n");
        sb.append("        </form>\n");
        sb.append("      </div>\n");

        sb.append("      <div class=\"d-flex align-items-center gap-3\">\n");
        sb.append("        <a href=\"").append(contextPath).append("/listerNotifications\" class=\"btn btn-sm btn-light border position-relative rounded-circle p-2 text-secondary\" title=\"Notifications\">\n");
        sb.append("          <i class=\"bi bi-bell-fill\"></i>\n");
        sb.append("          <span class=\"position-absolute top-0 start-100 translate-middle p-1 bg-danger border border-light rounded-circle\"></span>\n");
        sb.append("        </a>\n");

        sb.append("        <div class=\"user-profile-badge\">\n");
        sb.append("          <img src=\"").append(contextPath).append("/images/avatar-default.svg\" alt=\"User\" class=\"user-avatar\">\n");
        sb.append("          <div class=\"d-none d-sm-block text-start\">\n");
        sb.append("            <div class=\"fw-bold small text-dark\">").append(user).append("</div>\n");
        sb.append("            <div class=\"text-muted\" style=\"font-size: 0.75rem;\">Direction / Chercheur</div>\n");
        sb.append("          </div>\n");
        sb.append("          <a href=\"").append(contextPath).append("/logout\" class=\"text-danger ms-2\" title=\"Se déconnecter\"><i class=\"bi bi-power fs-6\"></i></a>\n");
        sb.append("        </div>\n");
        sb.append("      </div>\n");
        sb.append("    </header>\n");

        // CONTENT BODY
        sb.append("    <main class=\"content-body\">\n");

        return sb.toString();
    }

    /**
     * Ferme le contenu et inclut les scripts
     */
    public static String renderFooter(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        StringBuilder sb = new StringBuilder();
        sb.append("    </main>\n"); // Fin content-body

        sb.append("    <footer class=\"py-3 px-4 bg-white border-top small text-muted d-flex justify-content-between align-items-center\">\n");
        sb.append("      <div>&copy; 2025-2026 <strong>Laboratoire LIAS</strong> &bull; Faculté des Sciences Ben M'Sik Casablanca</div>\n");
        sb.append("      <div>Projet JEE &bull; Master Data Science & Big Data</div>\n");
        sb.append("    </footer>\n");

        sb.append("  </div>\n"); // Fin main-wrapper
        sb.append("</div>\n"); // Fin dashboard-wrapper

        sb.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
        sb.append("<script src=\"").append(contextPath).append("/js/main.js\"></script>\n");
        sb.append("</body>\n</html>\n");

        return sb.toString();
    }

    private static String isActive(String activeMenu, String target) {
        return (activeMenu != null && activeMenu.equalsIgnoreCase(target)) ? "active" : "";
    }
}
