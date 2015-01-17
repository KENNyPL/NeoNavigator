<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Neo navigator</title>

    <jsp:include page="common/scripts_header.jsp"/>
    <!-- 2 load the theme CSS file -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/themes/default/style.min.css"/>
    <link rel="stylesheet"
          href="//cdn.datatables.net/1.10.4/css/jquery.dataTables.css"/>

    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>


</head>

<body>
<div id="wrapper">
    <!-- Navigation -->
    <jsp:include page="common/nevigation_bar.jsp"/>
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Points administration - <spring:message code="hello" text="Hi"/></h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <c:url value="/points" var="points_add"/>
        <form:form id="myForm" onsubmit="return submitMe();" action="${points_add}" method="post"
                   modelAttribute="newPoint">

            <div class="row">
                <div class="col-lg-6 col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <spring:message code="category"/>
                        </div>
                        <div class="form-group">
                            <div id="jstree">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <spring:message code="point_new"/> <label class="category_name_div"></label>
                        </div>
                        <div class="form-group">
                            <label><spring:message code="point_name"/></label>
                            <form:input path="name" name="name" class="form-control" placeholder="Enter text"/>
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label><spring:message code="point_longitude"/></label>
                                <form:input path="longitude" name="longitude" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label><spring:message code="point_latitude"/></label>
                                <form:input path="latitude" name="latitude" class="form-control"/>
                            </div>
                            <input type="submit" value="<spring:message code="point_add_label"/> "/>
                        </div>
                    </div>
                </div>
            </div>

            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <form:input path="categoryIds" type="hidden" name="jsfields" id="jsfields" value="0"/>
        </form:form>


        <div class="panel panel-default">
            <div class="panel-heading">
                <spring:message code="points_in_category"/> <label class="category_name_div"></label>
            </div>

            <table id="example" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>longitude</th>
                    <th>latitude</th>
                    <%--<th><spring:message code="details"/></th>--%>
                </tr>
                </thead>
            </table>

        </div>
    </div>
</div>
</div>


<jsp:include page="common/scripts_footer.jsp"/>

<script src="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/libs/jquery-2.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/jstree.min.js"></script>
<script src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
<c:url value="/categories/all" var="allCategories"/>
<c:url value="/points/" var="points"/>
<script>
    var last_action = "";
    var parent_id = null;
    var current_category_name = "root";
    var table = null;


    function submitMe() {
        var tree = $('#jstree').jstree();
        var checked_ids = tree.get_top_checked();
        document.getElementById('jsfields').value = checked_ids;
    }

    $('#jstree')
        // listen for event
            .on('changed.jstree', function (e, data) {
                current_category_name = data.instance.get_node(data.selected[0]).text;
                // Using the jQuery library
                $('.category_name_div').html(current_category_name);

                // alert( 'Data source: '+table.api().ajax.url() );


                table.api().ajax.url("${points}" + current_category_name).load();

                // table.api().ajax.reload();
                // table.ajax.reload();
            })

    $('#jstree')
            .jstree({
                "core": {
                    "animation": 0,
                    "check_callback": true,
                    "themes": {"stripes": true},
                    'data': {
                        'url': function (node) {
                            return "${allCategories}";
                        }
                        /*'data' : function (node) {
                         return { 'id' : node.id };
                         }*/
                    }
                },
                "types": {
                    "#": {"max_children": 1, "max_depth": 4, "valid_children": ["root"]},
                    "root": {"icon": "/static/3.0.2/assets/images/tree_icon.png", "valid_children": ["default"]},
                    "default": {"valid_children": ["default", "file"]},
                    "file": {"icon": "glyphicon glyphicon-file", "valid_children": []}
                },
                "plugins": ["contextmenu", "dnd", "search", "state", "types", "wholerow", "checkbox"]
            });

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $(document).ready(function () {
        table = $('#example').dataTable({
            "ajax": '${points}' + current_category_name,
            "columns": [
                {"data": "name"},
                {"data": "longitude"},
                {"data": "latitude"}
            ]
        });
    });

</script>
</body>

</html>
