<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>
<script>
function showUpdateSku(skuId){
	//修改该库存的文本框为可用
	$("#m"+skuId).attr("disabled",false);
	$("#p"+skuId).attr("disabled",false);
	$("#i"+skuId).attr("disabled",false);
	$("#l"+skuId).attr("disabled",false);
	$("#f"+skuId).attr("disabled",false);
}

function doUpdateSku(skuId){
	//保存时修改该库存的文本框为不可用
	$("#m"+skuId).attr("disabled",true);
	$("#p"+skuId).attr("disabled",true);
	$("#i"+skuId).attr("disabled",true);
	$("#l"+skuId).attr("disabled",true);
	$("#f"+skuId).attr("disabled",true);
	//需传的参数
	var param={
			"marketPrice":$("#m"+skuId).val(),
			"price":$("#p"+skuId).val(),
			"stock":$("#i"+skuId).val(),
			"upperLimit":$("#l"+skuId).val(),
			"deliveFee":$("#f"+skuId).val(),
			"id":skuId
			
			
	}
	//异步调用将修改的数据保存到库中
	$.post("updateSku.do",param,function(data){
		if(data=="1"){
			alert("修改成功！");
		}
	});
}





</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${skus }" var="sku">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="${sku.id }"/></td>
				<td>${sku.product_id }</td>
				<td align="center">${sku.colorName }</td>
				<td align="center">${sku.size }</td>
				<td align="center"><input type="text" id="m${sku.id }" value="${sku.market_price }" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="p${sku.id }" value="${sku.price }" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="i${sku.id }" value="${sku.stock }" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="l${sku.id }" value="${sku.upper_limit }" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" id="f${sku.id }" value="${sku.delive_fee}" disabled="disabled" size="10"/></td>
				<td align="center">不是</td>
				<td align="center"><a href="javascript:showUpdateSku(${sku.id })" class="pn-opt">修改</a> | <a href="javascript:doUpdateSku(${sku.id })" class="pn-opt">保存</a></td>
			</tr>
	</c:forEach>
	</tbody>
</table>
</form>
</div>
</body>
</html>