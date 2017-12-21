$(function() { 
	$("#type\\.tid").on("change",function(){
		var tid = $(this).val() ;
		if (/^\d+$/.test(tid)) {	// 现在有内容选择
			$.post("pages/type/getSubtype.action",{tid:tid},function(data){
				$("#subtype\\.stid option").remove() ;
				for (var x = 0 ; x < data.length ; x ++) {
					var stid = data[x].stid ;
					var title = data[x].title ;
					$("#subtype\\.stid").append("<option value='"+stid+"'>" + title + "</option>") ;
				}
			},"json") ;
		} else {
			$("#subtype\\.stid option").remove() ;
		}
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
		rules : {
			"title" : {
				required : true,
				//remote : {
//									url : "check.jsp", // 后台处理程序
//									type : "post", // 数据发送方式
//									dataType : "html", // 接受数据格式
//									data : { // 要传递的数据
//										code : function() {
//											return $("#code").val();
//										}
//									},
//									dataFilter : function(data, type) {
//										if (data.trim() == "true")
//											return true;
//										else
//											return false;
//									}
				//}
			},
			"type.tid" : {
				required : true,
				digits : true
			},
			"subtype.stid" : {
				required : true
			},
			"price" : { 
				required : true
			},
			"salary" : {
				required : true ,
				digits : true
			}
		}
	});
})