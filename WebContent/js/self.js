/*var userName=getCookie("userName");
console.log(userName);
var userId=getCookie("userId");
console.log(userId);
var cy_brand_id=getCookie("brandId");
console.log(cy_brand_id);
$("#header-user").text(userName);



if(userId==null||userId==undefined){
	window.location.href = "login.html";
}*/

var loginUser=JSON.parse(localStorage.getItem("loginUser"));
if(loginUser==null||loginUser==undefined){
	window.location.href = "login.html";
}

function loginOut(){
	localStorage.removeItem("loginUser")
	 location.reload();
	console.log("dddd");
}

	

    








