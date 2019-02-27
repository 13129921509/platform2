window.onload=function (ev) {
    var html = $.ajax({
        type: "POST",
        url: "/key",
        contentType:"application/json",
        dataType:"json",
        async: false
    }).responseText;
    alert("欢迎您,"+JSON.parse(html).account);
}