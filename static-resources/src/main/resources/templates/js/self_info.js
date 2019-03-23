window.onload = function () {
    before1();
    userCenter();
}
function userCenter() {
    var html = $.ajax({
        type: "POST",
        url: "/user/detail",
        contentType:"application/json",
        dataType:"json",
        async: false
    }).responseText;

    json = JSON.parse(html);
    document.getElementsByName("username")[0].innerHTML = json["rsp"]['username'];
    document.getElementsByName("realName")[0].innerHTML = json["rsp"]['realName'];
    document.getElementsByName("receivingTel")[0].innerHTML = json["rsp"]['receivingTel'];
    document.getElementsByName("passwords")[0].innerHTML = json["rsp"]['passwords'];
    document.getElementsByName("receivingAddress")[0].innerHTML = json["rsp"]['receivingAddress'];
    document.getElementsByName("email")[0].innerHTML = json["rsp"]['email'];


}
/**
 * 倘若有用户登录 便在登录注册地带显示出来
 */
function before1() {
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
    }
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
        window.location.href = "/forward/dingdanzhongxin";
    },true)
    // src = document.createTextNode(window.accound.accound);

}