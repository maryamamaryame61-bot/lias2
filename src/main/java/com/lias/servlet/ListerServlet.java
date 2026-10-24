package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

@WebServlet("/lister")
public class ListerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Membre> liste = new ArrayList<>();

        // Lire depuis SQLite
        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM membre");
                while (rs.next()) {
                    liste.add(new Membre(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("statut")
                    ));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception ignored) {}

        // Si la base SQLite est vide, utiliser la base en mémoire
        if (liste.isEmpty()) {
            liste.addAll(BaseMembres.getMembres());
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Gestion des Membres", "membres"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Gestion des Membres &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Cycle de vie des chercheurs, statuts (permanent, associé, doctorant, retraité) et historisation.</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouter" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-person-plus-fill me-1"></i> Ajouter un membre
                    </a>
                    <a href="rechercher" class="btn btn-outline-secondary btn-sm">
                        <i class="bi bi-search me-1"></i> Rechercher
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <div class="position-relative flex-grow-1 me-3" style="max-width: 400px;">
                        <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                        <input type="text" class="form-control form-control-sm ps-5 table-search-input" data-table="membresTable" placeholder="Filtrer en temps réel (nom, email, statut)...">
                    </div>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(liste.size()).append(" membres enregistrés</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle" id="membresTable">
                        <thead>
                            <tr>
                                <th>Membre (Nom & Prénom)</th>
                                <th>Email Professionnel</th>
                                <th>Statut LIAS</th>
                                <th>État du Compte</th>
                                <th class="text-end">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (liste.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-4'>Aucun membre trouvé.</td></tr>");
        } else {
            for (Membre m : liste) {
                String statut = m.getStatut() != null ? m.getStatut() : "permanent";
                String badgeClass = "badge-permanent";
                String statutNom = "Permanent";

                if (statut.equalsIgnoreCase("associe") || statut.equalsIgnoreCase("associé")) {
                    badgeClass = "badge-associe";
                    statutNom = "Associé";
                } else if (statut.equalsIgnoreCase("doctorant")) {
                    badgeClass = "badge-doctorant";
                    statutNom = "Doctorant";
                } else if (statut.equalsIgnoreCase("retraite") || statut.equalsIgnoreCase("retraité")) {
                    badgeClass = "badge-retraite";
                    statutNom = "Retraité";
                } else if (statut.equalsIgnoreCase("ancien")) {
                    badgeClass = "badge-ancien";
                    statutNom = "Ancien Membre";
                }

                boolean isGele = statut.equalsIgnoreCase("retraite") || statut.equalsIgnoreCase("retraité");

                html.append(String.format("""
                    <tr>
                        <td>
                            <div class="d-flex align-items-center gap-2">
                                <img src="images/avatar-default.svg" alt="Avatar" width="36" height="36" class="rounded-circle">
                                <div>
                                    <strong class="text-dark">%s %s</strong><br>
                                    <small class="text-muted">Laboratoire LIAS</small>
                                </div>
                            </div>
                        </td>
                        <td>
                            <a href="mailto:%s" class="text-primary text-decoration-none">%s</a>
                        </td>
                        <td>
                            <span class="badge-status %s">%s</span>
                        </td>
                        <td>
                            %s
                        </td>
                        <td class="text-end">
                            <div class="btn-group btn-group-sm">
                                <a href="modifier?email=%s" class="btn btn-outline-primary" title="Modifier le membre"><i class="bi bi-pencil"></i></a>
                                <a href="supprimer?email=%s" class="btn btn-outline-danger btn-confirm-delete" data-msg="Voulez-vous supprimer ce membre ?" title="Supprimer"><i class="bi bi-trash"></i></a>
                            </div>
                        </td>
                    </tr>
                """,
                    m.getNom(), m.getPrenom(),
                    m.getEmail(), m.getEmail(),
                    badgeClass, statutNom,
                    isGele ? "<span class='badge bg-secondary'>Compte Gelé (&sect;8.1)</span>" : "<span class='badge bg-success bg-opacity-10 text-success border border-success border-opacity-25'>Actif</span>",
                    m.getEmail(), m.getEmail()
                ));
            }
        }

        html.append("""
                        </tbody>
                    </table>
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
