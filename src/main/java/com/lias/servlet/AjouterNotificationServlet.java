package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseNotifications;
import com.lias.data.SampleData;
import com.lias.model.Notification;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterNotification")
public class AjouterNotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Émettre une Notification", "notifications"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Diffuser une Notification &bull; LIAS</h2>
                            <p class="text-secondary small mb-0">Alertes sur les événements, validation de demandes et arrivages (Cahier des Charges &sect; 19).</p>
                        </div>
                        <a href="listerNotifications" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Alertes</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterNotification">
                            <div class="mb-3">
                                <label class="form-label-custom">Titre de la Notification <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Arrivage nouveaux équipements de calcul GPU" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Message / Détail de l'Alerte <span class="text-danger">*</span></label>
                                <textarea name="message" rows="4" class="form-control-custom" placeholder="Texte de l'annonce diffusée aux membres du laboratoire..." required></textarea>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Date de Diffusion <span class="text-danger">*</span></label>
                                <input type="date" name="date" class="form-control-custom" required>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerNotifications" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-bell me-1"></i> Diffuser la notification
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
        String message = request.getParameter("message");
        String date = request.getParameter("date");

        if (titre != null && message != null) {
            BaseNotifications.ajouter(new Notification(titre, message, date));
        }

        response.sendRedirect("listerNotifications");
    }
}
