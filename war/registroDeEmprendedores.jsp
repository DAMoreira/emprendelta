<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="not-ie" lang="en">
<!--<![endif]-->
<head>
<jsp:include page="headBlock.jsp"></jsp:include>
<style>
input,select,textarea{width:100%}
</style>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<article class="content clearfix row-fluid">
		<div class="field field-name-body field-type-text-with-summary field-label-hidden">
			<div class="field-items">
				<div class="field-item even" property="content:encoded">
					<p>
						<span style="line-height: 1.6em;">Contamos con un grupo de
							Mentores y Consultores que brindan un &aacute;mbito de apoyo y
							asistencia para la comunidad universitaria de FRD, interesada en
							recibir orientaci&oacute;n o ayuda para dar forma a su idea de negocio,
							as&iacute; como para fortalecer un emprendimiento en marcha.&nbsp;</span>
					</p>
					<p>
						<strong>Mentor&iacute;as</strong>
					</p>
					<p>Convocamos a empresarios de la zona que lograron desarrollar
						su emprendimiento con &eacute;xito a formar parte de un grupo de mentores
						que, acompa&ntilde;en, aconsejen, compartan sus habilidades, experiencias
						y contactos con los emprendedores de la comunidad universitaria.
						Adem&aacute;s, podr&aacute;n derivarlos con otros especialistas del Consultorio
						de Emprendedores para ver temas puntuales.</p>
					<p>
						<strong>Consultorio de emprendedores</strong>
					</p>
					<p>Los emprendedores podr&aacute;n enviar consultas desde la p&aacute;gina de
						la Facultad, registr&aacute;ndose previamente. Las mismas ser&aacute;n derivadas
						a distintos profesionales seg&uacute;n el tema planteado. Algunas cosas
						se resolver&aacute;n por esta v&iacute;a, y para otras, ser&aacute; necesario concertar
						alguna cita entre el emprendedor y el profesional.</p>
					<p>
						<strong>&iexcl;Registrate y forma parte de Emprendelta para recibir todos estos beneficios&#33;</strong>
						<registro>
						<strong> </strong></registro>
					</p>
				</div>
			</div>
		</div>
		<form class="webform-client-form" enctype="multipart/form-data"
			action="/node/342" method="post" id="webform-client-form-342"
			accept-charset="UTF-8">
			<div>
				<div class="form-item webform-component webform-component-textfield"
					id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-apellido">Nombre y
						Apellido <span class="form-required"
						title="This field is required.">*</span>
					</label> <input id="edit-submitted-nombre-apellido"
						name="submitted[nombre_apellido]" value="" size="60"
						maxlength="128" class="form-text required" type="text">
				</div>
				<div class="form-item webform-component webform-component-email"
					id="webform-component-mail">
					<label for="edit-submitted-mail">Mail <span
						class="form-required" title="This field is required.">*</span></label> <input
						class="email form-text form-email required"
						id="edit-submitted-mail" name="submitted[mail]" size="60"
						type="email">
				</div>
				<div class="form-item webform-component webform-component-textfield"
					id="webform-component-telefono">
					<label for="edit-submitted-telefono">Tel&eacute;fono <span
						class="form-required" title="This field is required.">*</span></label> <input
						id="edit-submitted-telefono" name="submitted[telefono]" value=""
						size="60" maxlength="128" class="form-text required" type="text">
				</div>
				<div class="form-item webform-component webform-component-select"
					id="webform-component-categoria">
					<label for="edit-submitted-categoria">Categor&iacute;a <span
						class="form-required" title="This field is required.">*</span></label> <select
						id="edit-submitted-categoria" name="submitted[categoria]"
						class="form-select required"><option value=""
							selected="selected">- Select -</option>
						<option value="safe_key1">Alumno</option>
						<option value="safe_key2">Docente</option>
						<option value="safe_key3">Investigador</option>
						<option value="safe_key4">No Docente</option>
						<option value="safe_key5">Otro</option></select>
				</div>
				<div class="form-item webform-component webform-component-textfield"
					id="webform-component-nombre-del-proyecto">
					<label for="edit-submitted-nombre-del-proyecto">Nombre del
						Proyecto <span class="form-required"
						title="This field is required.">*</span>
					</label> <input id="edit-submitted-nombre-del-proyecto"
						name="submitted[nombre_del_proyecto]" value="" size="60"
						maxlength="128" class="form-text required" type="text">
				</div>
				<div class="form-item webform-component webform-component-textfield"
					id="webform-component-producto-o-servicio">
					<label for="edit-submitted-producto-o-servicio">Producto o
						Servicio <span class="form-required"
						title="This field is required.">*</span>
					</label> <input id="edit-submitted-producto-o-servicio"
						name="submitted[producto_o_servicio]" value="" size="60"
						maxlength="128" class="form-text required" type="text">
				</div>
				<div class="form-item webform-component webform-component-textarea"
					id="webform-component-estado-del-emprendimiento">
					<label for="edit-submitted-estado-del-emprendimiento">Estado
						del emprendimiento <span class="form-required"
						title="This field is required.">*</span>
					</label>
					<div
						class="form-textarea-wrapper resizable textarea-processed resizable-textarea">
						<textarea id="edit-submitted-estado-del-emprendimiento"
							name="submitted[estado_del_emprendimiento]" cols="60" rows="5"
							class="form-textarea required"></textarea>
						<div class="grippie"></div>
					</div>
				</div>
				<div class="form-item webform-component webform-component-textarea"
					id="webform-component-otra-informacion-relevante">
					<label for="edit-submitted-otra-informacion-relevante">Otra
						informaci&oacute;n relevante </label>
					<div
						class="form-textarea-wrapper resizable textarea-processed resizable-textarea">
						<textarea id="edit-submitted-otra-informacion-relevante"
							name="submitted[otra_informacion_relevante]" cols="60" rows="5"
							class="form-textarea"></textarea>
						<div class="grippie"></div>
					</div>
				</div>
				<input name="details[sid]" type="hidden"> <input
					name="details[page_num]" value="1" type="hidden"> <input
					name="details[page_count]" value="1" type="hidden"> <input
					name="details[finished]" value="0" type="hidden"> <input
					name="form_build_id"
					value="form-YLyUEha11z3PT6UiVavbBauAkBFA2LI42ZItzs4wTp0"
					type="hidden"> <input name="form_id"
					value="webform_client_form_342" type="hidden">
				<div class="form-actions form-wrapper" id="edit-actions">
					<input class=" btn  form-submit" id="edit-submit" name="op"
						value="Registrarse" type="submit">
				</div>
			</div>
		</form>


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