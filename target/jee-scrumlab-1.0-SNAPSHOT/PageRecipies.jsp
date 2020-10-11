<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<%@ include file="header.jsp" %>


<!-- NAGŁÓWEK -->


<section>
    <div class="row padding-small">
        <i class="fas fa-users icon-users"></i>
        <h1>Przepisy naszych użytkowników:</h1>
        <hr>
        <div class="orange-line w-100"></div>
    </div>
</section>

<!-- WYSZUKIWARKA -->

<form method="post" action="${pageContext.request.contextPath}/recipies">
    <div>
        <table>
        <tr class="d-flex">
            <td class="col-5">
            <h4>Wyszukaj przepis</h4>
            </td>
            <td>
            <input class="w-100 p-1" name="search">
            </td>
            <td class="col-1">
                <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Szukaj</button>
            </td>
        </tr>
        </table>
    </div>
</form>
<br>
<br>

<!-- NAGŁÓWEK TABELI-->

<section class="mr-4 ml-4">
    <table class="table">
        <thead>
        <tr class="d-flex text-color-darker">
            <th scope="col" class="col-1">ID</th>
            <th scope="col" class="col-5">NAZWA</th>
            <th scope="col" class="col-5">OPIS</th>
            <th scope="col" class="col-1">AKCJE</th>
        </tr>
        </thead>

        <!-- KONIEC NAGŁÓWKA TABELI -->

        <tbody class="text-color-lighter">

        <c:forEach items="${recipies}" var="recipe">
            <tr class="d-flex">
                <th scope="row" class="col-1">${recipe.getId()}</th>
                <td class="col-5">
                        ${recipe.getName()}
                </td>
                <td class="col-5">${recipe.getDescription()}</td>
                <td class="col-1"><a href="${pageContext.request.contextPath}/recipe/details?recipe_id=${recipe.getId()}"
                                     class="btn btn-info rounded-0 text-light">Szczegóły</a></td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</section>


<%@ include file="socialMediaFooter.jsp" %>
<%@ include file="footer.jsp" %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>
