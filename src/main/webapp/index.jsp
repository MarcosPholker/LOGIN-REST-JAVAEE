<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>BEM VINDO</title>

</head>
<body>

	<form style="position: absolute; left: 32%; top: 32%; right: 32%;" action="ServletLogin" method="post" class="row g-3 needs-validation">
		
		<h1 style="text-align:center;">BEM VINDO</h1>
		
		<input type="hidden" value="<%=request.getParameter("url")%>" name="url">


		<div class="col-md-6">
			<label class="form-label">Login</label> 
			<input name="login"type="text" class="form-control" required="required">
			<div class="valid-feedback">ok</div>
		</div>

		<div class="col-md-6">
			<label class="form-label">Senha</label> 
			<input name="senha"type="password" class="form-control" required="required">
			<div class="valid-feedback">ok</div>
		</div>

		<input type="submit" value="Acessar" class="btn btn-primary">
		
		<h4 style = "color: red;" >${msg}</h4>

	</form>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
	</script>
		
		
		<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
		})()
		</script>
</body>
</html>