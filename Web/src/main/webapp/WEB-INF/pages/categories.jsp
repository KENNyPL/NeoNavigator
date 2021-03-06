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
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/vakata-jstree-2f630b4/dist/themes/default/style.min.css"/>

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
                <h1 class="page-header">System administration - <spring:message code="hello" text="Hi"/></h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="row">
                    <div class="col-lg-4 col-md-12">
                        <button id="add_element_button" type="button" class="btn btn-lg btn-block btn-success disabled"
                                onclick="demo_create();"><i class="fa fa-plus "></i> Create
                        </button>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <button id="rename_element_button" type="button"
                                class="btn btn-lg btn-block btn-warning disabled" onclick="demo_rename();"><i
                                class="fa fa-edit "></i> Rename
                        </button>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <button id="del_element_button" type="button" class="btn btn-lg btn-block btn-danger disabled"
                                onclick="demo_delete();"><i class="fa fa-minus "></i> Delete
                        </button>
                    </div>
                    <div class="col-lg-4 col-md-12">
                        <div id="jstree">
                        </div>
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
        var last_action="";
        var parent_id=null;
        $('#jstree')
                .jstree({
                    "core": {
                        "animation": 0,
                        "check_callback": true,
                        "themes": {"stripes": true},
                        'data': {
                            'url': function (node) {
                                return 'categories/all';
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
                    "plugins": ["contextmenu", "dnd", "search", "state", "types", "wholerow"]
                });


        function demo_create() {
            //todo: to add execute save current categories structure, check data integration etc...
            var ref = $('#jstree').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                return false;
            }
            sel = sel[0];
            sel = ref.create_node(sel);
            if (sel) {
                if(ref.edit(sel)== true){
                    console.log("ADD---");
                }else{
                    console.log("ssssssss---");
                }
            }
        }
        ;
        function demo_rename(mode) {
            //todo: to add execute rename current categories structure, check data integration etc...
            var ref = $('#jstree').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                return false;
            }
            sel = sel[0];

            ref.edit(sel);
        }
        ;

        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });

        function demo_delete() {
            //todo: to add execute delete current categories structure, check data integration etc...
            var ref = $('#jstree').jstree(true),
                    sel = ref.get_selected();
            if (!sel.length) {
                return false;
            }

            $.ajax({
                traditional: true,
                type: "DELETE",
                url: 'categories/byId/'+parent_id,
                dataType: "json",
                success: function(resut){
                    console.log('success' + resut.message);
                    console.log('result: ' + resut.result);
                    if(resut.result=="OK"){
                        ref.delete_node(sel);
                    } else{

                    }
                },
                error:function(resut){
                    console.log('failed: ' + resut.message);
                }
            });
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
        function handle_category_add_fail(resut, node) {
            var ref = $('#jstree').jstree(true),
                    sel = ref.get_selected();
            ref.delete_node(node);
            alert("<spring:message code="add_category_fail" text="Hi" /> \""+node.text+"\", reason: "+resut.message);
        };

        $(function () {
            // 6 create an instance when the DOM is ready
            $('#jstree').jstree();
            // 7 bind to events triggered on the tree
            $('#jstree').on("changed.jstree", function (e, data) {
                tree = $('#jstree').jstree();

                console.log("changed---");
                if (data.node) {
                    parent_id=data.node.id;
                    if(!$.isNumeric(parent_id)){
                        parent_id=0;
                    }
                    $('#rename_element_button').removeClass('disabled');
                    $('#del_element_button').removeClass('disabled');
                    $('#add_element_button').removeClass('disabled');

                    if (data.node.state.hasOwnProperty("read_only", true)) {
                        $('#rename_element_button').addClass('disabled');
                        $('#del_element_button').addClass('disabled');
                    }

                    if (tree.get_rules("#").max_depth <= data.node.parents.length) {
                        $('#add_element_button').addClass('disabled');
                    }

                }
                last_action= "changed";
            });
            $('#jstree').on("rename_node.jstree", function (e, data) {
                tree = $('#jstree').jstree();

                console.log("rename_node---");
                console.log(e);
                console.log(data);
                console.log(last_action);
                if(last_action=="create_node"){
                    console.log(parent_id);
                    console.log(data.text)

                    //todo: insert into database, if failed then delete node like below:


                    $.ajax({
                        traditional: true,
                        type: "POST",
                        url: 'categories/add',
                        data: 'id='+ parent_id+'&name='+ data.text ,
                        dataType: "json",
                        success: function(resut){
                            console.log('success' + resut.message);
                            console.log('result: ' + resut.result);
                            if(resut.result!="OK"){
                                handle_category_add_fail(resut, data.node);
                            }
                        },
                        error:function(resut){
                            console.log('failed: ' + resut.message);
                            handle_category_add_fail(resut, data.node);
                        }
                    });

                } else {
                    $.ajax({
                        traditional: true,
                        type: "POST",
                        url: 'categories/rename',
                        data: 'id='+ parent_id+'&name='+ data.text+"&${_csrf.parameterName}=${_csrf.token}" ,
                        dataType: "json",
                        success: function(resut){
                            console.log('success' + resut.message);
                            console.log('result: ' + resut.result);
                            if(resut.result!="OK"){
                                tree.set_text(data.node, data.node);
                            }
                        },
                        error:function(resut){
                            console.log('failed: ' + resut.message);
                            tree.set_text(data.node, data.old);
                        }
                    });
                }
                last_action="rename_node";
            });
            $('#jstree').on("create_node.jstree", function (e, data) {
                last_action="create_node";
            });
        });

    </script>

</body>

</html>