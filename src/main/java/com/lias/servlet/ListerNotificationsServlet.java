package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseNotifications;
import com.lias.data.SampleData;
import com.lias.model.Notification;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerNotifications")
public class ListerNotificationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Notification> notifications = BaseNotifications.getNotifications();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Centre de Notifications", "notifications"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Centre de Notifications &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Alertes sur les événements, arrivages de matériel et candidatures (Cahier des Charges &sect; 19).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterNotification" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-bell-plus me-1"></i> Émettre une notification
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-bell-fill text-warning me-2"></i> Journal des Notifications Récentes</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(notifications.size()).append(" alerte(s)</span>\n</div>\n");

        html.append("<div class=\"list-group list-group-flush\">\n");

        if (notifications.isEmpty()) {
            html.append("<div class='p-4 text-center text-muted'>Aucune notification enregistrée.</div>");
        } else {
            for (Notification n : notifications) {
                html.append(String.format("""
                    <div class="list-group-item p-3 d-flex align-items-center gap-3">
                        <div class="bg-warning bg-opacity-10 text-warning p-3 rounded-circle">
                            <i class="bi bi-bell fs-5"></i>
                        </div>
                        <div class="flex-grow-1">
                            <div class="d-flex justify-content-between align-items-center mb-1">
                                <h6 class="fw-bold text-dark mb-0">%s</h6>
                                <small class="text-muted"><i class="bi bi-clock me-1"></i>%s</small>
                            </div>
                            <p class="text-secondary small mb-0">%s</p>
                        </div>
                    </div>
                """, n.getTitre(), n.getDate(), n.getMessage()));
            }
        }

        html.append("""
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
