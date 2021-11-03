<%@ page import="model.Matiere" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.PlanEtudesImp" %><%--
  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestion des matieres</title>
</head>
<body>
<h1>Gestion des matieres</h1>
<form action="Deconnecter" method="post">
    <input type="submit" value="Logout">
</form>
<%
    if (session != null) {
        if (session.getAttribute("user") != null) {
            String name = (String) session.getAttribute("user");
            out.print("Hello, " + name );
        } else {
            response.sendRedirect("/login.jsp");
        }
    }
%>
<h2> Recherche Matiere</h2>
<form method="post" action="RechercheMc">
    Mot clé <input type="text" name="motCle" >
    <input type="submit" value="valider">
</form>
<hr>
<c:if test = "${check}">
    <h2> Ajout Matiere</h2>
    <form action="AjoutMatiere" method="post">
        Nom matiere:  <input type="text" name="NomMatiere"><br><br>
        Nbre H cours <input type="text" name="nbHeuresCours"><br><br>
        Nbre H Tp &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="nbHeuresTp"><br><br>
        Coefficient &nbsp; &nbsp;&nbsp; <input type="text" name="coefficient"><br><br>
        <label for="groupe">Groupe Module </label>
            <select id="groupe" name="groupe" class="form-control" >
                <option value="jee">jee</option>
            </select>

        <fieldset class="form-group" >
            <label>Niveau</label><br>
            <input type="radio" name="niveau" value="1" required > 1<br>
            <input type="radio" name="niveau" value="2"> 2<br>
            <input type="radio" name="niveau" value="3"> 3
        </fieldset>
        <input type="submit" class="btn btn-success">
    </form>
</c:if>
<hr>
<h2> Affichage Matieres </h2>
<div>
    <table class="table table-bordred">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom matiere:</th>
            <th>Nbre H cours</th>
            <th>Nbre H Tp</th>
            <th>Coefficient</th>
            <th>GM</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="m" items="${list}" >
            <tr>
                <td><c:out value="${m.id}" /></td>
                <td><c:out value="${m.nom}" /></td>
                <td><c:out value="${m.nbHeuresCours}" /></td>
                <td><c:out value="${m.getNbHeuresTp}" /></td>
                <td><c:out value="${m.coefficient}" /></td>
                <td><c:out value="${m.groupeModule.nom}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
