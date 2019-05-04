<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
<script type="text/javascript">
//上架
//全选全不选
	function checkAllNotBox(name,checked){
		$("input[name='"+name+"']").attr("checked",checked);
	}
	
	/* function isShowOroptDelete(name,brandId,isShow,pageNum,flag){
		alert(flag)
		//请至少选择一个
		var size = $("input[name='ids']:checked").size();
		if(size == 0){
			alert("请至少选择一个");
			return;
		}
		if(!pageNum){
			//你确定上架吗
			
			if(flag == 0 ? (!confirm("你确定下架吗?")) : (!confirm("你确定上架吗?"))){
				return;
			}
			//提交 Form表单
			$("#jvForm").attr("action","/brand/isShow.do");
			$("#jvForm").attr("method","post");
			$("#jvForm").submit();
		}
			//你确定删除吗
			if(!confirm("你确定删除吗？")){
				return;
			}
			//提交 Form表单
			$("#jvForm").attr("action",'deleteBatch.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum');
			$("#jvForm").attr("method","post");
			$("#jvForm").submit();
		
	} */
	 //function isShow(flag,name,brandId,isShow,pageNum){
		function isShow(flag){
	
		//请至少选择一个
		var size = $("input[name='ids']:checked").size();
		if(size == 0){
			alert("请至少选择一个");
			return;
		}
		//你确定删除吗
		if(flag === 1 ? (!confirm("你确定上架吗？")):(!confirm("你确定下架吗？"))){
			return;
		}
		//提交 Form表单
		//$("#jvForm").attr("action","isShow.do?isStandOrDown="+flag+"&name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum);
		$("#jvForm").attr("action","isShow.do?isShow="+flag);
		$("#jvForm").attr("method","post");
		$("#jvForm").submit();
		
	} 
	
	//多项删除携带查询参数
	function optDelete(name,brandId,isShow,pageNum){
		var size = $("input[name='ids']:checked").size();
		if(size==0){
			alert("请至少选择一个！");
			return;
		}
		if(!confirm("你确定删除吗？")){
			return;
		}

		$("#jvForm").attr("action","deleteBatch.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum);
		$("#jvForm").attr("method","post");
		$("#jvForm").submit();
		/* $("#jvForm")[0].action="deleteBatch.do?name="+name+"&brandId="+brandId+"&isShow="+isShow+"&pageNum="+pageNum;
		$("#jvForm").submit(); */
	} 
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="window.location.href='showAdd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="get" style="padding-top:5px;">
名称: <input type="text" name="name" value="${name }"/>
	<select name="brandId">
		<option value="">请选择品牌</option>
		<c:forEach items="${ brands}" var="brand">
			<option value="${brand.id }">${brand.name }</option>
		</c:forEach>
	</select>
	<script>
			$("select[name='brandId']").val("${brandId}");
	</script>
	<select name="isShow">
		<option value="1">上架</option>
		<option value="0">下架</option>
	<!-- 	<option selected="selected" value="0">下架</option> -->
	</select>
	<script>
		$("select[name='isShow']").val("${isShow}");
	</script>
	<input type="submit" class="query" value="查询"/>
</form>
<form id="jvForm">
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkAllNotBox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>图片</th>
			<th width="4%">新品</th>
			<th width="4%">热卖</th>
			<th width="4%">推荐</th>
			<th width="4%">上下架</th>
			<th width="12%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${productPage.result }" var="product">
		<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
			<td><input type="checkbox" name="ids" value="${product.id}"/></td>
			<td>${product.id}</td>
			<td align="center">${product.name}</td>
			<td align="center">
			<c:forTokens items="${product.imgUrl}" delims="," var="imgUrl" begin="0" end="0" >
				<img width="50" height="50" src="${imgUrl }"/>
			</c:forTokens>
			</td>
			<td align="center">${product.isNew}</td>
			<td align="center">${product.isHot}</td>
			<td align="center">${product.isCommend}</td>
			<td align="center">${product.isShow}</td>
			<td align="center">
			<a href="#" class="pn-opt">查看</a> | <a href="#" class="pn-opt">修改</a> | 
			<a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="singleDelete.do?productId=${product.id }
			&name=${name }
			&brandId=${brandId}
			&isShow=${isShow}
			&pageNum=${productPage.pageNum}">删除</a> |
			 <a href="../sku/list.do?productId=${product.id }" class="pn-opt">库存</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="page pb15">
	<span class="r inb_a page_b">
	<a href="list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=1">
		<font size="2">首页</font>
	</a>
	
	<!-- 分页工具条前五后的效果 -->
	<!-- 上一页：不是第一页，显示上一页 -->
	<c:if test="${productPage.pageNum !=1 }">
		<a href="list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=${productPage.pageNum-1}">上一页</a>
	</c:if>
	
	<!-- 如果总页数<10,开始页1，结束页总页数 -->
	<c:if test="${productPage.pages<10 }">
		<c:set var="start" value="1"></c:set>
		<c:set var="end" value="${productPage.pages }"></c:set>
	</c:if>
	
	<!-- 如果总页数>=10 -->
	<c:if test="${productPage.pages>=10 }">
	<!-- 如果当前页-5<1,开始页1，结束页10 -->
		<c:if test="${productPage.pageNum-5<1}">
			<c:set var="start" value="1"></c:set>
			<c:set var="end" value="10"></c:set>
		</c:if>
	<!-- 如果当前页-5>=1并且当前页+4<=总页数，开始页：当前页-5，结束页：当前页+4 -->
		<c:if test="${productPage.pageNum-5 >= 1 && productPage.pageNum+4 <= productPage.pages }">
			<c:set var="start" value="${productPage.pageNum - 5 }"></c:set>
			<c:set var="end" value="${productPage.pageNum+4 }"></c:set>
		</c:if>
	<!--如果当前页+4>=总页数，开始页：总页数-9，结束页：总页数  -->
		<c:if test="${productPage.pageNum+4 > productPage.pages }">
			<c:set var="start" value="${prductPage.pageNum-9 }"></c:set>
			<c:set var="end" value="${productPage.pages }"></c:set>
		</c:if>
	</c:if>
	
	<!-- 显示页码 如果是当前页标红显示 -->
	<c:forEach begin="${start }" end="${end}" step="1" var="i">
		<c:if test="${productPage.pageNum == i }">
			<font color="red" size="2">${i }</font>
		</c:if>
	<!--  如果不是当前页超链接显示-->
		<c:if test="${productPage.pageNum != i }">
			<a href="list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=${i}">${i }</a>
		</c:if>	
	</c:forEach>
	
	<!-- 下一页：不是最后一页，显示下一页 -->
	<c:if test="${productPage.pageNum != productPage.pages }">
		<a href="list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=${productPage.pageNum+1}">下一页</a>
	</c:if>
	
	<!-- 尾页 -->
	<a href="list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=${productPage.pages}"><font size="2">尾页</font></a>
	
		共<var>${productPage.pages }</var>页 到第<input type="text" size="3" id="PAGENO"/>页 <input type="button" onclick="javascript:window.location.href = 'list.do?name=${name }&brandId=${brandId}&isShow=${isShow}&pageNum=' + $('#PAGENO').val() " value="确定" class="hand btn60x20" id="skip"/>
	
	</span>
</div>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete('${name}','${brandId }','${isShow }','${productPage.pageNum }');"/>
<input class="add" type="button" value="上架" onclick="isShow(1);"/><input class="del-button" type="button" value="下架" onclick="isShow(0);"/></div>
<<%-- div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete('${name}','${brandId }','${isShow }','${productPage.pageNum }');"/>
<input class="add" type="button" value="上架" onclick="isShow();"/><input class="del-button" type="button" value="下架" onclick="isHide();"/></div> --%>
</form>
</div>
</body>
</html>