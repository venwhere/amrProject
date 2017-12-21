$(function() {
	$("#selectAll").on("click",function(){
		checkboxSelectAll('tkid',this.checked) ;
	}) ;
	// 实现整体修改操作的功能
	$(editBtn).on("click",function(){
		// 定义一个数组，保存所有需要被删除的gid数据
		var delGid = new Array() ;
		var foot = 0 ;
		var data = "" ; // 实现最终数据拼凑的字符串
		$("[id*=amount-]").each(function(){
			var gid = this.id.split("-")[1] ;
			var amount = this.value ;
			data += gid + ":" + amount + "|" ;
			if (amount == "0") {
				delGid[foot ++] = gid ;
			}
		}) ;
		// 进行ajax异步数据处理操作
		$.post("pages/res/editTake.action",{data:data},function(data){
			operateAlert(true,"领取数量修改成功！","领取数量修改失败！") ;
			for (var x = 0 ; x < delGid.length ; x ++) {
				$("#res-" + delGid[x]).remove() ;
			}
		},"text") ;
	}) ;
	$("#rmBtn").on("click",function(){	// 绑定用户锁定操作
		var data = "" ;
		$(":checked").each(function() {
			if(this.id == "tkid") {	// 要删除的内容
				data += this.value + "|" ;
			}
		}) ;
		if (data != "") {
			$.post("pages/res/rmTake.action",{data:data},function(dataA){
				operateAlert(dataA.trim() == "true","待领商品移除成功！","待领商品移除失败！") ;
				var temp = data.split("|") ;
				console.log("** data = " + data) ;
				for (var x = 0 ; x < temp.length ; x ++) {
					$("#res-" + temp[x]).remove() ;
				}
			},"text") ;
		} 
	}) ;
	$("button[id*=add-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			$("#amount-" + gid).val(amount+1) ;
		})
	}) ;
	$("button[id*=sub-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		$(this).on("click",function(){
			var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
			if (amount > 0) {
				$("#amount-" + gid).val(amount - 1) ;
			}
		})
	}) ;
})
