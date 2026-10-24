package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseEvenements;
import com.lias.data.SampleData;
import com.lias.model.Evenement;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerEvenements")
public class ListerEvenementsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerEvenementsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Evenement> evenements = BaseEvenements.getEvenements();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Gestion des Événements", "evenements"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Événements Scientifiques &amp; Congrès &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Conférences, séminaires doctoraux, workshops et colloques internationaux (Cahier des Charges &sect; 9).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterEvenement" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-calendar-plus me-1"></i> Planifier un événement
                    </a>
                    <a href="listerCalendrier" class="btn btn-outline-secondary btn-sm">
                        <i class="bi bi-calendar3 me-1"></i> Vue Calendrier
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <div class="position-relative flex-grow-1 me-3" style="max-width: 400px;">
                        <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                        <input type="text" class="form-control form-control-sm ps-5 table-search-input" data-table="eventsTable" placeholder="Recherche par titre, type, date...">
                    </div>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(evenements.size()).append(" événements programmés</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0" id="eventsTable">
                        <thead>
                            <tr>
                                <th style="width: 50%;">Titre de la Manifestation</th>
                                <th>Typologie</th>
                                <th>Date Programmée</th>
                                <th>Lieu &amp; Cadre</th>
                                <th class="text-end">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (evenements.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucun événement enregistré.</td></tr>");
        } else {
            for (Evenement e : evenements) {
                String badgeClass = "bg-primary";
                if ("Workshop".equalsIgnoreCase(e.getType())) badgeClass = "bg-success";
                else if ("Séminaire".equalsIgnoreCase(e.getType())) badgeClass = "bg-warning text-dark";

                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-calendar-event text-primary me-2"></i>%s</strong><br>
                            <small class="text-muted">Laboratoire LIAS &bull; FSBM Casablanca</small>
                        </td>
                        <td>
                            <span class="badge %s px-3 py-1">%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-clock me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border"><i class="bi bi-geo-alt me-1 text-danger"></i>Faculté des Sciences</span>
                        </td>
                        <td class="text-end">
                            <a href="listerCalendrier" class="btn btn-sm btn-outline-primary">
                                <i class="bi bi-calendar3 me-1"></i> Calendrier
                            </a>
                        </td>
                    </tr>
                """, e.getTitre(), badgeClass, e.getType(), e.getDate()));
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
