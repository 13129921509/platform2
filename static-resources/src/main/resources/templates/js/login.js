/**
 * 判断是否能够成功登陆
 * 1.邮箱不能为空，邮箱必须注册
 * 2.密码必须正确
 * 3.验证码不能为空，验证码必须正确
 */
function getRegistrationCode() {
    str = document.getElementsByName("email")[0];
    if (str == '') {
        // document.getElementsByName("spanRePassword")[0].style.display = "inline";
        // document.getElementsByName("spanRePassword")[0].innerHTML = "此项不能为空";
        // document.getElementsByName("spanRePassword")[0].style.color = "red";
        alert("此项不能为空!!");
        str.focus();
        return ;
    }
    if (document.getElementsByName("shopway")[0] != undefined){
        return "/shop/registry";
    }else {
        return "/user/registry"
    }
    email = document.getElementsByName("email")[0];
    var html = $.ajax({
        type: "POST",
        url: "/loginCode",
        contentType:"application/json",
        dataType:"json",
        data: JSON.stringify({
            "email":email.value
        }),
        async: false

    }).responseText;
    result = JSON.parse(html);
    if(result.code == 200){
    }
    if(result.code == 40404){
        alert("该邮箱还没有被注册,请注册!!!");
    }
}


function checkLogin(){
    var email = document.getElementsByName("email")[0].value;
    var password = document.getElementsByName("password")[0].value;
    if (email == null || password == null) {
        alert("账户或密码不能为空");
        return ;
    }
    var html = $.ajax({
        type: "POST",
        url: "/user/login",
        contentType:"application/json",
        dataType:"json",
        data: JSON.stringify({
            // "username":document.getElementsByName("username")[0].value,
            // "password":document.getElementsByName("password")[0].value,
            // "telephone":document.getElementsByName("tel")[0].value,
            "email":document.getElementsByName("email")[0].value,
            "password":document.getElementsByName("password")[0].value,
            "yzm":document.getElementsByName("yanzhengma")[0].value
        }),
        async: false
    }).responseText;
    result = JSON.parse(html);
    if (result.code == 200){
        return false;
    }else if (result.code == 40405) {
        alert("账户与密码不匹配");
        return true;
    }else if(result.code == 40404){
        alert("验证码错误");
        return true;
    }
}
function login2() {

    if (checkLogin()) {
        return false;
    }
    document.getElementsByName("loginform")[0].submit();
}
