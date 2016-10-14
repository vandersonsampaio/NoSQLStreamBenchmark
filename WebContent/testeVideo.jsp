<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tela de Teste</title>
</head>
<body>
	<video id=example-video width=600 height=300 class="video-js vjs-default-skin" controls>
	  <!-- <source
	     src="http://solutions.brightcove.com/jwhisenant/hls/apple/bipbop/bipbopall.m3u8"
	     type="application/x-mpegURL"> -->
	     <source
	     	src="video/video.mp4"
	     	type="video/mp4">
	</video>
	
	<script src="bootstrap-3.3.7-dist/js/video.js"></script>
	<script src="bootstrap-3.3.7-dist/js/videojs-contrib-hls.min.js"></script>
	<script type="text/javascript">
		var player = videojs('example-video');
		player.play();
	</script>
</body>
</html>