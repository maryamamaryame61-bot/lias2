package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseEquipes;
import com.lias.data.SampleData;
import com.lias.model.Equipe;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterEquipe")
public class AjouterEquipeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterEquipeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Créer une Équipe", "equipes"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Créer une Équipe de Recherche</h2>
                            <p class="text-secondary small mb-0">Définition des structures scientifiques et axes thématiques du LIAS.</p>
                        </div>
                        <a href="listerEquipes" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Équipes</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterEquipe">
                            <div class="mb-3">
                                <label class="form-label-custom">Nom de l'Équipe <span class="text-danger">*</span></label>
                                <input type="text" name="nom" class="form-control-custom" placeholder="Ex: Science des Données &amp; IA (SDIA)" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Responsable / Chef d'Équipe <span class="text-danger">*</span></label>
                                <input type="text" name="responsable" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Domaine &amp; Mots-Clés de Recherche <span class="text-danger">*</span></label>
                                <textarea name="domaine" rows="3" class="form-control-custom" placeholder="Ex: Machine Learning, Deep Learning, Big Data, Vision par ordinateur, Traitement du signal..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerEquipes" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-diagram-3 me-1"></i> Créer l'équipe
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
        String nom = request.getParameter("nom");
        String responsable = request.getParameter("responsable");
        String domaine = request.getParameter("domaine");

        if (nom != null && responsable != null) {
            BaseEquipes.ajouter(new Equipe(nom, responsable, domaine));
        }

        response.sendRedirect("listerEquipes");
    }
}
