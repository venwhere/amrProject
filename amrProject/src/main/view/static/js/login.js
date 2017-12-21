$(function() {
	$.backstretch("images/login_back.jpg");
	$("#imageCode").on("click",function(){
		$("#imageCode").attr("src","ImageCode?p=" + Math.random()) ;
	}) ;
	$("#myform").validate({
		debug : true,  
		submitHandler : function(form) {
			form.submit();  
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
			"eid" : {
				required : true ,
				number : true
			},
			"password" : { 
				required : true
			} ,
			"code" : {
				required : true ,
				remote : {
					url : "CheckCode", 
					type : "post", 
					dataType : "html", 
					data : {
						code : function() {
							return $("#code").val();
						}
					},
					dataFilter : function(data, type) {
						if (data.trim() == "true") {
							return true;
						} else { 
							return false;
						}
					}
				}
			}
		}
	});
//	$('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea')
//			.on('focus', function() {
//				$(this).removeClass('input-error');
//			});
//
//	$('.login-form').on(
//			'submit',
//			function(e) {
//				$(this).find(
//						'input[type="text"], input[type="password"], textarea')
//						.each(function() {
//							if ($(this).val() == "") {
//								e.preventDefault();
//								$(this).addClass('input-error');
//							} else {
//								$(this).removeClass('input-error');
//							}
//						});
//	
//				});
})
