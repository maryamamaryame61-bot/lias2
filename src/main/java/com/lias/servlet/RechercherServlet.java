package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

@WebServlet("/rechercher")
public class RechercherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RechercherServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Rechercher un Membre", "membres"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-7">
                    <div class="text-center mb-4">
                        <h2 class="fw-bold text-dark mb-1">Recherche de Profil Membre</h2>
                        <p class="text-secondary small">Consultation des fiches des membres par email ou nom (Cahier des Charges &sect; 3).</p>
                    </div>

                    <div class="custom-card p-4 p-md-5 mb-4 shadow-sm">
                        <form method="post" action="rechercher">
                            <div class="mb-3">
                                <label class="form-label-custom">Email du membre</label>
                                <div class="input-group">
                                    <span class="input-group-text bg-white"><i class="bi bi-search"></i></span>
                                    <input type="email" name="email" class="form-control" placeholder="Ex: h.elidrissi@univ-fsbm.ma" required>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                <a href="lister" class="btn btn-outline-secondary">Voir tous les membres</a>
                                <button type="submit" class="btn btn-primary-lias px-4">Consulter la fiche</button>
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

        String emailRecherche = request.getParameter("email");
        if (emailRecherche == null) emailRecherche = "";
        emailRecherche = emailRecherche.trim();

        response.setContentType("text/html;charset=UTF-8");

        Membre membreTrouve = null;

        // Chercher en mémoire
        for (Membre m : BaseMembres.getMembres()) {
            if (m.getEmail().equalsIgnoreCase(emailRecherche)) {
                membreTrouve = m;
                break;
            }
        }

        // Si non trouvé, chercher dans SQLite
        if (membreTrouve == null) {
            try {
                Connection con = ConnexionSQLite.getConnection();
                if (con != null) {
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM membre WHERE email = ?");
                    ps.setString(1, emailRecherche);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        membreTrouve = new Membre(
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("statut")
                        );
                    }
                    rs.close();
                    ps.close();
                    con.close();
                }
            } catch (Exception ignored) {}
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Fiche Membre", "membres"));

        if (membreTrouve != null) {
            html.append(String.format("""
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="custom-card p-4 p-md-5 mb-4">
                            <div class="text-center mb-4 pb-3 border-bottom">
                                <img src="images/avatar-default.svg" alt="Avatar" width="80" height="80" class="rounded-circle mb-3 border p-1">
                                <h3 class="fw-bold text-dark mb-1">%s %s</h3>
                                <div class="badge bg-primary px-3 py-2 fs-6 mb-2">%s</div>
                                <div class="text-muted small"><i class="bi bi-envelope me-1"></i>%s</div>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-sm-6">
                                    <div class="p-3 bg-light rounded border">
                                        <small class="text-muted d-block">Établissement de rattachement</small>
                                        <strong>Faculté des Sciences Ben M'Sik</strong>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="p-3 bg-light rounded border">
                                        <small class="text-muted d-block">Laboratoire</small>
                                        <strong>Laboratoire LIAS (Casablanca)</strong>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="rechercher" class="btn btn-outline-secondary"><i class="bi bi-search me-1"></i> Autre recherche</a>
                                <div class="btn-group">
                                    <a href="modifier?email=%s" class="btn btn-outline-primary"><i class="bi bi-pencil me-1"></i> Modifier</a>
                                    <a href="lister" class="btn btn-primary-lias">Liste des membres</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            """, membreTrouve.getNom(), membreTrouve.getPrenom(), membreTrouve.getStatut(), membreTrouve.getEmail(), membreTrouve.getEmail()));
        } else {
            html.append(String.format("""
                <div class="row justify-content-center">
                    <div class="col-lg-6 text-center py-5">
                        <div class="custom-card p-5">
                            <i class="bi bi-exclamation-circle text-warning display-3 mb-3"></i>
                            <h4 class="fw-bold text-dark mb-2">Membre Non Trouvé</h4>
                            <p class="text-secondary small mb-4">Aucun enregistrement ne correspond à l'email : <strong>%s</strong></p>
                            <div class="d-flex justify-content-center gap-3">
                                <a href="rechercher" class="btn btn-outline-secondary btn-sm"><i class="bi bi-search me-1"></i> Réessayer</a>
                                <a href="lister" class="btn btn-primary-lias btn-sm"><i class="bi bi-list me-1"></i> Voir tous les membres</a>
                            </div>
                        </div>
                    </div>
                </div>
            """, emailRecherche));
        }

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
