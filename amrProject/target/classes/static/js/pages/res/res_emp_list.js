$(function() {
	$("[id*=retBut-]").each(function() {
		var tkid = this.id.split("-")[1] ;
		$(this).on("click",function(){
			$.post("pages/res/editRflag.action",{tkid:tkid},function(data){
				operateAlert(data.trim() == "true","归还申请成功！","归还申请失败！") ;
				if (data.trim() == "true") {
					$("#status-" + tkid).html("<span class='text-info'>归还申请</span>") ;
					$("#butDiv-" + tkid).hide() ;
				}
			},"text") ;
		}) ;
	})
})