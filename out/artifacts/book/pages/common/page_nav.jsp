<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/12/25
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页的开始--%>
<div id="page_nav">
    <%--大于首页，才显示上一页和，首页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码范围是：1-总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                <c:if test="${i == requestScope.page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != requestScope.page.pageNo}">
                    <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--情况2：如果总页码大于5的情况，分为三种小情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页为前三页，显示页码为1->5--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况2：当前页为后三页，显示页码为末页减4->末页--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                    <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--小情况3：当前页为中间页，显示页码为当前页减2->当前页加2--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                        <c:if test="${i == requestScope.page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != requestScope.page.pageNo}">
                            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--页码输出的结束--%>
    <%--小于末页，才显示下一页和，末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        })
    </script>
</div>
<%--分页的结束--%>

