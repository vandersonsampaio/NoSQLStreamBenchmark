<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link type='text/css' rel='stylesheet' href='bootstrap-3.3.7-dist/css/bootstrap.css'>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Administrador</title>
	</head>
	<body>
		<div class="col-xs-12" style="padding: 10px;">
			<h1>Acesso de Administrador</h1>
		</div>
		<form class="col-xs-12 container">
			<div class="form-group row">
				<label for="selectFonteDados" class="col-xs-2 col-form-label">Fonte de Dados</label>
				<div class="col-xs-8">
					<select class="form-control" id="selectFonteDados">
					  <option value="C">Apache Cassandra</option>
					  <option value="H">Apache HBase</option>
					  <option value="M">MongoDB</option>
					  <option value="R">Redis</option>
					  <option value="V">Project Voldemort</option>
					</select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="selectResolucao" class="col-xs-2 col-form-label">Resolução</label>
				<div class="col-xs-8">
					<select class="form-control" id="selectResolucao">
					  <option value="240p">240p</option>
					  <option value="360p">360p</option>
					  <option value="420p">420p</option>
					  <option value="720p">720p</option>
					  <option value="1080p">1080p</option>
					  <option value="4k">4k</option>
					</select>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="edtConcorrencia" class="col-xs-2 col-form-label">Concorrência</label>
				<div class="col-xs-8">
					<input class="form-control" id="edtConcorrencia" type="number"/>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="edtQuantidade" class="col-xs-2 col-form-label">Quantidade</label>
				<div class="col-xs-8">
					<input class="form-control" id="edtQuantidade" type="number"/>
				</div>
			</div>
			
			<div class="form-group row">
				<label for="edtConsole" class="col-xs-2 col-form-label">Console</label>
				<div class="col-xs-8">
					<textarea class="form-control" id="edtConsole"></textarea>
				</div>
			</div>
			
			<div class="form-group col-xs-12">
				<button type="submit" title="Inserir" class="btn btn-primary" name="btnInserir">Inserir</button>
				<button type="submit" title="Consultar" class="btn btn-info" name="btnConsultar">Consultar</button>
				<button type="submit" title="Limpar" class="btn btn-danger" name="btnLimpar">Limpar Base</button>
			</div>
		</form>
	</body>
</html>