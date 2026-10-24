package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseAffiliations;
import com.lias.data.SampleData;
import com.lias.model.Affiliation;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerAffiliations")
public class ListerAffiliationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Affiliation> affiliations = BaseAffiliations.getAffiliations();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Historique des Affiliations", "affiliations"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Historique des Affiliations Temporelles &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Traçabilité des entrées, départs et réintégrations des membres (Cahier des Charges &sect; 4.3).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterAffiliation" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Nouvelle Période
                    </a>
                </div>
            </div>

            <div class="alert alert-secondary border-0 shadow-sm d-flex align-items-center gap-3 mb-4">
                <i class="bi bi-clock-history fs-3 text-primary"></i>
                <div class="small">
                    <strong>Règle Temporelle (&sect;4.3 &amp; &sect;21) :</strong> Un membre peut quitter le laboratoire et revenir. Chaque période est historisée sans perte de données antérieures.
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-calendar-range text-primary me-2"></i> Périodes d'Affiliation Historisées</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(affiliations.size()).append(" périodes enregistrées</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Membre Concerné</th>
                                <th>Équipe d'Attache</th>
                                <th>Date de Début</th>
                                <th>Date de Fin</th>
                                <th>État de la Période</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (affiliations.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucune affiliation enregistrée.</td></tr>");
        } else {
            for (Affiliation a : affiliations) {
                boolean isEnCours = "En cours".equalsIgnoreCase(a.getDateFin()) || a.getDateFin() == null || a.getDateFin().isBlank();
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-person-fill text-muted me-1"></i>%s</strong>
                        </td>
                        <td>
                            <span class="badge bg-info bg-opacity-10 text-info fs-6 px-3 py-1">%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar-plus me-1 text-success"></i>%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar-minus me-1 text-danger"></i>%s</span>
                        </td>
                        <td>
                            %s
                        </td>
                    </tr>
                """, a.getMembre(), a.getEquipe(), a.getDateDebut(), a.getDateFin(),
                    isEnCours ? "<span class='badge-status badge-accepte'>Affiliation Active</span>" : "<span class='badge-status badge-retraite'>Période Clôturée</span>"
                ));
            }
        }

        html.append("""
                        </tbody>
                    </table>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
