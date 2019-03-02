window.onload=function (ev) {
    var html = $.ajax({
        type: "POST",
        url: "/key",
        contentType:"application/json",
        dataType:"json",
        async: false
    }).responseText;
    if(JSON.parse(html).account == undefined){

    }else{
        window.accound = JSON.parse(html);
        before1();
        alert("欢迎您,"+JSON.parse(html).account);
    }
}

/**
 * 倘若有用户登录 便在登录注册地带显示出来
 */
function before1() {
    document.getElementsByName("tihuan").forEach(function (value) {
        value.style.display = "none";
    });

    ul = document.getElementsByName("topbar")[0].children[0];
    li = document.createElement("li");
    a = document.createElement("a");
    a.innerHTML = window.accound["account"];
    li.appendChild(a);
    ul.appendChild(li);
    a.addEventListener("click",function (ev) {
        window.location.href = "../dingdanzhongxin.html";
    },true)
    // src = document.createTextNode(window.accound.accound);

}