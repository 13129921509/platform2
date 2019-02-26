
function registry2() {
    checkUsername();
    checkPassword();
    checkRePassword();
    checkTel();
    checkYzm();
    checkRegistry();//首先解决js无法识别函数的问题
}

function checkYzm() {
    var str = document.getElementsByName("yzm")[0].value;
    if (str == '') {
        document.getElementsByName("spanYzm")[0].style.display = "inline";
        document.getElementsByName("spanYzm")[0].innerHTML = "此项不能为空";
        document.getElementsByName("spanYzm")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanYzm")[0].style.display = "none";
    }
    var html = $.ajax({
        type: "POST",
        url: "/checkYzm",
        contentType:"application/json",
        dataType:"json",
        data: JSON.stringify({
            // "username":document.getElementsByName("username")[0].value,
            // "password":document.getElementsByName("password")[0].value,
            // "telephone":document.getElementsByName("tel")[0].value,
            "email":document.getElementsByName("email")[0].value,
            "yzm":document.getElementsByName("yzm")[0].value
        }),
        async: true

    }).responseText;

}


function checkUsername(input) {
    if(input == undefined){
        var str = document.getElementsByName("username")[0].value;
    }else{
        var str = input.value;
    }
    var han = /^[\u4e00-\u9fa5]+$/;
    if (str == '') {
        document.getElementsByName("spanUsername")[0].style.display = "inline";
        document.getElementsByName("spanUsername")[0].innerHTML = "此项不能为空";
        document.getElementsByName("spanUsername")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanUsername")[0].style.display = "none";
    }
    if (han.test(str)) {
        document.getElementsByName("spanUsername")[0].style.display = "inline";
        document.getElementsByName("spanUsername")[0].innerHTML = "此项不能为汉字";
        document.getElementsByName("spanUsername")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanUsername")[0].style.display = "none";
    };
}

function checkPassword(input) {
    if(input == undefined){
        var str = document.getElementsByName("password")[0].value;
    }else{
        var str = input.value;
    }
    if (str == '') {
        document.getElementsByName("spanPassword")[0].style.display = "inline";
        document.getElementsByName("spanPassword")[0].innerHTML = "此项不能为空";
        document.getElementsByName("spanPassword")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanPassword")[0].style.display = "none";
    }
    if(str.length <= 6){
        document.getElementsByName("spanPassword")[0].style.display = "inline";
        document.getElementsByName("spanPassword")[0].innerHTML = "密码不能必须大于6位数字";
        document.getElementsByName("spanPassword")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanPassword")[0].style.display = "none";
    }
}

function checkRePassword(input) {
    if(input == undefined){
        var str = document.getElementsByName("repassword")[0].value;
    }else{
        var str = input.value;
    }
    if (str == '') {
        document.getElementsByName("spanRePassword")[0].style.display = "inline";
        document.getElementsByName("spanRePassword")[0].innerHTML = "此项不能为空";
        document.getElementsByName("spanRePassword")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanRePassword")[0].style.display = "none";
    }
    if(str != document.getElementsByName("password")[0].value){
        document.getElementsByName("spanRePassword")[0].style.display = "inline";
        document.getElementsByName("spanRePassword")[0].innerHTML = "必须与密码一致";
        document.getElementsByName("spanRePassword")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanRePassword")[0].style.display = "none";
    }
}

function checkTel(input) {
    if(input == undefined){
        var str = document.getElementsByName("tel")[0].value;
    }else{
        var str = input.value;
    }
    if (str == '') {
        document.getElementsByName("spanTel")[0].style.display = "inline";
        document.getElementsByName("spanTel")[0].innerHTML = "此项不能为空";
        document.getElementsByName("spanTel")[0].style.color = "red";
        return ;
    }else{
        document.getElementsByName("spanTel")[0].style.display = "none";
    }

    var myreg = /^(((13[0-9]{1})|159|153)+\d{8})$/;
    if(str.length!=11 || !myreg.test(str))
    {
        document.getElementsByName("spanTel")[0].style.display = "inline";
        document.getElementsByName("spanTel")[0].innerHTML = "请输入正确的手机格式";
        document.getElementsByName("spanTel")[0].style.color = "red";
    }
}


function getRegistrationCode() {
    var html = $.ajax({
        type: "POST",
        url: "/registrycode",
        contentType:"application/json",
        dataType:"json",
        data: JSON.stringify({
            // "username":document.getElementsByName("username")[0].value,
            // "password":document.getElementsByName("password")[0].value,
            // "telephone":document.getElementsByName("tel")[0].value,
            // "telephone":document.getElementsByName("tel")[0].value,
            "email":document.getElementsByName("email")[0].value
        }),
        async: false

    }).responseText;
    result = JSON.parse(html);
    if(result.code == 40402){
        document.getElementsByName("spanYzm")[0].style.display = "inline";
        document.getElementsByName("spanYzm")[0].innerHTML = "邮箱已被注册，请更换";
        document.getElementsByName("spanYzm")[0].style.color = "red";
    }

    if(result.code == 200){
        document.getElementsByName("spanYzm")[0].style.display = "inline";
        document.getElementsByName("spanYzm")[0].innerHTML = "60秒后验证码失效";
        document.getElementsByName("spanYzm")[0].style.color = "red";
    }
}

function checkRegistry() {
    var html = $.ajax({
        type: "POST",
        url: "/user/registry",
        contentType:"application/json",
        dataType:"json",
        data: JSON.stringify({
            "username":document.getElementsByName("username")[0].value,
            "password":document.getElementsByName("password")[0].value,
            "telephone":document.getElementsByName("tel")[0].value,
            "email":document.getElementsByName("email")[0].value
        }),
        async: false
    }).responseText;
    result = JSON.parse(html);
    if (result.code == 200){
        alert("注册成功,请登录!!!");
        // document.getElementsByName("registry")[0].action = "/forward/login";
        // document.getElementsByName("submit")[0].submit;
    }
}