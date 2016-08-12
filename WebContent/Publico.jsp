<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publico</title>
</head>
	<body>
		<div class="col-lg-12">
			<label>Fonte de Dados</label>
			<select>
			  <option value="C">Apache Cassandra</option>
			  <option value="H">Apache HBase</option>
			  <option value="M">MongoDB</option>
			  <option value="R">Redis</option>
			  <option value="V">Project Voldemort</option>
			</select>
		</div>
		
		<div class="col-lg-12">
			<label>Resolução</label>
			<select>
			  <option value="240p">240p</option>
			  <option value="360p">360p</option>
			  <option value="420p">420p</option>
			  <option value="720p">720p</option>
			  <option value="1080p">1080p</option>
			  <option value="4k">4k</option>
			</select>
		</div>
		
		<div class="col-lg-12">
			<label>Concorrência</label>
			<input type="text"/>
		</div>
		
		<div class="col-lg-12">
			<label>Quantidade</label>
			<input type="text"/>
		</div>
		
		<div class="col-lg-12">
			<input type="submit" title="Buscar" name="inserir"/>
			<input type="submit" title="Medir"/>
		</div>
		
		<video width="320" height="240" controls>
  			<source src="movie.mp4" type="video/mp4"></source>
  		</video>
		
		<div class="col-lg-12">
			<label>Console</label>
			<textarea name="comment"></textarea>
		</div>
	</body>
</html>