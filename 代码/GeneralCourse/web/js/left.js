$(document).ready(function () {
    // $.ajax({
    //     type:'post',
    //     url:'/Module?type=getModule',
    //     // data: [{"type": "getModule"}],
    //     dataType:'text',
    //     contentType: "application/x-www-form-urlencoded; charset=utf-8",
    //     error:function(){
    //         alert("获取数据失败");
    //     },
    //     success:function(msg){
    //         var modules = jQuery.parseJSON(msg);
    //         // alert(msg);
    //         $.each(modules, function(index,element) {
    //             $("#mainModule").append(element.name)
    //             getModule(1, element.id);
    //         })
    //     }
    // });
    // function getModule(level, parentId) {
    //     $.ajax({
    //         type:'post',
    //         url:'/Module?type=getSonModule&level=' + level + '&parentId=' + parentId,
    //         // data: [{"type": "getModule"}],
    //         dataType:'text',
    //         contentType: "application/x-www-form-urlencoded; charset=utf-8",
    //         error:function(){
    //             alert("获取数据失败");
    //         },
    //         success:function(msg){
    //             var modules = jQuery.parseJSON(msg);
    //             // alert(msg);
    //             $.each(modules, function(index,element) {
    //                 var module = "<dd id='module-" + element.id + "'>\n" +
    //                     "        <div class=\"title\" id='" + element.id + "'>\n" +
    //                     "            <span><img src=\"../images/leftico01.png\" /></span>" + element.name + "\n" +
    //                     "        </div>\n" +
    //                     "    </dd>";
    //
    //                 $("#module").append(module);
    //                 getNextLevelModule(parseInt(element.level) + 1, element.id);
    //             })
    //             $(".menuson li").click(function(){
    //                 $(".menuson li.active").removeClass("active")
    //                 $(this).addClass("active");
    //             });
    //
    //             $('.title').click(function(){
    //                 var $ul = $(this).next('ul');
    //                 $('dd').find('ul').slideUp();
    //                 if($ul.is(':visible')){
    //                     $(this).next('ul').slideUp();
    //                 }else{
    //                     $(this).next('ul').slideDown();
    //                 }
    //             });
    //         }
    //     });
    // }
    //
    // function getNextLevelModule(level, id) {
    //     $.ajax({
    //         type:'post',
    //         url:'/Module?type=getSonModule&level=' + level + '&parentId=' + id,
    //         // data: [{"type": "getModule"}],
    //         dataType:'text',
    //         contentType: "application/x-www-form-urlencoded; charset=utf-8",
    //         error:function(){
    //             alert("获取数据失败");
    //         },
    //         success:function(msg){
    //             var modules = jQuery.parseJSON(msg);
    //             // alert(msg);
    //             var module = "<ul class=\"menuson\">\n";
    //             $.each(modules, function(index,element) {
    //                 module += "<li id='sonModule-" + element.id + "'><cite></cite><a href=\"index.html\" target=\"rightFrame\">" + element.name + "</a><i></i></li>\n";
    //
    //
    //             })
    //             module +=  "        </ul>";
    //             $("#module-" + id).append(module);
    //         }
    //     });
    // }


})