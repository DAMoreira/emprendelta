<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="not-ie" lang="en">
<!--<![endif]-->
<head>
<jsp:include page="headBlock.jsp"></jsp:include>
<style type="text/css">
.controls>*{width:100%}
.label{background-color:#ddd}
</style>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	<article class="content clearfix row-fluid">

		<div class="field field-name-body field-type-text-with-summary field-label-hidden">
			<div class="field-items">
				<div class="field-item even" property="content:encoded">
					<p>El Consultorio de emprendedores es un &aacute;mbito de apoyo y asistencia para la comunidad universitaria de FRD, interesada en recibir orientaci&oacute;n o ayuda para dar forma a su idea de negocio, as&iacute; como para fortalecer un emprendimiento en marcha.</p>
					<p>
						<strong style="line-height: 1.6em;">¿A qui&eacute;nes est&aacute; dirigido?</strong>
					</p>
					<p>
						<span style="line-height: 1.6em;">A toda la comunidad universitaria: alumnos, docentes, graduados, investigadores, no docentes, y que tengan una idea de negocio, un proyecto, o un emprendimiento en acci&oacute;n y necesiten orientaci&oacute;n.</span>
					</p>
					<p>
						<strong style="line-height: 1.6em;">¿Qu&eacute; servicios ofrece?</strong>
					</p>
					<p>
						<span style="line-height: 1.6em;">Contamos con una red de profesionales que pueden brindar informaci&oacute;n, orientaci&oacute;n y asesoramiento tanto para iniciar un emprendimiento como para mejorar un proyecto existente. Adem&aacute;s, brindamos informaci&oacute;n sobre sobre programas de apoyo a emprendedores existentes.</span>
					</p>
					<p>
						<strong style="line-height: 1.6em;">¿C&oacute;mo participar?&nbsp;</strong>
					</p>
					<p>
						<span style="line-height: 1.6em;">Los interesados en participar deber&aacute;n registr&aacute;rse previamente en “Registro de emprendedores” en el cual deber&aacute;n describir brevemente su idea/proyecto/negocio y luego completar el formulario debajo online, indicando los motivos espec&iacute;ficos de su consulta, inquietud o necesidad.</span>
					</p>
					<p>
						<strong style="line-height: 1.6em;">Consultas:</strong>
						<span style="line-height: 1.6em;"> <a href="mailto:emprendelta@frd.utn.edu.ar">emprendelta@frd.utn.edu.ar</a> – 03489-420400 int 5113</span>
					</p>
				</div>
			</div>
		</div>
	<s:if test="%{#session.user==null}">
		<div>
			<p>
				<span style="line-height: 1.6em;">Para realizar consultas debe <a href="/registro-de-emprendedores">Registrarse</a> o <a href="/login?redirectAction=consultorio-para-emprendedores">Acceder a su cuenta</a></span>
			</p>
		</div>
	</s:if>
	<s:else>
		<div>
			<s:actionmessage/>
			<s:actionerror/>
		</div>
		<form class="webform-client-form" action="/consulta" method="post">
			<div style="max-width:700px">
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-y-apellido">
					<s:textfield name="nombre" value="%{#session.user.name}" label="Nombre y Apellido" />
				</div>
				<div class="form-item webform-component webform-component-email" id="webform-component-mail">
					<s:textfield name="email" value="%{#session.user.email}" label="Mail" />
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-telefono">
					<s:textfield name="telefono" label="Teléfono" />
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-del-proyecto">
					<s:textfield name="nombreProyecto" label="Nombre del Proyecto" />
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-producto-o-servicio">
					<s:textfield name="productoServicio" label="Producto o Servicio" />
				</div>
				<div class="form-item webform-component webform-component-textarea" id="webform-component-texto-de-consulta">
					<s:textarea name="descripcion" label="Texto de consulta" />
				</div>
				<div class="form-actions form-wrapper" id="edit-actions">
					<s:submit value="Enviar"></s:submit>
				</div>
			</div>
		</form>
	</s:else>

	</article>
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