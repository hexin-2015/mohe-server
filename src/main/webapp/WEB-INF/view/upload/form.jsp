<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="weixinsave" enctype="multipart/form-data">
	<input type="file" name="file" value="上传" id="imgFile"/>
	<input id="test" type="button" name="submit" value="上传"/>
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script>

$('#test').click(function(){
	 var files = $('#imgFile').prop('files');
	 var data = new FormData();
     data.append('imgFile', files[0]);
     data.append('imgType','plant');
     data.append('sessionid','1');
     //data.append('se','car');
     
     console.log(data.get('name'));
	$.ajax({  
	    /* headers: {  
	    	"content-type": "multipart/form-data"  
	    },  */ 
	    cache: false,
        processData: false,
        contentType: false,
	    type: "POST", 
	    url:"weixinsave",
	    data: data,
	    success: function (data) {  
	    	console.log(data);
	    },
	    failed: function(data){
	    	alert("失败")
	    }
	});  
	
	return false;
})

</script>

</body>
</html>