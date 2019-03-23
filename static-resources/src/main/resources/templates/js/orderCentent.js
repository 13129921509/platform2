
window.onload = function () {
    before1();
    pullOrders();
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


function pullOrders() {
    /**
     * 订单填充
     * @type {string}
     */
    var html = $.ajax({
        type: "GET",
        url: "/order",
        contentType:"application/json",
        dataType:"json",
        async: false
    }).responseText;

    array = JSON.parse(html);
    for (var i in array){
        var listone = document.getElementsByName("list_one")[0].cloneNode(true);
        var list = document.getElementsByName("list")[0];
        listone.children[1].innerHTML = "订单号:"+array[i]["orderCode"];
        listone.children[2].children[0].children[0].innerHTML = array[i]["status"];
        listone.children[2].children[0].children[1].innerHTML = "￥"+array[i]["price"];
        listone.children[2].children[0].children[2].innerHTML = array[i]["startingTime"].replace(/-/g,"/").slice(0,-3);
        listone.children[2].children[0].children[3].children[0].href =
            listone.children[2].children[0].children[3].children[0].href+"?code="+ array[i]["orderCode"];
        // 在此处进行获得订单图片的操作 orderDetails
        html = $.ajax({
            type: "POST",
            url: "/order/img",
            contentType:"application/json",
            dataType:"json",
            data: JSON.stringify({
                commodityCode:array[i]["commodityCode"]
            }),
            async: false
        }).responseText;
        listone.children[0].children[0].setAttribute("src",html);
        listone.children[0].children[0].style.width = "80px";
        listone.children[0].children[0].style.height = "80px";

        list.appendChild(listone);
    }

}