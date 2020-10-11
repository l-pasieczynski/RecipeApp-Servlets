<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<%@ include file="appHeader.jsp" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">

        <%@ include file="appMenu.jsp" %>


        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">LISTA PLANÓW</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="${pageContext.request.contextPath}/app/plan/add"
                           class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj
                            plan</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <table class="table border-bottom">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-1">ID</th>
                            <th class="col-2">NAZWA</th>
                            <th class="col-7">OPIS</th>
                            <th class="col-2 center">AKCJE</th>
                        </tr>
                        </thead>
                        <tbody class="text-color-lighter">
                        <c:forEach items="${plans}" var="plan">
                            <tr class="d-flex">
                                <th scope="row" class="col-1">${plan.getId()}</th>
                                <td class="col-2"> ${plan.getName()} </td>
                                <td class="col-7"> ${plan.getDescription()} </td>
                                <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                    <a href="${pageContext.request.contextPath}/app/plan/delete?plan_id=${plan.getId()}"
                                       class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                    <a href="${pageContext.request.contextPath}/app/plan/details?plan_id=${plan.getId()}"
                                       class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                    <a href="${pageContext.request.contextPath}/app/plan/edit?plan_id=${plan.getId()}"
                                       class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="appScript.jsp" %>

</body>
</html>