$(function() { //为两个按钮绑定事件
		$("#passBut").click(audit);
		$("#refBut").click(audit);
})
function audit() {
	var  status=$(this).attr("data-status");//取得绑定了该函数的元素的data-status属性值
	$.post("pages/purchase/audit.action",{pid:pid,status:status},function(data){
		if(status==1){
		   operateAlert(data.trim()== "true","购入申请通过！","审核中出现了异常，请检查！") ;
		}else if(status==2){
		   operateAlert(!data.trim()== "true","购入申请拒绝！","购入申请拒绝！") ;
		}
		$("#auditDiv").hide() ;
	},"text") ;
}