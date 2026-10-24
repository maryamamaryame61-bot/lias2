package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseCalendrier;
import com.lias.data.SampleData;
import com.lias.model.CalendrierEvent;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterCalendrier")
public class AjouterCalendrierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Ajouter au Calendrier", "calendrier"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Ajouter une Date au Calendrier</h2>
                            <p class="text-secondary small mb-0">Planification des soutenances, séminaires et échéances du laboratoire (Cahier des Charges &sect; 20).</p>
                        </div>
                        <a href="listerCalendrier" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Calendrier</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterCalendrier">
                            <div class="mb-3">
                                <label class="form-label-custom">Intitulé de l'Événement ou de l'Échéance <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Soutenance de thèse Doctorat DSBD" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Date de l'événement <span class="text-danger">*</span></label>
                                <input type="date" name="date" class="form-control-custom" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Description &amp; Lieu / Salle <span class="text-danger">*</span></label>
                                <textarea name="description" rows="3" class="form-control-custom" placeholder="Ex: Amphithéâtre 1, FSBM Casablanca - Présentation des travaux de recherche..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerCalendrier" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-calendar-plus me-1"></i> Planifier la date
                                </button>
                            </div>
                        </form>
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

        String titre = request.getParameter("titre");
        String date = request.getParameter("date");
        String description = request.getParameter("description");

        if (titre != null && date != null) {
            BaseCalendrier.ajouter(new CalendrierEvent(titre, date, description));
        }

        response.sendRedirect("listerCalendrier");
    }
}
