$(function() {
	$("[id*=editBtn-]").each(//选择出所有id的值包含了“editBtn-”的元素
			function() {  
				var did =this.id.split("-")[1]; //this表示绑定了该函数的元素，取得的是部门的编号
				$(this).on("click", function() { //绑定单击事件 
							// console.log("***** did = " + did) ;
							var title = $("#title-" + did).val();//取得的是输入的新的部门名称
							if (title !="") {//只有客户输入信息的情况才会执行
								$.post("pages/dept/edit.action", { //发送异步请求
									did : did,
									title : title
								}, function(data) {  //回调函数
								   //另外一个组件的函数
									operateAlert(data.trim() == "true",
											"部门名称修改成功！", "部门名称修改失败！");
								}, "text");
							} else {
								operateAlert(false, "部门名称修改成功！", "部门名称修改失败！");
							}
						});
			})
})