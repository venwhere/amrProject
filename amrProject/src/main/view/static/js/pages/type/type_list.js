$(function(){
	$("[id*=editBtn-]").each(function(){
		var tid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			// console.log("***** tid = " + tid) ;
			var title = $("#title-" + tid).val() ;
			$.post("pages/type/edit.action",{tid:tid,title:title},function(data){
				operateAlert(data.trim() == "true" , "办公用品分类信息修改成功！","办公用品分类信息修改失败！") ;
			},"text") ; 
		}) ;
	})
})