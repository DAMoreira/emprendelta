<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<head>
<jsp:include page="headBlock.jsp"></jsp:include>
<style>
.box{display:none}
.active{display:block}
</style>

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article class="content clearfix row-fluid">
		<div class="field field-name-body field-type-text-with-summary field-label-hidden">
			<div class="field-items">
				<div class="field-item even" property="content:encoded">
<div class="row" id="login">
<div class="col-md-1"></div>
<div class="col-md-5">

 <section >
				<div class="col-md-2"></div>
				<div class="col-md-8">
	        		<h2 class="m-t-0 m-b-15"><s:text name="reactivation.title"/></h2>
	         		<p><s:text name="reactivation.text"/><a href="/sendActivation"><s:text name="reactivation.activate"/></a></p>
				</div>
				<div class="col-md-2"></div>
			</section>
</div>
<div class="col-md-1"></div>
</div>

</div>

	<div class="terms"></div>
	</div>

	<div class="clearfix">
		<div class="links"></div>

	</div>

	</div>
	</div>
	</div>
	</div>



	</div>


	</div>
	</div>
	<!--content area-->
	</div>
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');
	  ga('create', 'UA-78742279-1', 'auto');
	  ga('send', 'pageview');
	  
	</script>
</body>
</html>