package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lias.data.BaseMembres;
import com.lias.data.ConnexionSQLite;
import com.lias.data.InitialisationBD;
import com.lias.data.SampleData;
import com.lias.model.Membre;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouter")
public class AjouterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Ajouter un Membre", "membres"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Nouveau Membre du Laboratoire</h2>
                            <p class="text-secondary small mb-0">Enregistrement administratif d'un enseignant-chercheur ou doctorant (Cahier des Charges &sect; 3).</p>
                        </div>
                        <a href="lister" class="btn btn-outline-secondary btn-sm">
                            <i class="bi bi-arrow-left me-1"></i> Retour à la liste
                        </a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouter">
                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Nom de famille <span class="text-danger">*</span></label>
                                    <input type="text" name="nom" class="form-control-custom" placeholder="Ex: El Idrissi" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Prénom <span class="text-danger">*</span></label>
                                    <input type="text" name="prenom" class="form-control-custom" placeholder="Ex: Hassan" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Email Professionnel / Institutionnel <span class="text-danger">*</span></label>
                                <input type="email" name="email" class="form-control-custom" placeholder="Ex: h.elidrissi@univ-fsbm.ma" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Statut Officiel au Laboratoire LIAS <span class="text-danger">*</span></label>
                                <select name="statut" class="form-select-custom" required>
                                    <option value="permanent">Membre Permanent (Appartient officiellement au LIAS)</option>
                                    <option value="associe">Membre Associé (Collaborateur externe d'un autre labo)</option>
                                    <option value="doctorant">Doctorant (Thèse de doctorat en cours)</option>
                                    <option value="retraite">Retraité (Ancien membre en fin de carrière &bull; compte gelé)</option>
                                    <option value="ancien">Ancien Membre (Ayant quitté le laboratoire)</option>
                                </select>
                                <div class="form-text small text-muted">La séparation stricte entre statut et rôle de gouvernance est assurée par le système.</div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="lister" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-check2-circle me-1"></i> Enregistrer le Membre
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InitialisationBD.creerTables();
        response.setContentType("text/html;charset=UTF-8");

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String statut = request.getParameter("statut");

        if (nom != null && prenom != null && email != null) {
            // Sauvegarder dans SQLite
            try {
                Connection con = ConnexionSQLite.getConnection();
                if (con != null) {
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO membre(nom, prenom, email, statut) VALUES (?, ?, ?, ?)"
                    );
                    ps.setString(1, nom);
                    ps.setString(2, prenom);
                    ps.setString(3, email);
                    ps.setString(4, statut);
                    ps.executeUpdate();
                    ps.close();
                    con.close();
                }
            } catch (Exception ignored) {}

            // Sauvegarder dans la liste en mémoire
            BaseMembres.ajouter(new Membre(nom, prenom, email, statut));
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Membre Enregistré", "membres"));

        html.append(String.format("""
            <div class="row justify-content-center">
                <div class="col-lg-6 text-center py-5">
                    <div class="custom-card p-5">
                        <i class="bi bi-check-circle-fill text-success display-3 mb-3"></i>
                        <h3 class="fw-bold text-dark mb-2">Membre Ajouté avec Succès !</h3>
                        <p class="text-secondary small mb-4">Le chercheur <strong>%s %s</strong> (%s) a été enregistré dans le registre officiel du LIAS.</p>
                        <div class="d-flex justify-content-center gap-3">
                            <a href="ajouter" class="btn btn-outline-primary btn-sm"><i class="bi bi-plus-lg me-1"></i> Ajouter un autre</a>
                            <a href="lister" class="btn btn-primary-lias btn-sm"><i class="bi bi-list me-1"></i> Voir la liste</a>
                        </div>
                    </div>
                </div>
            </div>
        """, nom, prenom, statut));

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
