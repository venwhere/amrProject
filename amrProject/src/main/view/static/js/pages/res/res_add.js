$(function() { 
	$("#type\\.tid").on("change",function(){//这是一个改变事件
		var tid = $(this).val() ; //取得被选择的option的中的value值
		if (/^\d+$/.test(tid)) {//进行正则验证，只能是一个整数
			$.post("pages/type/getSubtype.action",{tid:tid},function(data){
				$("#subtype\\.stid option").remove();//把之前出现的option项删除
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
		messages: {
			"type.tid" : "必须选择购买商品的分类" ,
			"subtype.stid" : "必须选择购买商品的子分类" 
		},
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
				required : true
			},
			"subtype.stid" : {
				required : true
			},
			"price" : {
				required : true ,
				number : true
			},
			"pic" : {
				required : true
			}
		}
	});
})