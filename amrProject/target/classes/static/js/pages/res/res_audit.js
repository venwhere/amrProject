$(function() {
	$("[id*=passBut-]").each(function() {
		var tkid = this.id.split("-") [1] ;
		$(this).on("click",function() {
			editStatus(tkid,1) ;
		}) ;
	}) ;
	$("[id*=refBut-]").each(function() {
		var tkid = this.id.split("-") [1] ;
		$(this).on("click",function() {
			editStatus(tkid,2) ;
		}) ;
	}) ;
	$("[id*=getBut-]").each(function() {
		var tkid = this.id.split("-") [1] ;
		$(this).on("click",function() {
			$.post("pages/res/editRdate.action",{tkid:tkid},function(data){
				operateAlert(data.trim() == "true","用品归还成功！","用品归还失败！") ;
				if (data.trim() == "true") {
					$("#audit-" + tkid).html("<span class='text-primary'>已归还</span>") ;
					$("#statusDiv-" + tkid).hide() ;
				}
			},"text") ; 
		}) ; 
	}) ;
})
function editStatus(tkid,status) {
//	console.log("tikid = " + tkid + "、status = " + status) ;
	$.post("pages/res/editAudit.action",{tkid:tkid,status:status},function(data) {
		operateAlert(data.trim() == "true","领取申请处理成功！","领取申请处理失败！") ;
		if (data.trim() == "true") {
			if (status == 1) {
				$("#audit-" + tkid).html("<span class='text-success'>审核通过</span>") ;
			} 
			if (status == 2) {
				$("#audit-" + tkid).html("<span class='text-danger'>审核拒绝</span>") ;
			}
			$("#statusDiv-" + tkid).hide() ;
		}
	} , "text") ;
}