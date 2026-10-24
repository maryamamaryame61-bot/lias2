package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lias.data.BaseMembres;
import com.lias.data.ConnexionSQLite;
import com.lias.data.SampleData;
import com.lias.model.Membre;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifier")
public class ModifierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModifierServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        String emailParam = request.getParameter("email");
        if (emailParam == null) emailParam = "";

        // Trouver les infos si existant
        String currentNom = "";
        String currentPrenom = "";
        String currentStatut = "permanent";
        for (Membre m : BaseMembres.getMembres()) {
            if (m.getEmail().equalsIgnoreCase(emailParam)) {
                currentNom = m.getNom();
                currentPrenom = m.getPrenom();
                currentStatut = m.getStatut();
                break;
            }
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Modifier un Membre", "membres"));

        html.append(String.format("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Mise à Jour du Profil Membre</h2>
                            <p class="text-secondary small mb-0">Modification libre du profil et historisation (Cahier des Charges &sect; 3).</p>
                        </div>
                        <a href="lister" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Retour</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="modifier">
                            <div class="mb-3">
                                <label class="form-label-custom">Email du membre à modifier <span class="text-danger">*</span></label>
                                <input type="email" name="email" class="form-control-custom" value="%s" required>
                                <div class="form-text small">Clé d'identification du membre dans le système.</div>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Nouveau Nom</label>
                                    <input type="text" name="nom" class="form-control-custom" value="%s" placeholder="Nom de famille" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Nouveau Prénom</label>
                                    <input type="text" name="prenom" class="form-control-custom" value="%s" placeholder="Prénom" required>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Nouveau Statut</label>
                                <select name="statut" class="form-select-custom">
                                    <option value="permanent" %s>Permanent</option>
                                    <option value="associe" %s>Associé</option>
                                    <option value="doctorant" %s>Doctorant</option>
                                    <option value="retraite" %s>Retraité (Compte gelé)</option>
                                    <option value="ancien" %s>Ancien Membre (Compte désactivé)</option>
                                </select>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="lister" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-save me-1"></i> Enregistrer les modifications
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        """,
            emailParam, currentNom, currentPrenom,
            "permanent".equalsIgnoreCase(currentStatut) ? "selected" : "",
            "associe".equalsIgnoreCase(currentStatut) ? "selected" : "",
            "doctorant".equalsIgnoreCase(currentStatut) ? "selected" : "",
            "retraite".equalsIgnoreCase(currentStatut) ? "selected" : "",
            "ancien".equalsIgnoreCase(currentStatut) ? "selected" : ""
        ));

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String statut = request.getParameter("statut");

        // Mise à jour SQLite
        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                    "UPDATE membre SET nom = ?, prenom = ?, statut = ? WHERE email = ?"
                );
                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, statut);
                ps.setString(4, email);
                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception ignored) {}

        // Mise à jour en mémoire
        for (Membre m : BaseMembres.getMembres()) {
            if (m.getEmail().equalsIgnoreCase(email)) {
                m.setNom(nom);
                m.setPrenom(prenom);
                m.setStatut(statut);
                break;
            }
        }

        response.sendRedirect("lister");
    }
}
