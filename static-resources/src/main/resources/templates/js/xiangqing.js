
window.onload = function () {
	before1();
	pullList();
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
		window.location.href = "../dingdanzhongxin.html";
	},true)
	// src = document.createTextNode(window.accound.accound);

}


/**
 * 填充列表
 */
function pullList() {
	var html = $.ajax({
		type: "GET",
		url: "/list/1",
		contentType:"application/json",
		dataType:"json",
		async: false
	}).responseText;
	array = JSON.parse(html);

		/**
		 * 显示图片
		 */
	// 	html = $.ajax({
	// 		type: "POST",
	// 		url: "/img/"+array[item]["id"],
	// 		contentType:"application/json",
	// 		dataType:"json",
	// 		async: false
	// 	}).responseText;
	// 	srcs = JSON.parse(html);
	// 	child_.children[0].children[0].children[0].src = srcs[0];
	// 	items.appendChild(child_);
	// 	if ((parseInt(item)+1)%5==0&&array.length>=5){
	// 		center.appendChild(items);
	// 		items = document.getElementsByName("items")[0].cloneNode(true);
	// 		items.removeChild(items.children[0]);
	// 	}
	// }
	// if (items.children.length > 1){
	// 	center.appendChild(items);
	// 	items = document.getElementsByName("items")[0].cloneNode(true);
	// 	items.removeChild(items.children[0]);
	// }
	// center.removeChild(center.children[1]);
}
