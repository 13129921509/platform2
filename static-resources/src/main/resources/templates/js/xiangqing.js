
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
		url: "/commodity",
		contentType:"application/json",
		dataType:"json",
		async: false
	}).responseText;
	info = JSON.parse(html);
	document.getElementsByName("brand")[0].innerHTML=info["rsp"]["brand"];
	document.getElementsByName("commodityName")[0].innerText=info["rsp"]["commodityName"];
	document.getElementsByName("floorPrice")[0].innerHTML=info["rsp"]["floorPrice"];//商品价格
	document.getElementsByName("brand")[1].innerHTML = info["rsp"]["brand"];
	document.getElementsByName("model")[0].innerHTML = info["rsp"]["model"];
	document.getElementsByName("cPUModel")[0].innerHTML = info["rsp"]["cPUModel"];
	document.getElementsByName("mobileOperatingSystem")[0].innerHTML = info["rsp"]["mobileOperatingSystem"];
	document.getElementsByName("rearCamera")[0].innerHTML = info["rsp"]["rearCamera"];
	document.getElementsByName("screenSize")[0].innerHTML = info["rsp"]["screenSize"];
	document.getElementsByName("_4GNetworkSystem")[0].innerHTML = info["rsp"]["c4GNetworkSystem"];
	document.getElementsByName("CPUFrequency")[0].innerHTML = info["rsp"]["cPUFrequency"];
	document.getElementsByName("SIMCardSize")[0].innerHTML = info["rsp"]["sIMCardSize"];
	document.getElementsByName("packingList")[0].innerHTML = info["rsp"]["packingList"];




	/**
		 * 显示介绍图片
		 */
		html = $.ajax({
			type: "POST",
			url: "/src/"+info["rsp"]["id"],
			contentType:"application/json",
			dataType:"json",
			async: false
		}).responseText;
		srcs = JSON.parse(html);
		imgs = document.getElementsByName("srcs")[0];
		src_demo = document.getElementsByName("src")[0].cloneNode(true);
		imgs.innerHTML="";
		for (i in srcs){
			src = src_demo.cloneNode(true);
			src.setAttribute("src",srcs[i]);
			imgs.appendChild(src);
		}

	/**
	 * 显示所有版本
	 */
	html = $.ajax({
		type: "POST",
		url: "/version/relationId/"+info["rsp"]["id"],
		contentType:"application/json",
		dataType:"json",
		async: false
	}).responseText;
	version = JSON.parse(html);
	items = document.getElementsByName("items")[0];
	item_demo = document.getElementsByName("item")[0].cloneNode(true);
	items.innerHTML="";
	for (i in version["rsp"]){
		item = item_demo.cloneNode(true);
		item.innerHTML = version["rsp"][i]["version"]+" "+version["rsp"][i]["color"];
		item.setAttribute("code",version["rsp"][i]["commodityCode"])
		item.addEventListener("click",function (evt) {
			this.className = "v-item on";
		},true);
		items.appendChild(item);
	}

	///img/{commodityId}
	/**
	 * 显示所有缩略图
	 */
	html = $.ajax({
		type: "POST",
		url: "/img/"+info["rsp"]["id"],
		contentType:"application/json",
		dataType:"json",
		async: false
	}).responseText;
	array = JSON.parse(html);
	imgSrcs = document.getElementsByName("imgSrcs")[0];
	img_demo = document.getElementsByName("imgSrc")[0].cloneNode(true);
	imgSrcs.innerHTML="";
	for (i in array){
		img = img_demo.cloneNode(true);
		img.children[0].src = array[i];
		img.addEventListener("mouseover",function (evt) {
			document.getElementsByClassName("big-show")[0].children[0].src = this.children[0].src;
		},true);
		imgSrcs.appendChild(img);
	}

	///shopDetail/{id}

	html = $.ajax({
		type: "POST",
		url: "/shopDetail/"+info["rsp"]["id"],
		contentType:"application/json",
		dataType:"json",
		async: false
	}).responseText;
	array = JSON.parse(html);
	document.getElementsByName("shopName")[0].innerText = array["rsp"]["name"];
	document.getElementsByName("shopEmail")[0].innerText = array["rsp"]["email"];
	document.getElementsByName("shopTel")[0].innerText = array["rsp"]["telephone"];

	imgSrcs.innerHTML="";
}
