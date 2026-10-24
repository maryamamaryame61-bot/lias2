package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseDocuments;
import com.lias.data.BaseEvenements;
import com.lias.data.BaseMembres;
import com.lias.data.BasePublications;
import com.lias.data.SampleData;
import com.lias.model.DocumentLabo;
import com.lias.model.Evenement;
import com.lias.model.Membre;
import com.lias.model.Publication;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rechercheGlobale")
public class RechercheGlobaleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RechercheGlobaleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Recherche Globale", "recherche"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-9">
                    <div class="text-center mb-4">
                        <h2 class="fw-bold text-dark mb-1">Moteur de Recherche Global &bull; LIAS</h2>
                        <p class="text-secondary small">Recherche transversale simultanée dans les Membres, Documents, Événements et Publications (Cahier des Charges &sect; 18).</p>
                    </div>

                    <div class="custom-card p-4 p-md-5 mb-4 shadow-sm">
                        <form method="post" action="rechercheGlobale">
                            <div class="input-group input-group-lg shadow-sm">
                                <span class="input-group-text bg-white border-end-0 text-muted"><i class="bi bi-search fs-5"></i></span>
                                <input type="text" name="motcle" class="form-control border-start-0" placeholder="Entrez un mot-clé (ex: Alami, Deep Learning, Séminaire, Règlement...)" required autofocus>
                                <button class="btn btn-primary-lias px-4" type="submit">Rechercher</button>
                            </div>
                        </form>
                    </div>

                    <div class="text-center text-muted small">
                        <i class="bi bi-info-circle me-1"></i> Astuce : vous pouvez rechercher le nom d'un enseignant, un axe de recherche ou un type de document.
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        String motcle = request.getParameter("motcle");
        if (motcle == null) motcle = "";
        String query = motcle.toLowerCase().trim();

        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Membre> membresTrouves = new ArrayList<>();
        for (Membre m : BaseMembres.getMembres()) {
            if (m.getNom().toLowerCase().contains(query)
                    || m.getPrenom().toLowerCase().contains(query)
                    || m.getEmail().toLowerCase().contains(query)
                    || (m.getStatut() != null && m.getStatut().toLowerCase().contains(query))) {
                membresTrouves.add(m);
            }
        }

        ArrayList<Publication> pubsTrouvees = new ArrayList<>();
        for (Publication p : BasePublications.getPublications()) {
            if (p.getTitre().toLowerCase().contains(query)
                    || p.getAuteur().toLowerCase().contains(query)
                    || (p.getEquipe() != null && p.getEquipe().toLowerCase().contains(query))
                    || String.valueOf(p.getAnnee()).contains(query)) {
                pubsTrouvees.add(p);
            }
        }

        ArrayList<Evenement> eventsTrouves = new ArrayList<>();
        for (Evenement e : BaseEvenements.getEvenements()) {
            if (e.getTitre().toLowerCase().contains(query)
                    || e.getType().toLowerCase().contains(query)
                    || e.getDate().contains(query)) {
                eventsTrouves.add(e);
            }
        }

        ArrayList<DocumentLabo> docsTrouves = new ArrayList<>();
        for (DocumentLabo d : BaseDocuments.getDocuments()) {
            if (d.getTitre().toLowerCase().contains(query)
                    || d.getType().toLowerCase().contains(query)
                    || d.getDate().contains(query)) {
                docsTrouves.add(d);
            }
        }

        int totalTrouves = membresTrouves.size() + pubsTrouvees.size() + eventsTrouves.size() + docsTrouves.size();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Résultats de Recherche", "recherche"));

        html.append(String.format("""
            <div class="d-flex justify-content-between align-items-center mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Résultats de la Recherche Globale</h2>
                    <p class="text-secondary small mb-0">%d résultat(s) trouvé(s) pour le terme « <strong>%s</strong> »</p>
                </div>
                <a href="rechercheGlobale" class="btn btn-outline-secondary btn-sm"><i class="bi bi-search me-1"></i> Nouvelle recherche</a>
            </div>

            <!-- RESULTATS PAR MODULE -->
            <div class="row g-4">
                <!-- MEMBRES -->
                <div class="col-md-6">
                    <div class="custom-card h-100">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-people-fill text-primary me-2"></i> Membres Trouvés (%d)</h5>
                        </div>
                        <div class="custom-card-body">
        """, totalTrouves, motcle, membresTrouves.size()));

        if (membresTrouves.isEmpty()) {
            html.append("<p class='text-muted small mb-0'>Aucun membre correspondant.</p>");
        } else {
            html.append("<ul class='list-unstyled mb-0'>");
            for (Membre m : membresTrouves) {
                html.append(String.format("""
                    <li class="pb-2 mb-2 border-bottom d-flex justify-content-between align-items-center">
                        <div>
                            <strong>%s %s</strong><br>
                            <small class="text-muted">%s</small>
                        </div>
                        <span class="badge bg-light text-dark border">%s</span>
                    </li>
                """, m.getNom(), m.getPrenom(), m.getEmail(), m.getStatut()));
            }
            html.append("</ul>");
        }

        html.append(String.format("""
                        </div>
                    </div>
                </div>

                <!-- PUBLICATIONS -->
                <div class="col-md-6">
                    <div class="custom-card h-100">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-journal-text text-success me-2"></i> Publications Trouvées (%d)</h5>
                        </div>
                        <div class="custom-card-body">
        """, pubsTrouvees.size()));

        if (pubsTrouvees.isEmpty()) {
            html.append("<p class='text-muted small mb-0'>Aucune publication correspondante.</p>");
        } else {
            html.append("<ul class='list-unstyled mb-0'>");
            for (Publication p : pubsTrouvees) {
                html.append(String.format("""
                    <li class="pb-2 mb-2 border-bottom">
                        <div class="fw-bold text-dark small">%s</div>
                        <small class="text-muted"><i class="bi bi-person me-1"></i>%s &bull; %d (%s)</small>
                    </li>
                """, p.getTitre(), p.getAuteur(), p.getAnnee(), p.getEquipe()));
            }
            html.append("</ul>");
        }

        html.append(String.format("""
                        </div>
                    </div>
                </div>

                <!-- ÉVÉNEMENTS -->
                <div class="col-md-6">
                    <div class="custom-card h-100">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-calendar-event text-warning me-2"></i> Événements Trouvés (%d)</h5>
                        </div>
                        <div class="custom-card-body">
        """, eventsTrouves.size()));

        if (eventsTrouves.isEmpty()) {
            html.append("<p class='text-muted small mb-0'>Aucun événement correspondant.</p>");
        } else {
            html.append("<ul class='list-unstyled mb-0'>");
            for (Evenement e : eventsTrouves) {
                html.append(String.format("""
                    <li class="pb-2 mb-2 border-bottom d-flex justify-content-between align-items-center">
                        <div>
                            <strong>%s</strong><br>
                            <small class="text-muted"><i class="bi bi-clock me-1"></i>%s</small>
                        </div>
                        <span class="badge bg-warning bg-opacity-10 text-dark border">%s</span>
                    </li>
                """, e.getTitre(), e.getDate(), e.getType()));
            }
            html.append("</ul>");
        }

        html.append(String.format("""
                        </div>
                    </div>
                </div>

                <!-- DOCUMENTS -->
                <div class="col-md-6">
                    <div class="custom-card h-100">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-folder2-open text-info me-2"></i> Documents Trouvés (%d)</h5>
                        </div>
                        <div class="custom-card-body">
        """, docsTrouves.size()));

        if (docsTrouves.isEmpty()) {
            html.append("<p class='text-muted small mb-0'>Aucun document correspondant.</p>");
        } else {
            html.append("<ul class='list-unstyled mb-0'>");
            for (DocumentLabo d : docsTrouves) {
                html.append(String.format("""
                    <li class="pb-2 mb-2 border-bottom d-flex justify-content-between align-items-center">
                        <div>
                            <strong>%s</strong><br>
                            <small class="text-muted">%s &bull; %s</small>
                        </div>
                        <span class="badge bg-light text-primary border"><i class="bi bi-download me-1"></i>PDF</span>
                    </li>
                """, d.getTitre(), d.getType(), d.getDate()));
            }
            html.append("</ul>");
        }

        html.append("""
                        </div>
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
