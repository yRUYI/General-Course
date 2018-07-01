$(document).ready(function () {
    // var url=$("a").arrt("href");
    // alert(url)
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
                alert($("#employeeID").text());
            })
        }
    });
})