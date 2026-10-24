package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseMessages;
import com.lias.data.SampleData;
import com.lias.model.MessageInterne;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterMessage")
public class AjouterMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterMessageServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Nouveau Message Interne", "messages"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Envoyer un Message Interne</h2>
                            <p class="text-secondary small mb-0">Discussions entre membres ou annonces d'équipe (Cahier des Charges &sect; 13).</p>
                        </div>
                        <a href="listerMessages" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Messages</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterMessage">
                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Expéditeur (Votre nom) <span class="text-danger">*</span></label>
                                    <input type="text" name="expediteur" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Destinataire (Équipe ou Membre) <span class="text-danger">*</span></label>
                                    <input type="text" name="destinataire" class="form-control-custom" placeholder="Ex: Tous les doctorants ou Équipe SDIA" required>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Contenu du Message <span class="text-danger">*</span></label>
                                <textarea name="contenu" rows="4" class="form-control-custom" placeholder="Votre message ou annonce interne..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerMessages" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-send me-1"></i> Envoyer le message
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String expediteur = request.getParameter("expediteur");
        String destinataire = request.getParameter("destinataire");
        String contenu = request.getParameter("contenu");

        if (expediteur != null && contenu != null) {
            BaseMessages.ajouter(new MessageInterne(expediteur, destinataire, contenu));
        }

        response.sendRedirect("listerMessages");
    }
}
