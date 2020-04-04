
function editType() {
	let file = $("#file").val();
	if (!file) {
		let filename = file.substr(file.lastIndexOf("."));
		if (filename != '.xls') {
			alert("请上传xls格式的文件");
		}
	}
}


//开局加载待审核公司数
$(function() {
	$.get("/toBeAudited",function(data,status){
	      $("#newlyAdded").html(data) 
	    });
});