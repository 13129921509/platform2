
window.onload = function () {
    pullOrders();
    before1();
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
        window.location.href = "../dingdanzhongxin.html";
    },true)
    // src = document.createTextNode(window.accound.accound);

}


function pullOrders() {
    /**
     * 订单填充
     * @type {string}
     */
    var html = $.ajax({
        type: "POST",
        url: "/orderDetail",
        contentType:"application/json",
        dataType:"json",
        async: false
    }).responseText;

    array = JSON.parse(html);
    if (array["code"] != 200){
        alert("登录超时");
        $.ajax({
            type: "POST",
            url: "/forward/login",
            contentType:"application/json",
            dataType:"json",
            async: false
        });
    }
    for (var i in array["rsp"]){
        var listone = document.getElementsByName("list_one")[0].cloneNode(true);
        var list = document.getElementsByName("list")[0];
        listone.children[1].innerHTML = array["rsp"][i]["commodityCode"];
        listone.children[3].innerHTML = array["rsp"][i]["commodityName"].slice(0,20)+"……";
        listone.children[4].children[0].children[0].innerHTML = array["rsp"][i]["number"];
        listone.children[4].children[0].children[1].innerHTML = "￥"+array["rsp"][i]["price"]; // 在此处进行获得订单图片的操作 orderDetails
        html = $.ajax({
            type: "POST",
            url: "/orderDetails/img/"+array["rsp"][i]["commodityCode"],
            contentType:"application/json",
            dataType:"json",
            async: false
        }).responseText;
        listone.children[0].children[0].setAttribute("src",html);
        listone.children[0].children[0].style.width = "80px";
        listone.children[0].children[0].style.height = "80px";

        list.appendChild(listone);
    }
    list.removeChild(list.children[1]);

}