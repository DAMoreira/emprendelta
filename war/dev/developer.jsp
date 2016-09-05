<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
</head>
<body>
	<h1>Metodos disponibles:</h1>
	<div>
		<h2>Borrar un diario</h2>
		<p>Para borrar un diario se debe invocar la url /dev/reset?parameter= con el namespace correspondiente al diario que se quiera borrar.</p>
	</div>
	<div>
		<h2>Borrar estadísticas</h2>
		<p>Borra todas las estadisticas del TDH, las que son generadas por acciones de todos los usuarios, /dev/cleanStatistics</p>
	</div>
	<div>
		<h2>Correr Cron</h2>
		<p>/cron/saveStatistics - Persiste datos de la cache en el datastore (programado para correr cada 10 o 60 minutos)</p>
		<p>/cron/countDailyStatistics - Crea los objetos para la estadistica diaria (corre una vez al dia)</p>
	</div>
</body>
</html>