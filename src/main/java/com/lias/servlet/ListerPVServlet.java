package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BasePV;
import com.lias.data.SampleData;
import com.lias.model.PV;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerPV")
public class ListerPVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerPVServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<PV> pvs = BasePV.getPvs();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Procès-Verbaux de Réunion (PV)", "pv"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Procès-Verbaux (PV) de Réunion &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Comptes-rendus des réunions de conseil de labo et décisions officielles (Cahier des Charges &sect; 17).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterPV" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-file-earmark-plus me-1"></i> Rédiger / Upload un PV
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-file-earmark-text text-primary me-2"></i> Archives des Délibérations &amp; Ordres du Jour</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(pvs.size()).append(" procès-verbaux enregistrés</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th style="width: 35%;">Réunion / Instance</th>
                                <th>Date de Tenue</th>
                                <th style="width: 40%;">Ordre du Jour &amp; Points Débattus</th>
                                <th class="text-end">Document</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (pvs.isEmpty()) {
            html.append("<tr><td colspan='4' class='text-center text-muted py-5'>Aucun PV archivé pour le moment.</td></tr>");
        } else {
            for (PV pv : pvs) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-journal-text text-primary me-2"></i>%s</strong><br>
                            <small class="text-muted">Conseil du Laboratoire LIAS</small>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar3 me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="small text-secondary">%s</span>
                        </td>
                        <td class="text-end">
                            <button class="btn btn-sm btn-outline-primary" onclick="alert('Téléchargement du PV officiel : %s');">
                                <i class="bi bi-download me-1"></i> PV PDF
                            </button>
                        </td>
                    </tr>
                """, pv.getReunion(), pv.getDate(), pv.getOrdreJour(), pv.getReunion().replace("'", "\\'")));
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
