$(function() { 
	$("#level\\.lid").on("change",function(){
		$("#salary").removeData("previousValue") ;	// 删除指定元素中的之前的数据
	}) ; 
	$("#myform").validate({
		debug : true, // 取消表单的提交操作
		submitHandler : function(form) {
			form.submit(); // 提交表单
		},
		errorPlacement : function(error, element) {
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1, function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});

			})
		},
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",
		messages : { 
			"salary" : "输入的工资必须符合该员工的级别！" ,
			"eid" : "该雇员编号已经存在，请重新分配！"
		} ,
		rules : {
			"name" : {
				required : true
			},
			"eid" : {
				required : true,
				digits : true,
				remote : {
					url : "pages/admin/checkEid.action", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "html", // 接受数据格式
					data : { // 要传递的数据
						eid : function() {
							return $("#eid").val();
						} 
					},
					dataFilter : function(data, type) {
						if (data.trim() == "true")
							return true;
						else
							return false;
					}
				}
			},
			"dept.did" : {
				required : true
			},
			"level.lid" : {
				required : true
			},
			"password" : {
				required : true
			},
			"phone" : {
				required : true
			},
			"photo" : {
				required : true
			},
			"salary" : {  //对薪资的填写进行验证
				required : true ,//不能为空
				digits : true ,//只能是整数
				remote : {
					url : "pages/level/checkSalary.action", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "html", // 接受数据格式
					data : { // 要传递的数据
						salary : function() {  //取得输入的薪资
							return $("#salary").val();
						} ,
						lid : function() { //取得当前选中的薪资的等级
							return $("#level\\.lid option:selected").val();
						}
					},
					dataFilter : function(data, type) {
						if (data.trim() == "true")
							return true;
						else
							return false;
					}
				}
			}
		}
	});
})