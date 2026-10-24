package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseCalendrier;
import com.lias.data.BaseEvenements;
import com.lias.data.SampleData;
import com.lias.model.CalendrierEvent;
import com.lias.model.Evenement;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerCalendrier")
public class ListerCalendrierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<CalendrierEvent> events = BaseCalendrier.getEvents();
        ArrayList<Evenement> evenements = BaseEvenements.getEvenements();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Calendrier & Agenda", "calendrier"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Calendrier Temporel du Laboratoire &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Visualisation chronologique des soutenances, conférences et réunions (Cahier des Charges &sect; 20).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterCalendrier" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-calendar-plus me-1"></i> Ajouter une date
                    </a>
                </div>
            </div>

            <div class="row g-4">
                <div class="col-lg-8">
                    <div class="custom-card p-4">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h5 class="fw-bold text-dark mb-0"><i class="bi bi-calendar3 text-primary me-2"></i> Échéancier Temporel 2025-2026</h5>
                            <span class="badge bg-light text-dark border">Vue Mensuelle</span>
                        </div>

                        <div class="list-group list-group-flush">
        """);

        for (CalendrierEvent ce : events) {
            html.append(String.format("""
                <div class="list-group-item px-0 py-3 d-flex align-items-center gap-3">
                    <div class="bg-primary bg-opacity-10 text-primary p-3 rounded-3 text-center" style="min-width: 90px;">
                        <span class="fw-bold small text-uppercase">%s</span>
                    </div>
                    <div class="flex-grow-1">
                        <h6 class="fw-bold text-dark mb-1">%s</h6>
                        <p class="text-muted small mb-0"><i class="bi bi-geo-alt text-danger me-1"></i>%s</p>
                    </div>
                    <span class="badge bg-primary bg-opacity-10 text-primary">Calendrier</span>
                </div>
            """, ce.getDate(), ce.getTitre(), ce.getDescription()));
        }

        for (Evenement ev : evenements) {
            html.append(String.format("""
                <div class="list-group-item px-0 py-3 d-flex align-items-center gap-3">
                    <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-3 text-center" style="min-width: 90px;">
                        <span class="fw-bold small text-uppercase">%s</span>
                    </div>
                    <div class="flex-grow-1">
                        <span class="badge bg-light text-secondary border small mb-1">%s</span>
                        <h6 class="fw-bold text-dark mb-1">%s</h6>
                        <p class="text-muted small mb-0">Faculté des Sciences Ben M'Sik &bull; LIAS</p>
                    </div>
                    <span class="badge bg-success bg-opacity-10 text-success">Scientifique</span>
                </div>
            """, ev.getDate(), ev.getType(), ev.getTitre()));
        }

        html.append("""
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="custom-card p-4 bg-light border-0">
                        <h6 class="fw-bold text-dark mb-3"><i class="bi bi-info-circle text-primary me-2"></i> Navigation Temporelle</h6>
                        <p class="small text-muted">Ce module garantit la traçabilité temporelle des activités du laboratoire et permet d'anticiper la production des rapports périodiques.</p>
                        <a href="listerEvenements" class="btn btn-outline-primary btn-sm w-100 mb-2">Gérer les événements</a>
                        <a href="listerRapports" class="btn btn-outline-secondary btn-sm w-100">Bilan d'activité</a>
                    </div>
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
