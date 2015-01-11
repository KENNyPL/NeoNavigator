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
      <div class="col-lg-12 col-md-12">
        <div class="row">
          <div class="col-lg-4 col-md-12">
            <button id="add_element_button" type="button" class="btn btn-lg btn-block btn-success disabled" onclick="demo_create();"><i class="fa fa-plus "></i> Create</button>
            </div>
          <div class="col-lg-4 col-md-12">
            <button id="rename_element_button" type="button" class="btn btn-lg btn-block btn-warning disabled" onclick="demo_rename();"><i class="fa fa-edit "></i> Rename</button>
            </div>
          <div class="col-lg-4 col-md-12">
            <button id="del_element_button" type="button" class="btn btn-lg btn-block btn-danger disabled" onclick="demo_delete();"><i class="fa fa-minus "></i> Delete</button>
            </div>

        <div id="jstree">
          <%--<!-- in this example the tree is populated from inline HTML -->--%>
          <%--<ul>--%>
            <%--<li>Root node 1--%>
              <%--<ul>--%>
                <%--<li id="child_node_1">Child node 1</li>--%>
                <%--<li>Child node 2</li>--%>
              <%--</ul>--%>
            <%--</li>--%>
            <%--<li>Root node 2</li>--%>
          <%--</ul>--%>
        </div>
        </br>
        <button id="save_element_button" type="button" class="btn btn-primary btn-lg btn-block" onclick="demo_save();"><i class="fa fa-save "></i> Save changes</button>
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
  $('#jstree')
          .jstree({
            "core" : {
              "animation" : 0,
              "check_callback" : true,
              "themes" : { "stripes" : true },
              'data' : [
                {
                  'text' : 'Root node 2',
                  'state' : {
                    'opened' : true,
                    "select": true,
                    "read_only": true
                  },
                  'children' : [
                    { 'text' : 'Child 1' },
                    'Child 2'
                  ]
                }
              ]
            },
            "types" : {
              "#" : { "max_children" : 1, "max_depth" : 4, "valid_children" : ["root"] },
              "root" : { "icon" : "/static/3.0.2/assets/images/tree_icon.png", "valid_children" : ["default"] },
              "default" : { "valid_children" : ["default","file"] },
              "file" : { "icon" : "glyphicon glyphicon-file", "valid_children" : [] }
            },
            "plugins" : [ "contextmenu", "dnd", "search", "state", "types", "wholerow" ]
          });


  function demo_create() {
    var ref = $('#jstree').jstree(true),
            sel = ref.get_selected();
    if(!sel.length) { return false; }
    sel = sel[0];
    sel = ref.create_node(sel);
    if(sel) {
      ref.edit(sel);
    }
  };
  function demo_rename() {
    var ref = $('#jstree').jstree(true),
            sel = ref.get_selected();
    if(!sel.length) { return false; }
    sel = sel[0];
    ref.edit(sel);
  };
  function demo_delete() {
    var ref = $('#jstree').jstree(true),
            sel = ref.get_selected();
    if(!sel.length) { return false; }
    ref.delete_node(sel);
  };

  function demo_save() {

    //todo: to add execu7te save current categories structure, check data integration etc...
  };

  $(function () {
    var to = false;
    $('#demo_q').keyup(function () {
      if (to) {
        clearTimeout(to);
      }
      to = setTimeout(function () {
        var v = $('#demo_q').val();
        $('#jstree_demo').jstree(true).search(v);
      }, 250);
    });
  });

  $(function () {
    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
    // 7 bind to events triggered on the tree
    $('#jstree').on("changed.jstree", function (e, data) {
      tree = $('#jstree').jstree();
      console.log(data.selected);

      if(data.node){

        $('#rename_element_button').removeClass('disabled');
        $('#del_element_button').removeClass('disabled');
        $('#add_element_button').removeClass('disabled');

        if(data.node.state.hasOwnProperty("read_only", true)){
          $('#rename_element_button').addClass('disabled');
          $('#del_element_button').addClass('disabled');
        }

        console.log(tree.get_rules("#").max_depth);
        console.log(data.node.parents.length);
        if(tree.get_rules("#").max_depth<=data.node.parents.length){
          $('#add_element_button').addClass('disabled');
        }

      }
    });
  });

</script>

</body>

</html>