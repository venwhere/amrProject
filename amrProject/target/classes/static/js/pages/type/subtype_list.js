$(function(){
	$("[id*=editBtn-]").click(edit);
}) 
function edit(){
	var stid = this.id.split("-")[1];
	$(this).on("click",function(){
		var title = $("#title-" + stid).val();
		$.post("pages/type/editSubtype.action",{stid:stid,title:title},function(data){
			operateAlert(data.trim() == "true" , "办公用品子分类信息修改成功！","办公用品子分类信息修改失败！") ;
		},"text") ; 
	}) ;
}