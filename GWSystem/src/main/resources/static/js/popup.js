
function editType() {
	let file = $("#file").val();
	if (!file) {
		let filename = file.substr(file.lastIndexOf("."));
		if (filename != '.xls') {
			alert("请上传xls格式的文件");
		}
	}
}
