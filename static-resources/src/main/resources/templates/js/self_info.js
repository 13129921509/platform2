window.onload = function () {
    before1();
    userCenter();
    clickEdit();
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
    if(html == ""){
        return ;
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

/**
 * 弹窗事件
 * 单击任何一个编辑字段 弹出相应的输入框然后操作
 */
function clickEdit() {
    edit = document.getElementsByClassName("edit");
    for (e in edit){
        edit[e].onclick = function (e) {
            e = e.currentTarget;
            cs = e.parentElement.parentElement.children[0].innerHTML;
            cs2 = e.parentElement.parentElement.children[1].getAttribute("name");

            email = document.getElementsByName("email")[0].innerText;
            var answer = prompt("请输入你的新" + cs);
            if (answer) {
                ///edit/{email}
                var html = $.ajax({
                    type: "POST",
                    url: "/edit/"+email+"?cs=" + cs2 + "&value=" + answer,
                    contentType: "application/json",
                    dataType: "json",
                    async: false
                }).responseText;
                console.log(html);
            }
        };
    };
}