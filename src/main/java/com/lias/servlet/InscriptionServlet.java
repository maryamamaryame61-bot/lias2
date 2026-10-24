package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lias.data.ConnexionSQLite;
import com.lias.data.InitialisationBD;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("""
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer un compte | Laboratoire LIAS - FSBM Casablanca</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: linear-gradient(135deg, #07253d 0%, #0b3b60 50%, #155e96 100%);
            padding: 20px;
        }
        .register-card {
            width: 100%%;
            max-width: 480px;
            background: #ffffff;
            border-radius: 18px;
            padding: 40px;
            box-shadow: 0 20px 45px rgba(0,0,0,0.25);
        }
    </style>
</head>
<body>
    <div class="register-card">
        <div class="text-center mb-4">
            <a href="index.jsp">
                <img src="images/lias-logo.svg" alt="LIAS Logo" height="48" class="mb-3">
            </a>
            <h4 class="fw-bold text-dark mb-1">Créer un compte</h4>
            <p class="text-muted small">Inscription pour les membres du Laboratoire LIAS</p>
        </div>

        <form method="post" action="inscription">
            <div class="mb-3">
                <label class="form-label small fw-semibold text-secondary">Identifiant souhaité <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text bg-light text-muted"><i class="bi bi-person"></i></span>
                    <input type="text" name="login" class="form-control" placeholder="Ex: f.bennani" required>
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label small fw-semibold text-secondary">Mot de passe <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text bg-light text-muted"><i class="bi bi-key"></i></span>
                    <input type="password" name="password" class="form-control" placeholder="Mot de passe sécurisé" required>
                </div>
            </div>

            <div class="mb-4">
                <label class="form-label small fw-semibold text-secondary">Confirmer le mot de passe <span class="text-danger">*</span></label>
                <div class="input-group">
                    <span class="input-group-text bg-light text-muted"><i class="bi bi-shield-check"></i></span>
                    <input type="password" name="confirm" class="form-control" placeholder="Retapez le mot de passe" required>
                </div>
            </div>

            <button type="submit" class="btn btn-primary-lias w-100 py-2 mb-3">
                <i class="bi bi-check2-circle me-1"></i> Créer mon compte
            </button>

            <div class="d-flex justify-content-between align-items-center small">
                <a href="authentification" class="text-decoration-none text-primary fw-semibold">Déjà un compte ? Se connecter</a>
                <a href="index.jsp" class="text-decoration-none text-secondary"><i class="bi bi-arrow-left"></i> Retour</a>
            </div>
        </form>
    </div>
</body>
</html>
""");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        response.setContentType("text/html;charset=UTF-8");

        if (!password.equals(confirm)) {
            renderMessage(response, false, "Erreur de mot de passe", "Les deux mots de passe saisis ne correspondent pas.", "inscription", "Réessayer");
            return;
        }

        try {
            InitialisationBD.creerTables();
            Connection con = ConnexionSQLite.getConnection();

            if (con != null) {
                PreparedStatement check = con.prepareStatement(
                        "SELECT * FROM utilisateur WHERE login=?");
                check.setString(1, login);
                ResultSet rs = check.executeQuery();

                if (rs.next()) {
                    rs.close();
                    check.close();
                    con.close();
                    renderMessage(response, false, "Identifiant déjà utilisé", "Le login <strong>" + login + "</strong> est déjà attribué. Veuillez en choisir un autre.", "inscription", "Choisir un autre login");
                    return;
                }

                rs.close();
                check.close();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO utilisateur(login,password,role) VALUES(?,?,?)");
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, "chercheur");
                ps.executeUpdate();
                ps.close();
                con.close();

                renderMessage(response, true, "Compte créé avec succès !", "Votre compte a été enregistré dans le système LIAS. Vous pouvez maintenant vous connecter.", "authentification", "Accéder à la connexion");
            } else {
                renderMessage(response, true, "Compte simulé avec succès !", "Votre compte <strong>" + login + "</strong> a été pris en compte pour la session.", "authentification", "Se connecter");
            }

        } catch (Exception e) {
            renderMessage(response, false, "Erreur système", "Une erreur est survenue lors de l'enregistrement : " + e.getMessage(), "inscription", "Retour au formulaire");
        }
    }

    private void renderMessage(HttpServletResponse response, boolean success, String title, String message, String returnUrl, String returnLabel) throws IOException {
        response.getWriter().println("""
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>LIAS - Notification</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/style.css">
    <style>
        body { min-height: 100vh; display: flex; align-items: center; justify-content: center; background: #f8fafc; padding: 20px; }
        .card-box { max-width: 460px; width: 100%%; background: #fff; border-radius: 16px; padding: 35px; box-shadow: 0 10px 30px rgba(0,0,0,0.08); text-align: center; }
    </style>
</head>
<body>
    <div class="card-box">
        <div class="mb-3">
            <i class="bi %%s display-3 %%s"></i>
        </div>
        <h4 class="fw-bold text-dark mb-2">%%s</h4>
        <p class="text-secondary small mb-4">%%s</p>
        <a href="%%s" class="btn %%s px-4 py-2">%%s</a>
    </div>
</body>
</html>
""".formatted(
            success ? "bi-check-circle-fill" : "bi-exclamation-triangle-fill",
            success ? "text-success" : "text-danger",
            title,
            message,
            returnUrl,
            success ? "btn-primary-lias" : "btn-outline-danger",
            returnLabel
        ));
    }
}