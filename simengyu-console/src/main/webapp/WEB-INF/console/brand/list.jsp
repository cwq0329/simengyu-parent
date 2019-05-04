<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ include file="../head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
<script>
	//全选全不选
	function checkAllOrNotBox(name,checked){
	$("input[name='"+name+"']").attr("checked",checked); 
		/* $("#tableList input[type='checkbox']").attr("checked",checked); */
	}
	//多项删除 参数：品牌名称、是否可用、当前页
	function optDelete(name, isDisplay, pageNum) {
		//获得有效的选择复选框的数量
		var size = $("input[name='ids']:checked").size();
		if (size == 0) {
			alert('请至少选择一个您要删除的品牌');
			return;
		}
		//你确定删除吗？
		if (!confirm("你确定删除吗？")) {
			return;
		}

		//开始删除 	 提交表单
		/* $("#hidd").val($.trim(name)); */
		$("#formList")[0].action ='doBatchDelete.do?name=' + name + '&isDisplay='
				+ isDisplay + '&pageNum=' + pageNum;
		$("#formList").submit();
	}

		
	

</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 品牌管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='add.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="list.do" method="get" style="padding-top:5px;">
品牌名称: <input type="text" name="name" value="${name}"/>
	<select name="isDisplay">
		
		<%-- <option value="1" <c:if test="${isDisplay==1}">selected="selected"</c:if>  >是</option>
		<option value="0" <c:if test="${isDisplay==0}">selected="selected"</c:if>  >否</option> --%>
		<option value="">是否可用</option>
		<option value="1">是</option>
		<option value="0">否</option>
	<script>
		$("select[name='isDisplay']").val("${isDisplay}");
	</script>
	</select>
	<input type="submit" class="query" value="查询"/>
</form >
<form id="formList" method="post">
<table id="tableList" cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="checkAllOrNotBox('ids',this.checked)"/></th>
			<th>品牌ID</th>
			<th>品牌名称</th>
			<th>品牌图片</th>
			<th>品牌描述</th>
			<th>排序</th>
			<th>是否可用</th>
			<th>操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
	<c:forEach items="${pageBrand.result}" var="brand">
		<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'">
			<td><input type="checkbox" value="${brand.id }" name="ids"/></td>
			<td align="center">${brand.id }</td>
			<td align="center">${brand.name }</td>
			<td align="center"><img width="40" height="40" src="${brand.imgUrl }"/></td>
			<td align="center">${brand.description }</td>
			<td align="center">${brand.sort }</td>
			<td align="center">
			<c:if test="${brand.isDisplay ==1}">是</c:if>
			<c:if test="${brand.isDisplay ==0 }">否</c:if>			
			</td>
			<td align="center">
			<a class="pn-opt" href="showEdit.do?brandId=${brand.id }">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="singleDelete.do?brandId=${brand.id }
			&pageNum=${pageBrand.pageNum}&name=${name}&isDisplay=${isDisplay}">删除</a>
			<%-- <a class="pn-opt" href="showEdit.do?brandId=${brand.id }">修改</a> | <a class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}" href="singleDelete.do?brandId=${brand.id }
			&pageNum=${pageBrand.pageNum}&name=${name}&isDisplay=${isDisplay}">删除</a> --%>
			</td>
		</tr>
	
		</c:forEach>
	</tbody>
	<!-- <input id='hidd' type="hidden" name="name" value=""></input> -->
</table>
</form>
<div class="page pb15">
		<span class="r inb_a page_b">
		<a href="list.do?name=${name}&isDisplay=${isDisplay}&pageNum=1">	
			<font size="2">首页</font>
		</a>
		
		<c:if test="${pageBrand.pageNum <= 1 }">
			<font size="2">上一页</font>
		</c:if>
		
		<c:if test="${pageBrand.pageNum > 1 }">
			<a href="list.do?name=${name}&isDisplay=${isDisplay}&pageNum=${pageBrand.pageNum-1}">
				<font size="2">上一页</font>
			</a>
		</c:if>
		
		<c:forEach begin="1" end="${pageBrand.pages }" var="ps">
			<c:if test="${pageBrand.pageNum == ps }">
				<strong>${ps }</strong>
			</c:if>
			<c:if test="${pageBrand.pageNum != ps }">
				<a href="list.do?name=${name}&isDisplay=${isDisplay}&pageNum=${ps}">
					${ps }
				</a>
			</c:if>
		</c:forEach>
			
		<c:if test="${pageBrand.pageNum>=pageBrand.pages}">
			<font size="2">下一页</font>
		</c:if>
		
		<c:if test="${pageBrand.pageNum < pageBrand.pages }">
			<a href="list.do?name=${name}&isDisplay=${isDisplay}&pageNum=${pageBrand.pageNum+1}">
				<font size="2">下一页</font>
			</a>
		</c:if>
		
		<a href="list.do?name=${name}&isDisplay=${isDisplay}&pageNum=${pageBrand.pages}"><font size="2">尾页</font></a>
		
		共<var>${pageBrand.pages}</var>页 到第<input type="text" size="3" id="PAGENO"/>页 <input type="button" onclick="javascript:window.location.href ='list.do?name=${name}&isDisplay=${isDisplay}&pageNum=' + $('#PAGENO').val() " value="确定" class="hand btn60x20" id="skip"/>
	
	</span>
</div>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除"
 onclick="optDelete('${name}','${isDisplay}','${pageBrand.pageNum}');"/></div>
</div>
</body>
</html>