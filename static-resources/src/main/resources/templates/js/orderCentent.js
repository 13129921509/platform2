
window.onload = function () {
    /**
     * 订单填充
     * @type {string}
     */
    var html = $.ajax({
        type: "POST",
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
        // 在此处进行获得订单图片的操作

        list.appendChild(listone);
    }

}