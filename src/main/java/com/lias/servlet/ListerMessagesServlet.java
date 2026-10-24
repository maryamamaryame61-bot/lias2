package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseMessages;
import com.lias.data.SampleData;
import com.lias.model.MessageInterne;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerMessages")
public class ListerMessagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerMessagesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<MessageInterne> messages = BaseMessages.getMessages();

        // Données d'exemple si vide
        if (messages.isEmpty()) {
            BaseMessages.ajouter(new MessageInterne("Pr. Hassan El Idrissi", "Équipe SDIA", "Rappel : la soumission des résumés pour la Journée Big Data est fixée au 15 avril."));
            BaseMessages.ajouter(new MessageInterne("Dr. Fatima Zahra Bennani", "Doctorants LIAS", "Les nouvelles stations de calcul sont prêtes et configurées avec Docker et CUDA."));
            messages = BaseMessages.getMessages();
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Communication Interne", "messages"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Communication Interne &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Discussions entre membres, échanges d'équipe et annonces scientifiques (Cahier des Charges &sect; 13).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterMessage" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-chat-left-dots me-1"></i> Nouveau message
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-chat-square-text text-primary me-2"></i> Fil de Discussions &amp; Échanges</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(messages.size()).append(" message(s)</span>\n</div>\n");

        html.append("<div class=\"list-group list-group-flush\">\n");

        for (MessageInterne m : messages) {
            html.append(String.format("""
                <div class="list-group-item p-4">
                    <div class="d-flex justify-content-between align-items-center mb-2">
                        <div class="d-flex align-items-center gap-2">
                            <i class="bi bi-person-circle fs-4 text-primary"></i>
                            <div>
                                <strong class="text-dark">%s</strong>
                                <span class="text-muted small">&rarr; Destinataire : <span class="badge bg-light text-dark border">%s</span></span>
                            </div>
                        </div>
                        <span class="badge bg-light text-secondary border small">Message Récent</span>
                    </div>
                    <div class="p-3 bg-light rounded-3 text-secondary small">
                        %s
                    </div>
                </div>
            """, m.getExpediteur(), m.getDestinataire(), m.getContenu()));
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
