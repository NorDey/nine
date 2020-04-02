//获取DOM对象
function e(str){
	return document.getElementById(str);
}

//动态添加有样式的内容
function appendHtml(obj,str,className){
    obj.innerHTML = str;
    obj.style.color = className;
}

//验证学生号必填
function checkStudent_id(){
	var flag = true;
	var odom = e("studentId");//输入框DOM对象
	var omsg = e("studentId_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}

//验证姓名
function checkName(){
	var flag = true;
	var odom = e("studentName");//输入框DOM对象
	var omsg = e("studentName_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}

//验证密码
function checkPwd(){
	var flag = true;
	var odom = e("password");//输入框DOM对象
	var omsg = e("password_span");//信息提示DOM对象
	var reg  = /^[0-9A-Za-z]{6,}$/;  
	if(odom.value == ""){
		appendHtml(omsg,"密码不能为空","red");
        flag =  false;
    }else if(!reg.test(odom.value)){
    	appendHtml(omsg,"格式错误","red");
    	flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}

//确认密码
function checkconfirm(){
	var flag = true;
	var od1	 = e("password");//第一次输入框
	var odom = e("confirm");//输入框DOM对象
	var omsg = e("confirm_span");//信息提示DOM对象
	if(odom.value != od1.value){
		appendHtml(omsg,"密码不一致","red");
        flag =  false;
    }else if(odom.value == ""){
    	appendHtml(omsg,"不能为空","red");
    }else{
    	appendHtml(omsg,"正确","gray");
        flag =  true;
    }
	return flag;
}

//验证电话
function checkphone(){
	var flag = true;
	var odom = e("phonenumber");//输入框DOM对象
	var omsg = e("phonenumber_span");//信息提示DOM对象
	var reg =/^1[3456789]\d{9}$/;
	if(odom.value == ""){
		appendHtml(omsg,"电话不能为空","red");
        flag =  false;
    }else if(!reg.test(odom.value)){
    	appendHtml(omsg,"格式错误","red");
    	flag =  false;
    }else{
    	appendHtml(omsg,"正确","gray");
        flag =  true;
    }
	return flag;
}

//验证毕业生注册表单
function OncheckFormStudent(){
	//alert("修改成功");
    var ids  = ["checkStudent_id()","checkName()","checkPwd()","checkconfirm()","checkphone()"];
    var sum = 0;
    for(var i = 0; i<ids.length ;i++){
        if ( eval(ids[i]) )sum++;
    }
    if(ids.length == sum ? true : false){
    	return true;
    	//window.location.href='index.html';
    }else{
    	alert("注册失败");
    	return false;
    }
    return ids.length == sum ? true : false; 
}

//验证工商注册号不能为空
function checkRegistrationId(){
	var flag = true;
	var odom = e("registrationId");//输入框DOM对象
	var omsg = e("registrationId_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}
//验证企业名称不能为空
function checkEnterpriseName(){
	var flag = true;
	var odom = e("enterpriseName");//输入框DOM对象
	var omsg = e("enterpriseName_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}
//验证公司地址不能为空
function checkAddress(){
	var flag = true;
	var odom = e("address");//输入框DOM对象
	var omsg = e("address_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}
//验证公司注册资本不能为空
function checkRegisteredCapital(){
	var flag = true;
	var odom = e("registeredCapital");//输入框DOM对象
	var omsg = e("registeredCapital_span");//信息提示DOM对象
	if(odom.value == ""){
		appendHtml(omsg,"不能为空","red");
        flag =  false;
    }else{
    	appendHtml(omsg,"通过","gray");
        flag =  true;
    }
	return flag;
}

//验证企业注册表单
function OncheckFormCompany(){
	//alert("修改成功");
    var ids  = ["checkRegistrationId()","checkEnterpriseName()","checkAddress()","checkRegisteredCapital()"];
    var sum = 0;
    for(var i = 0; i<ids.length ;i++){
        if ( eval(ids[i]) )sum++;
    }
    if(ids.length == sum ? true : false){
    	return true;
    	//window.location.href='index.html';
    }else{
    	alert("注册失败");
    	return false;
    }
    return ids.length == sum ? true : false; 
}