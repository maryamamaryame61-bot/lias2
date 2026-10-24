package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lias.data.ConnexionSQLite;
import com.lias.data.InitialisationBD;
import com.lias.data.SampleData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/authentification")
public class AuthentificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        String error = request.getParameter("error");
        String contextPath = request.getContextPath();

        response.getWriter().println("""
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion | Laboratoire LIAS - FSBM Casablanca</title>
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
        .login-card {
            width: 100%;
            max-width: 440px;
            background: #ffffff;
            border-radius: 18px;
            padding: 40px;
            box-shadow: 0 20px 45px rgba(0,0,0,0.25);
        }
    </style>
</head>
<body>
    <div class="login-card">
        <div class="text-center mb-4">
            <a href="index.jsp">
                <img src="images/lias-logo.svg" alt="LIAS Logo" height="48" class="mb-3">
            </a>
            <h4 class="fw-bold text-dark mb-1">Espace Membre</h4>
            <p class="text-muted small">Portail de gestion scientifique &bull; FSBM Casablanca</p>
        </div>
""");

        if (error != null) {
            response.getWriter().println("""
        <div class="alert alert-danger d-flex align-items-center gap-2 small py-2 mb-3">
            <i class="bi bi-exclamation-triangle-fill"></i>
            <div>Identifiant ou mot de passe incorrect.</div>
        </div>
""");
        }

        response.getWriter().println("""
        <form method="post" action="authentification">
            <div class="mb-3">
                <label class="form-label small fw-semibold text-secondary">Identifiant / Login</label>
                <div class="input-group">
                    <span class="input-group-text bg-light text-muted"><i class="bi bi-person"></i></span>
                    <input type="text" name="login" class="form-control" placeholder="Ex: admin ou votre email" required value="admin">
                </div>
            </div>

            <div class="mb-3">
                <label class="form-label small fw-semibold text-secondary">Mot de passe</label>
                <div class="input-group">
                    <span class="input-group-text bg-light text-muted"><i class="bi bi-key"></i></span>
                    <input type="password" name="password" class="form-control" placeholder="Mot de passe" required value="admin123">
                </div>
            </div>

            <div class="p-2 mb-3 bg-light rounded border small text-muted">
                <i class="bi bi-info-circle text-primary me-1"></i> <strong>Accès démonstration :</strong><br>
                Login: <code>admin</code> &bull; Mot de passe: <code>admin123</code>
            </div>

            <button type="submit" class="btn btn-primary-lias w-100 py-2 mb-3">
                <i class="bi bi-box-arrow-in-right me-1"></i> Se connecter
            </button>

            <div class="d-flex justify-content-between align-items-center small">
                <a href="inscription" class="text-decoration-none text-primary fw-semibold">Créer un compte</a>
                <a href="index.jsp" class="text-decoration-none text-secondary"><i class="bi bi-arrow-left"></i> Retour au portail</a>
            </div>
        </form>
    </div>
</body>
</html>
""");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // Identifiants par défaut admin / admin123
        if ("admin".equals(login) && "admin123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            response.sendRedirect("accueil");
            return;
        }

        try {
            InitialisationBD.creerTables();
            Connection con = ConnexionSQLite.getConnection();

            if (con != null) {
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM utilisateur WHERE login = ? AND password = ?"
                );
                ps.setString(1, login);
                ps.setString(2, password);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", login);
                    rs.close();
                    ps.close();
                    con.close();
                    response.sendRedirect("accueil");
                    return;
                }
                rs.close();
                ps.close();
                con.close();
            }

            response.sendRedirect("authentification?error=1");

        } catch (Exception e) {
            response.sendRedirect("authentification?error=1");
        }
    }
}
