<%--
  Created by IntelliJ IDEA.
  User: katsu
  Date: 21.03.2020
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="appHeader.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="appMenu.jsp" %>


        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="${pageContext.request.contextPath}/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.getName()}
                                </p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    ${plan.getDescription()}
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:if test="${d1!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Poniedziałek</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d1}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d2!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Wtorek</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d2}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d3!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Sroda</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d3}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d4!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Czwartek</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d4}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d5!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Piątek</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d5}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d6!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Sobota</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d6}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${d7!=null}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">Niedziela</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${d7}" var="day">
                                <tr class="d-flex">
                                    <td class="col-2">${day.getMealName()}</td>
                                    <td class="col-7">${day.getRecipeName()}</td>
                                    <td class="col-1 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/plan/delete?recipeplanid=${day.getRecipePlanId()}&planid=${plan_id}"
                                           class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    </td>
                                    <td class="col-2 center">
                                        <a href="${pageContext.request.contextPath}/app/recipe/details?recipe_id=${day.getRecipeId()}"
                                           class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </c:if>


                </div>
            </div>
        </div>
    </div>
</section>


<%@ include file="appScript.jsp" %>

</body>
</html>