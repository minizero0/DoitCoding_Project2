<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 초기화하기 -->
<style type="text/css">
	.main_layout{
        width:1380px;
		margin : auto;
    }
	*{
		font-family: "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
	}	
	a{
		text-decoration: none;
	}	
	li{
		list-style: none;
	}
</style>
<!-- 상단 구성하기 -->
<style type="text/css">
	#logo{
		width: 370px;
		height: 110px;
		padding-top : 5px;
	}
	.main_user{
		float : right;
		padding-top : 60px;
		padding-right : 15px;
	}
	.main_user a{
		color : #091140;
		position : relative;
		font-size : 11px;
		font-style : bold;
		margin :-18px;
		padding-right : 20px;
	}
	.main_user img{
		position : relative;
		width : 46px;
		height : 45px;
		bottom : 15px;
		left : 47px;
	}
	#login{
		right : 7px;
		bottom : 1px;
	}
	#login img{
		padding-right : 6px;
	}
	.search{
		overflow : hidden;
		position: relative;
  		top: 6px;
  		right : 15px;
		float : right;
		width : 250px;
		height : 40px; 
      	border : 2px solid white; 
      	border-radius : 25px 25px 25px 25px;
	}
	.search_txt{
		position: relative;
		color : white;
		top: -10px;
  		right : -15px;
		width : 175px;
		height : 30px; 
		border: none; 
		font-size : 13px;
		background-color:rgba(255, 255, 255, 0);
	}
	.search_btn{
		position : relative;
  		right : -25px;
	}
</style>
<!-- main-header 구성하기 -->
<style type="text/css">
	#navigation{
		position: sticky;
		top: 0;
	}	
	#navigation ul{
		background-color : #08428C;
		border-radius : 5px 5px 0px 0px;
		list-style-type: none;
	    margin: 0;
	    padding: 0;
	}
	#navigation ul:after{
	    content:'';
	    display: block;
	    clear:both;
	}
	#navigation ul > li{
		list-style : none;
		display: inline-block;
	    text-align: center;
	    padding: 14px 45px;
	}
	#navigation li.home a{
		color: #F2AE30;
		font-weight : 600;
		font-size : 17px;
	}
	#navigation li > a{
		font-size : 16px;
		color : white;
		height: 100px;
		text-decoration: none;
		line-height : 30px;
	}
	#navigation li:hover:not(.home){
		transition-duration: 0.4s;
		border-bottom: 5px solid #F2AE30;
		padding-bottom : 9px;
	}
	#navigation ul li:hover ul {
		display: block;
	}
	#poster1{
		max-width: 100%;
		width: 1400px;
		height: 570px;
		display : block;
	}
	hr{
		border : 0;
		height : 2.5px;
		background-color : #08348C;
	}
</style>
<!-- 본문 구성하기 -->
<style type="text/css">
	#main-content h2{
		padding-left : 10px;
		color : #F2AE30;	
	}
	#main-content ul{
		text-align : center;
	}
	#main-content ul>li{
		float : left;
		border : 1.5px solid #dcdcdc; 
      	border-radius : 25px 25px 25px 25px;
      	width : 80px;
      	height : 25px;
      	padding : 5px;
      	margin-right : 15px;
	}
	#main-content li a{
		font-size : 14px;
		color : #091140;
		height: 100px;
		text-decoration: none;
	}
	#main-content li:hover{
		transition-duration: 0.5s;
		background: #08428C;
	}
	#main-content li:hover a{
		color : white;
	}
</style>
<!-- footer 구성하기 -->
<style type="text/css">
	#main-footer{
		padding-top: 100px;
	}
	footer{
		line-height: 100px;
		background: #08428C;
		color: white;
		font-size: 14px;
		text-align: center;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script >
	$(function(){
		var str = sessionStorage.getItem("custid");			//login된 아이디 가져오기
	})
</script>
<!--<link rel="stylesheet" type="text/css" href="mainTest.css"> -->
</head>
<body class="main_layout">
 		<img src="./image/logo.png" id="logo">
 		<div class="main_user">
 				<a href="loginTest.html" id="login"><img src="./image/loginicon.png">로그인</a>
 				<a href="signupTest.html"><img src="./image/signupicon.png">회원가입</a>
 				<a href="mypageTest.html"><img src="./image/mypageicon.png">마이페이지</a>
 		</div>
 	    <nav id="navigation">
              <form action="" method="post" class="search">
				   <input class="search_txt" type="search" placeholder="검색어 입력...">
				   <a class="search_btn" href="example.html"><img  src="./image/searchicon.png" width="35" height="35"></a>
			  </form>
              <ul>
                  <li class="home"><a href="mainTest.html">홈</a></li>
                  <li><a href="#">시사회</a></li>	
                  <li><a href="#">연극</a></li>
                  <li><a href="#">뮤지컬</a></li>
                  <li><a href="#">콘서트</a></li>
             </ul>
        </nav>
     <div id="main-header">
            <header> 
                 <img src="./image/monster.jpg" id="poster1">
            </header>
     </div>
     <hr>
     <div id="main-content">
     	<h2>장르별랭킹</h2>
     	<ul>
               	<li><a href="#">시사회</a></li>	
                <li><a href="#">연극</a></li>
                <li><a href="#">뮤지컬</a></li>
                <li><a href="#">콘서트</a></li>
     	</ul>
     	<br>
     </div>
     <%
		String str = (String)session.getAttribute("custid");
		System.out.print("sessionid:"+str);
	%>
     <div id="main-footer">
		<footer>
			<div>Footer</div>
		</footer>
	</div>	  
</body>
</html>