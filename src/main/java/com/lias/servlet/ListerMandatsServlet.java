package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseMandats;
import com.lias.data.SampleData;
import com.lias.model.Mandat;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerMandats")
public class ListerMandatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Mandat> mandats = BaseMandats.getMandats();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Mandats de Gouvernance", "mandats"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Gouvernance &amp; Mandats &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Périodes de gouvernance élective et historisation des responsabilités (Cahier des Charges &sect; 5.3).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterMandat" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Nouveau Mandat
                    </a>
                </div>
            </div>

            <div class="alert alert-primary border-0 shadow-sm d-flex align-items-center gap-3 mb-4">
                <i class="bi bi-award-fill fs-3 text-primary"></i>
                <div class="small">
                    <strong>Règle Institutionnelle (&sect;5.2 &amp; &sect;5.3) :</strong> Le Directeur en mandat actif est le responsable métier suprême. Le rôle est évolutif selon les mandats et l'historique complet est indélébile.
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-journal-bookmark text-primary me-2"></i> Historique des Mandats de Direction</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(mandats.size()).append(" mandat(s) archivé(s)</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Directeur du Laboratoire</th>
                                <th>Vice-Directeur(trice)</th>
                                <th>Chef(s) d'Équipe Clés</th>
                                <th>Période Officielle</th>
                                <th>Statut du Mandat</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (mandats.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucun mandat enregistré.</td></tr>");
        } else {
            for (Mandat m : mandats) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-person-fill text-primary me-1"></i>%s</strong><br>
                            <span class="badge bg-primary bg-opacity-10 text-primary small">Mandat Actif</span>
                        </td>
                        <td>
                            <span class="text-dark fw-semibold"><i class="bi bi-person me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="text-secondary small">%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border"><i class="bi bi-calendar-range me-1"></i>%s &rarr; %s</span>
                        </td>
                        <td>
                            <span class="badge-status badge-accepte">En cours</span>
                        </td>
                    </tr>
                """, m.getDirecteur(), m.getViceDirecteur(), m.getChefEquipe(), m.getDateDebut(), m.getDateFin()));
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
