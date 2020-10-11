<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="appHeader.jsp" %>


<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="appMenu.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">

                <!-- dash menu, dodawanie przepisow planów -->

                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <!-- koniec dash menu -->
                <!-- pobieranie liczby przepisow i planow -->

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${userRecipes}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${userPlans}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${planName}
                </h2>

                <c:forEach items="${dayName}" var="day">
                <table class="table">
                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${day}</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                <c:forEach items="${userLastAddedPlan}" var="plan">

                <c:if test="${day eq plan.getDayName()}">
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">${plan.getMealName()}</td>
                        <td class="col-8">${plan.getRecipeName()}</td>
                        <td class="col-2">
                            <button type="button" a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${recipes.getId()}"
                                    class="btn btn-primary rounded-0">Szczegóły
                            </button>
                        </td>
                    </tr>
                    </tbody>
                    </c:if>
                </c:forEach>
                </c:forEach>
                </table>
            </div>
        </div>

    </div>
</section>

<%@ include file="appScript.jsp" %>

</body>
</html>