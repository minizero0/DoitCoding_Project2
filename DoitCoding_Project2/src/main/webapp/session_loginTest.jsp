<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		font-family: "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
	}	
	a{
		text-decoration: none;
	}	
	li{
		list-style: none;
	}
	div{
		 position: absolute;
		  top:50%;
		  left: 50%;
		  transform: translate(-50% , -50%)
	}
</style>
<!-- Background -->
<style type="text/css">
	#background{
		width : 500px;
		height : 550px;
		background-color : #f5f5f5;
		border : 1px solid #dcdcdc; 
		border-radius : 10px 10px 10px 10px;
		box-shadow : 10px 10px #dedede;
	}
	.main{
		position : relative;
		padding : 20px 10px;
		background-color : #08348C;
		border-radius : 10px 0px 0px 10px;
		top : 65px;
		right : 61px;
	}
	.main img{
		position : relative;
		top : 13px;
	}
</style>	
<!-- 로그인화면(center) -->
<style type="text/css">
	#logo{
		width: 370px;
		height: 110px;
	}
	h2{
		text-align : center;
		color : #08348C;
	}
	.user{
		font-weight: 500;
		position: relative;
		padding : 5px;
		margin : 10px;
		border : 2px solid #08348C;
		width : 250px;
		height :	30px;
		padding-left : 10px;
	}
	div ul{
		position: relative;
	}
	div ul li{
		position: relative;
		color : #a9a9a9;
	}
	ul li #custid,ul li #pwd{
		font-size : 15px;
		position: relative;
		width : 200px;
		height : 30px;
		padding-left : 10px;
		border: none;
		background-color:rgba(255, 255, 255, 0);
	}
	ul li #custid{
		left : 15px;
	}
	ul li #pwd{
		left : 6px;
	}
	#btn_login{
		font-size : 16px;
		width : 270px;
		height : 45px;
		background-color : #08348C;
		color : white;
		border : none;
		margin-left: 50px;
		margin-bottom : 130px;
		border-radius : 5px 5px 5px 5px;
	}
	#btn_login:hover{
		background-color : #F2AE30;
		color : #08348C;
		transition-duration: 0.5s;
	}
	.caption{
		width : 350px;
		margin-top : 140px;
		text-decoration: none;
	}
	.caption a{
		font-size : 14px;
		margin-left : 70px;
		color : #08348C;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script>
	$(function(){
		$("#form_login").submit(function(e){
			var url = "LoginCustomer";
			var data = $(this).serialize();
			var custid = $("#custid").val();
			
			$.ajax(url, {
				data:data,
				success:function(login_Flag){
					alert(login_Flag);
					if(login_Flag){
						sessionStorage.setItem("custid", custid);
					}
					
				}
			});
			e.preventDefault();
			
			
			
		}); // form_login이 제출되었을 때
		
	}) // 전체 $(function)

</script>
</head>
<body>
	<div id="background">
		<a class="main" href="mainTest.html">
		<img  src="./image/homeicon.png" width="40" height="40"></a>
	</div>
	<div>
		<form id="form_login">
			<img src="./image/logo.png" id="logo">
			<h2>Login</h2>
			<ul>
				<li class="user">ID<input id="custid" type="text" name="custid"></li>
				<li class="user">PW<input id="pwd" type="password" name="pwd"></li>
			</ul>
				<input type="submit" id="btn_login" value="Login" name="btn_login"><br>
		<div class="caption">
				<a href="signupTest.html">회원가입</a> <a href="#">ID/PW 찾기</a>
		</div>					
		</form>
	</div>	
</body>
</html>