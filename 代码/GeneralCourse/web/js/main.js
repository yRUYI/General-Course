$(document).ready(function () {
    $.ajax({
        type:'get',
        url:'/Login',
        // async: false,
        dataType:'text',
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        error:function(){
            alert("获取数据失败");
        },
        success:function(msg){
            var username = jQuery.parseJSON(msg);
            // alert(msg);
            $.each(username, function(index,element) {
                $("#userName").text(element.username);
                $("#employeeID").text(element.employeeId);
                // alert($("#employeeID").text());
            })
            getModules($("#employeeID").text());
        }
    });

    function getModules(employeeId) {
        $.ajax({
            type:'post',
            url:'/Module?type=getModule&employeeId=' + employeeId,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var modules = jQuery.parseJSON(msg);
                // alert(msg);
                $.each(modules, function(index,element) {
                    $("#mainModule").append(element.name)
                    getModule(1, element.id, employeeId);
                })

            }
        });
    }

    function getModule(level, parentId, employeeId) {
        // alert("module: " + employeeId)
        $.ajax({
            type:'post',
            url:'/Module?type=getSonModule&level=' + level + '&parentId=' + parentId + '&employeeId=' + employeeId,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var modules = jQuery.parseJSON(msg);
                // alert(msg);
                $.each(modules, function(index,element) {
                    var module = "<dd id='module-" + element.id + "'>\n" +
                        "        <div class=\"title\" id='" + element.id + "'>\n" +
                        "            <span><img src=\"../images/leftico01.png\" /></span>" + element.name + "\n" +
                        "        </div>\n" +
                        "    </dd>";

                    $("#module").append(module);
                    getNextLevelModule(parseInt(element.level) + 1, element.id, employeeId);
                })


                $('.title').click(function(){
                    var $ul = $(this).next('ul');
                    $('dd').find('ul').slideUp();
                    if($ul.is(':visible')){
                        $(this).next('ul').slideUp();
                    }else{
                        $(this).next('ul').slideDown();
                    }
                });
                $(".menuson li").click(function(){
                    $(".menuson li.active").removeClass("active")
                    $(this).addClass("active");
                });

            }
        });

    }

    function getNextLevelModule(level, id, employeeId) {
        $.ajax({
            type:'post',
            url:'/Module?type=getSonModule&level=' + level + '&parentId=' + id + '&employeeId=' + employeeId,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var modules = jQuery.parseJSON(msg);
                // alert(msg);
                var module = "<ul class=\"menuson\">\n";
                $.each(modules, function(index,element) {
                    module += "<li id='sonModule-" + element.id + "' ><cite></cite><a href=\"#\"  onclick=\"doSomeThing(this)\" >" + element.name + "</a><i></i></li>\n";


                });
                module +=  "        </ul>";
                $("#module-" + id).append(module);
            }
        });
    }

    doSomeThing = function(that) {
        var moduleType = $(that).text();
        alert(moduleType);

        $("#currentPlace").empty();
        var place = "<li><a href=\"#\">" + moduleType + "</a></li>";
        $("#currentPlace").append(place);

        if (moduleType == "栏目管理") {
            showColumns();
        } else if (moduleType == "留言管理") {
            getMessages();
        } else if (moduleType == "审核文章") {
            showArticle(0);
        } else if (moduleType == "文章管理") {
            showArticle(1);
        } else if (moduleType == "角色管理") {
            showRole();
        } else if (moduleType == "人员管理") {
            showEmployee();
        } else if (moduleType == "角色分配") {
            showEmployees();
        } else if (moduleType == "模块管理") {
            showModule();
        } else if (moduleType == "权限管理") {
            showPermission();
        }

    }

    function showColumns() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照栏目级别搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-primary\"  id='searchColumns'>\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 编号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t栏目名称\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t栏目级别\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);

        showColumn();

    }

    function showColumn() {
        $.ajax({
            type:'post',
            url:'/Column?type=getAllColumns',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var column = jQuery.parseJSON(msg);
                // alert(msg);
                var columns = "<tr>\n";
                $.each(column, function(index,element) {
                    columns += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.level + "</td>" +
                        "<td><a id='editColumn' onclick='editColumn(this)' name='" + element.id + "'data-toggle=\"modal\" data-target=\"#editColumnModal\">修改</a> <a id='lookColumn' onclick='lookColumn(this)' name='" + element.id + "'data-toggle=\"modal\" data-target=\"#lookColumnModal\">查看</a></td></tr>";
                });
                columns +=  "        </tr>";
                $("tbody").append(columns);

            }
        });

    }
    $("#sureEditColumn").click(function () {
        var id = $("#editColumnId").val();
        var name = $("#editColumnName").val();

        $.ajax({
            type:'post',
            url:'/Column?type=editColumn&id=' + id + "&name=" + name,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                alert("修改成功");
            }
        });
        showColumns();
    })

    editColumn = function (that) {
        var id = $(that).attr("name");
        alert(id);
        $.ajax({
            type:'post',
            url:'/Column?type=getTheColumn&id=' + id,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var column = jQuery.parseJSON(msg);
                // alert(msg);
                $.each(column, function(index,element) {
                    $("#editColumnId").val(element.id);
                    $("#editColumnName").val(element.name);
                    $("#editColumnLevel").val(element.level);
                });
            }
        });
    }

    lookColumn = function (that) {
        var id = $(that).attr("name");
        alert(id);
        $.ajax({
            type:'post',
            url:'/Column?type=getTheColumn&id=' + id,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var column = jQuery.parseJSON(msg);
                // alert(msg);
                $.each(column, function(index,element) {
                    $("#lookColumnId").val(element.id);
                    $("#lookColumnName").val(element.name);
                    $("#lookColumnLevel").val(element.level);
                });
            }
        });


    }

    // 留言展示
    function getMessages() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照留言内容搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"button\">\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 编号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t留言内容\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t留言时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t是否已回复\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        showMessage();
    }

    function showMessage() {
        $.ajax({
            type:'get',
            url:'/Message',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var message = jQuery.parseJSON(msg);
                alert(msg);
                var messages = "<tr>\n";
                $.each(message, function(index,element) {
                    messages += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>";
                        if (element.content.length > 20) {
                            messages += "<td>" + element.content.substr(0, 20) + "...</td>"
                        } else {
                            messages += "<td>" + element.content + "</td>";
                        }
                    messages += "<td>" + element.createTime + "</td>" +
                        "<td>" + element.status + "</td>" +
                        "<td><a name='" + element.id + "' onclick='editMessage(this)' data-toggle=\"modal\" data-target=\"#editMessageModal\">查看</a> <a id='deleteMessage' name='" + element.id + "'>删除</a></td></tr>";


                });
                messages +=  "        </tr>";
                $("tbody").append(messages);

                $("#deleteMessage").click(function () {
                    var id = $("#deleteMessage").attr("name");
                    alert(id)
                    if (confirm("确认删除？")) {
                        $.ajax({
                            type: 'post',
                            url: '/Message?type=delete&id=' + id,
                            // data: [{"type": "getModule"}],
                            dataType: 'text',
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            error: function () {
                                alert("获取数据失败");
                            },
                            success: function (msg) {
                                alert("删除成功");
                            }
                        });
                        getMessages();
                    }
                })
            }
        });
    }

    editMessage = function (that) {
        var id = $(that).attr("name");
        // alert(id);

        $.ajax({
            type:'post',
            url:'/Message?type=look&id=' + id,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var message = jQuery.parseJSON(msg);
                $.each(message, function(index,element) {
                    $("#editMessageId").val(element.id);
                    $("#editMessageContent").val(element.content);
                    $("#editMessageTime").val(element.createTime);
                    $("#editReply").val(element.reply);
                    $("#editReplyEmployee").val(element.name);
                    $("#editReplyTime").val(element.replyTime);

                });
            }
        });
    }

    $("#sureEditMessage").click(function () {
        var id = $("#editMessageId").val();
        var reply = $("#editReply").val();
        var employeeId = $("#employeeID").text();

        $.ajax({
            type:'post',
            url:'/Message?type=update&id=' + id + "&reply=" + reply + '&employeeId=' + employeeId,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                alert("回复成功");
            }
        });
        getMessages();
    })



    //文章展示
    function showArticle(showType) {
        if (showType == 0) {//审核文章
            reviewArticle()
        } else if (showType == 1) {//文章管理
            getArticle();
        }
    }
    
    function reviewArticle() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照标题搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"button\">\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t文章标题\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t作者\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t栏目名称\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建日期\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t审核状态\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        showReviewArticle();
    }

    function showReviewArticle() {
        $.ajax({
            type:'post',
            url:'/Article?type=review',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var message = jQuery.parseJSON(msg);
                alert(msg);
                var messages = "<tr>\n";
                $.each(message, function(index,element) {
                    messages += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.title + "</td>" +
                        "<td>" + element.author + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td>" + element.columnName + "</td>" +
                        "<td>" + element.status + "</td>" +
                        "<td><a>删除</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(messages);
            }
        });
    }

    //文章管理
    function getArticle() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照标题搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"button\">\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t文章标题\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t作者\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t栏目名称\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建日期\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t是否置顶\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        showAllArticle();
    }

    function showAllArticle() {
        $.ajax({
            type:'post',
            url:'/Article?type=manage',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var message = jQuery.parseJSON(msg);
                alert(msg);
                var messages = "<tr>\n";
                $.each(message, function(index,element) {
                    messages += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.title + "</td>" +
                        "<td>" + element.author + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td>" + element.columnName + "</td>" +
                        "<td>" + element.whether_top + "</td>" +
                        "<td><a id='deleteArticle' name='" + element.id + "'>删除</a><a id='editArticle' name='" + element.id + "' onclick='editArticle(this)' data-toggle=\"modal\" data-target=\"#editArticleModal\">编辑</a> <a id='lookArticleModal'>查看</a></td></tr>";
                });
                $("tbody").append(messages);
            }
        });
    }

    editArticle = function (that) {
        var id = $(that).attr("name");
        alert(id);

        $.ajax({
            type:'post',
            url:'/Article?type=getArticleById&id=' + id,
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                $.each(Role, function(index,element) {

                });
            }
        });

    }

    //角色管理
    function showRole() {
        var search = "<div class=\"row clearfix\">\n" +
            "\t\t<div class=\"col-md-2 column\">\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"col-md-6 column\">\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"col-md-4 column\">\n" +
            "\t\t\t <button type=\"button\" class=\"btn btn-default\">新增</button>\n" +
            "\t\t</div>\n" +
            "\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t角色名\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        getAllRole();
    }

    function getAllRole() {
        $.ajax({
            type:'get',
            url:'/Role',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                var roles = "<tr>\n";
                $.each(Role, function(index,element) {
                    roles += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td><a>删除</a> <a>编辑</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(roles);
            }
        });
    }

    function showEmployee() {
        var search = "<div class=\"row clearfix\">\n" +
            "\t\t<div class=\"col-md-2 column\">\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"col-md-6 column\">\n" +
            "\t\t</div>\n" +
            "\t\t<div class=\"col-md-4 column\">\n" +
            "\t\t\t <button type=\"button\" class=\"btn btn-default\">新增</button>\n" +
            "\t\t</div>\n" +
            "\t</div>";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t姓名\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t性别\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t账号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t角色\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        getAllEmployee();
    }

    function getAllEmployee() {
        $.ajax({
            type:'get',
            url:'/Employee',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                var roles = "<tr>\n";
                $.each(Role, function(index,element) {
                    roles += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.sex + "</td>" +
                        "<td>" + element.account + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td>" + element.roleName + "</td>" +
                        "<td><a>删除</a> <a>编辑</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(roles);
            }
        });
    }

    function showModule() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照标题搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"button\">\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table style='width: 1000px;' class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t名称\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t是否禁用\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t级别\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        getAllModule();
    }

    function getAllModule() {
        $.ajax({
            type:'get',
            url:'/Module',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                var roles = "<tr>\n";
                $.each(Role, function(index,element) {
                    roles += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.status + "</td>" +
                        "<td>" + element.level + "</td>" +
                        "<td><a>编辑</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(roles);
            }
        });
    }

    function showPermission() {
        var search = "<div class=\"col-lg-6\">\n" +
            "\t\t\t\t<div class=\"input-group\">\n" +
            "\t\t\t\t\t<input type=\"text\" placeholder='按照权限名称搜索' class=\"form-control\">\n" +
            "\t\t\t\t\t<span class=\"input-group-btn\">\n" +
            "\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"button\">\n" +
            "\t\t\t\t\t\t\t搜索\n" +
            "\t\t\t\t\t\t</button>\n" +
            "\t\t\t\t\t</span>\n" +
            "\t\t\t\t</div><!-- /input-group -->\n" +
            "\t\t\t</div><!-- /.col-lg-6 -->\n" +
            "\t\t</div>";

        var table = "<table style='width: 1000px;' class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t名称\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t描述\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        getAllPermission();
    }

    function getAllPermission() {
        $.ajax({
            type:'get',
            url:'/Permission',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                var roles = "<tr>\n";
                $.each(Role, function(index,element) {
                    roles += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td>" + element.description + "</td>" +
                        "<td><a>编辑</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(roles);
            }
        });
    }

    function showEmployees() {
        var search = "";

        var table = "<table class=\"table table-striped table-hover\">\n" +
            "\t\t\t\t<thead>\n" +
            "\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t 序号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\tid\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t姓名\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t性别\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t账号\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t<th>\n" +
            "\t\t\t\t\t\t\t创建时间\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t角色\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t\t\t操作\n" +
            "\t\t\t\t\t\t</th>\n" +
            "\t\t\t\t\t</tr>\n" +
            "\t\t\t\t</thead>\n" +
            "\t\t\t\t<tbody>\n" +
            "\t\t\t\t</tbody>\n" +
            "\t\t\t</table>\n";

        $("#content").empty();
        $("#content").append(search);
        $("#content").append(table);
        getAllEmployees();
    }

    function getAllEmployees() {
        $.ajax({
            type:'get',
            url:'/Employee',
            // data: [{"type": "getModule"}],
            dataType:'text',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error:function(){
                alert("获取数据失败");
            },
            success:function(msg){
                var Role = jQuery.parseJSON(msg);
                alert(msg);
                var roles = "<tr>\n";
                $.each(Role, function(index,element) {
                    roles += "<tr><td>" + index + "</td>" +
                        "<td>" + element.id + "</td>" +
                        "<td>" + element.name + "</td>" +
                        "<td>" + element.sex + "</td>" +
                        "<td>" + element.account + "</td>" +
                        "<td>" + element.createTime + "</td>" +
                        "<td>" + element.roleName + "</td>" +
                        "<td><a>编辑</a> <a>查看</a></td></tr>";
                });
                $("tbody").append(roles);
            }
        });
    }
})