$(function() {
	$("[id*=res-]").each(function () {
		$("#applyDiv").show() ;
	}) ;
	$("#allPrice").text(calSum());
	//绑定全选事件
	$("#selectAll").click(selectAll)
	// 绑定数量修改事件
	$("#editBtn").click(editAmount)
	//绑定删除事件
	$("#rmBtn").click(remove);
	//绑定清单数量+1事件
	$("button[id*=add-]").each(function(){
		$(this).click(amountAdd);
	}) ;
	//绑定清单数量-1事件
	$("button[id*=sub-]").each(function(){
		$(this).click(amountSub);
	}) ;
})
//处理数量-1的函数
function amountSub(){
	var gid = this.id.split("-")[1] ; // 取得商品ID数据
//	$(this).on("click",function(){//加这语句会循环加多操作
		var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
		if (amount > 0) {
			$("#amount-" + gid).val(amount - 1) ;
			$("#allPrice").text(calSum()) ;
		}
//	})
}
//处理数量+1的函数
function  amountAdd(){
	var gid = this.id.split("-")[1] ; // 取得商品ID数据
//	$(this).on("click",function(){
		var amount = parseInt($("#amount-" + gid).val()) ;	// 直接取得value属性
		$("#amount-" + gid).val(amount+1) ;
		$("#allPrice").text(calSum()) ;
//	})
}

//处理全选的函数
function selectAll(){
    checkboxSelectAll('did',this.checked) ;
}  
//进行总金额的统计
function calSum() {
	var sumPrice = 0.0 ;	// 保存总价
	// 计算购买的商品的总价数据
	$("span[id*=price-]").each(function(){
		var gid = this.id.split("-")[1] ; // 取得商品ID数据
		var price = $(this).text() ;	// 取得文本内容
		var amount = $("#amount-" + gid).val() ;	// 直接取得value属性
		sumPrice += parseFloat(price) * parseInt(amount) ;
	}) ;
	return round(sumPrice,2) ;
}
//处理数量修改的函数
function editAmount(){
	var del = "" ; //用来保存数量为0的清单的编号
	var data = "" ;  //用来保存数量不为0的清单的编号和数量
	$("[id*=amount-]").each(function(){
		var gid = this.id.split("-")[1];//取得待购清单的编号
		var amount =this.value ;//取得的就是数量
		if (amount != "0") {  //判断数量是否为0
			data += gid + ":" + amount + "|" ;  //进行组装
		} else {//如果数量为0的时候直接组装编号
			del += gid + "|" ;  
		}
	}) ; 
	if (data != "") {//如果最终data不是空字符串进行数据的提交
		$.post("pages/res/editAmount.action",{updatestr:data,deletestr:del},function(data){
			operateAlert(data.trim() == "true","商品数量修改成功！","商品数量修改失败！") ;
			var temp = del.split("|") ;//
			for (var x = 0 ; x < temp.length ; x ++) {
				$("#res-" + temp[x]).remove() ;//如果在服务器端删除了数据，那么页面的样式也要删除
			}
			$("#allPrice").text(calSum()) ;//进行总金额统计
		},"text") ;
	} else {
		operateAlert(false,"商品数量修改成功！","商品数量修改失败！") ;
	}
}
//处理删除的函数
function  remove(){
	var del = "" ;
	$(":checked").each(function() {//选择出所有被选中状态的input元素
		if(this.id == "did") {     //只有id值是“did”的input才是我们需要的
			del += this.value + "|" ;//取得编号并且组装
		}
	}) ;
	if (del != "") {//判断是否有选中的项
		$.post("pages/res/rm.action",{deletestr:del},function(data){
			operateAlert(data.trim() == "true","商品信息删除成功！","商品信息删除失败！") ;
			//以下的操作的是实现页面的样式删除
			var temp = del.split("|") ;
			for (var x = 0 ; x < temp.length ; x ++) {
				$("#res-" + temp[x]).remove() ;
			} 
			$("#allPrice").text(calSum()) ;
		},"text") ;
	} else {
		operateAlert(false,"商品信息删除成功！","商品信息删除失败！") ;
	}
}