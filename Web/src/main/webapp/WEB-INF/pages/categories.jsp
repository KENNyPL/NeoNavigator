<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>

  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Neo navigator</title>

  <jsp:include page="common/scripts_header.jsp"/>
  <!-- 2 load the theme CSS file -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/themes/default/style.min.css" />


</head>

<body>
<div id="wrapper">
  <!-- Navigation -->
  <jsp:include page="common/nevigation_bar.jsp"/>

  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">System administration - <spring:message code="hello" text="Hi" /></h1>
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-lg-4 col-md-6">


        <div id="jstree">
          <!-- in this example the tree is populated from inline HTML -->
          <ul>
            <li>Root node 1
              <ul>
                <li id="child_node_1">Child node 1</li>
                <li>Child node 2</li>
              </ul>
            </li>
            <li>Root node 2</li>
          </ul>
        </div>


      </div>
    </div>
    <!-- /.row -->

    <div class="row">
      <div class="col-lg-12">
        Cygan & Domański Company
      </div>
      <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
  </div>
  <!-- /#page-wrapper -->

</div>
<jsp:include page="common/scripts_footer.jsp"/>

<script src="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/libs/jquery-2.1.3.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/jstree.min.js"></script>
<script>
  $(function () {
    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
    // 7 bind to events triggered on the tree
    $('#jstree').on("changed.jstree", function (e, data) {
      console.log(data.selected);
    });
    // 8 interact with the tree - either way is OK
    $('button').on('click', function () {
      $('#jstree').jstree(true).select_node('child_node_1');
      $('#jstree').jstree('select_node', 'child_node_1');
      $.jstree.reference('#jstree').select_node('child_node_1');
    });
  });
</script>

</body>

</html>