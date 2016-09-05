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
	<s:actionerror/>
	<s:actionmessage/>
	<!-- Login -->
	<s:form action="authenticate" cssClass="box tile animated %{(boxActive=='login'||boxActive==''||boxActive==null)?'active':''}" id="box-login">
		<h2 class="m-t-0 m-b-15">
			<s:text name="global.login"></s:text>
		</h2>
		<s:hidden name="boxActive" value="login"></s:hidden>
		<s:textfield name="username" key="global.username" cssClass="form-control" 
		class="login-control m-b-10" placeholder="%{getText('global.username')}" required="true" autocomplete="off"></s:textfield>
		
		<s:password name="password" key="global.password" cssClass="form-control" 
		class="login-control" placeholder="%{getText('global.password')}" required="true"></s:password>
		<div class="checkbox m-b-20">
			<label>
				<input type="checkbox">
				<s:text name="login.rememberMe"></s:text>
			</label>
		</div>
		<button class="btn btn-sm m-r-5"><s:text name="global.login" /></button>
		
		<small>
			<a  href="/registro-de-emprendedores">
				<s:text name="login.noAccount"></s:text>
			</a>
			 | 
			<a class="box-switcher" data-switch="box-reset" href="">
				<s:text name="login.forgotPassword"></s:text>
			</a>
		</small>
	</s:form>

	<!-- Register -->
	<s:form action="register" cssClass="box animated tile %{boxActive=='register'?'active':''}" id="box-register">
		<h2 class="m-t-0 m-b-15"><s:text name="register.register"></s:text></h2>
		<s:hidden name="boxActive" value="register"></s:hidden>


			<div>
				<div class="form-item webform-component webform-component-textfield"
					id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-apellido">Nombre y
						Apellido <span class="form-required"
						title="This field is required.">*</span>
					</label>
					<s:textfield name="user.name" cssClass="login-control m-b-10" placeholder="%{getText('user.fullname')}" required="true"></s:textfield>
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-deusuario">Nombre de Usuario<span class="form-required" title="This field is required.">*</span></label>
					<s:textfield name="username" cssClass="login-control m-b-10" placeholder="%{getText('global.username')}" required="true"></s:textfield>
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-deusuario">Mail <span class="form-required" title="This field is required.">*</span></label>
					<s:textfield type="email" name="user.email" cssClass="login-control m-b-10" placeholder="%{getText('user.email')}" required="true"></s:textfield>
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-deusuario">Contraseña <span class="form-required" title="This field is required.">*</span></label>
					<s:password name="password" cssClass="login-control m-b-10" placeholder="%{getText('global.password')}" required="true"></s:password>
				</div>
				<div class="form-item webform-component webform-component-textfield" id="webform-component-nombre-apellido">
					<label for="edit-submitted-nombre-deusuario">Reingresar Contraseña <span class="form-required" title="This field is required.">*</span></label>
					<s:password name="passwordConfirmation" cssClass="login-control m-b-20" placeholder="%{getText('login.confirmPassword')}" required="true"></s:password>
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
					<button type="submit" class="btn btn-sm m-r-5"><s:text name="global.register"></s:text></button>
				</div>
			</div>


		<small>
			<a class="box-switcher" data-switch="box-login" href="">
				<s:text name="login.alreadyAccount"></s:text>
			</a>
		</small>
	</s:form>
	
	<!-- Forgot Password -->
	<s:form action="passwordReset" cssClass="box animated tile %{boxActive=='reset'?'active':''}" id="box-reset">
		<h2 class="m-t-0 m-b-15">
			<s:text name="login.resetPassword"></s:text>
		</h2>
		<s:hidden name="boxActive" value="reset"></s:hidden>
		<s:textfield type="email" key="resetPasswordEmail" cssClass="login-control m-b-20" placeholder="%{getText('user.email')}" required="true"></s:textfield>
		<s:submit key="login.resetPassword" cssClass="btn btn-sm m-r-5"></s:submit>
		<small>
			<a class="box-switcher" data-switch="box-login" href="">
				<s:text name="login.alreadyAccount"></s:text>
			</a>
		</small>
	</s:form>
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
	<script type="text/javascript">
/* --------------------------------------------------------
    Login + Sign up
   -----------------------------------------------------------*/
$(".box-switcher").click(function(e){
	e.preventDefault();
    var box = $(this).attr('data-switch');
    $(this).closest('.box').toggleClass('active');
    $('#'+box).closest('.box').addClass('active'); 
});
</script>
</body>
</html>