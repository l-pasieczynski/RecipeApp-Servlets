<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="appHeader.jsp" %>

<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@ include file="appMenu.jsp" %>


<div class="m-4 p-3 width-medium">
    <div class="m-4 p-3 border-dashed view-height">

        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="${pageContext.request.contextPath}/app/dashboard" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <table class="table">
                <thead>
                <tr class="d-flex">
                    <th class="col-1">ID</th>
                    <th class="col-3">IMIĘ</th>
                    <th class="col-6">NAZWISKO</th>
                    <th class="col-2 center">AKCJE</th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">
                <c:forEach items="${allUsers}" var="user">
                <tr class="d-flex">
                    <td class="col-1">${user.getId()}</td>
                    <td class="col-3">${user.getFirstName()}</td>
                    <td class="col-6">${user.getLastName()}</td>
                    <td class="col-2 center">
                        <form method="post" action="${pageContext.request.contextPath}/app/admin/users">
                            <input type="hidden" name="userId" value="${user.getId()}">
                            <input type="submit" value="Blokuj" name="blocked" class="btn btn-danger rounded-0 text-light m-1">
                        </form>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class="form-group">
                <h4>${message}</h4>
            </div>

        </div>
    </div>
</div>
    </div>
</section>

<%@ include file="appScript.jsp" %>


</body>
</html>
