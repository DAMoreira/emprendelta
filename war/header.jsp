<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-TJJVXC"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-TJJVXC');</script>
<!-- End Google Tag Manager -->
<div class="row-fluid" style="border-bottom:solid 3px #529228">
	<div  class="span3">
		<div class="region region-left-sidebar">
			<div id="block-block-88" class="clearfix block block-block">
				<div class="titlecontainer">
					<h3 class="blocktitle">
						<img alt="" src="/img/logo.jpg"
							style="width: 300px; height: 87px;">
					</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="login pull-right">
	<s:if test="%{#session.user==null}">
		<a href="/login">Acceder</a>
	</s:if>
	<s:else>
		<s:property value="#session.user.name" />
	</s:else>
	</div>
</div>
	<div id="zone2" class="row-fluid">
		<div id="left-sidebar" class="span3">
			<div class="region region-left-sidebar">
				<div id="block-block-88" class="clearfix block block-block">

					<div class="content">
						<ul class="menu">
							<li class=" leaf"><a href="/">Que hacemos</a></li>
							<li class="last leaf"><a href="/manual-del-emprendedor">Manual del Emprendedor</a></li>
							<li class="leaf "><a href="/material-de-interes">Material de Inter&eacute;s</a></li>
							<li class="leaf active-trail"><a class="active-trail active" href="/novedades">Novedades</a></li>
							<li class="leaf"><a href="/consultorio-para-emprendedores">Consultorio para emprendedores</a></li>
							<li class="leaf"><a href="/registro-de-emprendedores">Registro de emprendedores</a></li>
							<li class="leaf"><a href="/contacto">Contacto</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id="content-area" class=" span9 ">
			<div class="inner  inner-no-right-sidebar ">
				<div id="content-region">
					<div class="tabs"></div>
					<div class="region region-content">
						<div id="block-system-main" class="clearfix block block-system">
							<div class="content">
								<div id="node-257" class="node node-page iteration-1"
									about="/?q=emprendelta" typeof="foaf:Document">
									<span property="dc:title" content="Emprendelta"
										class="rdf-meta element-hidden"></span>
									<div class="node-user-picture"></div>
									<div class="meta">
