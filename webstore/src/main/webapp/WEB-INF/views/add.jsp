<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<title>Search</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Add book</h1>
				<p>Here is the magic place of creation, magnificent workbench of Gods and the root of your mind. Come, come, enjoy the madness!</p>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-md-6">
				<form name="title" action="adding" method="POST">
					<input type='text' name='title' /> <input type='text'
						name='authors' />

										<label class="radio-inline"><input type="radio" name="status" value='free'>Free</label>
										<label class="radio-inline"><input type="radio" name="status" value='loan'>Loan</label>
										<label class="radio-inline"><input type="radio" name="status" value='missing'>Missing</label>

					<input type='submit' value='Search' class='btn' />
				</form>
			</div>
		</div>
	</section>
	<section class="container">
		<a href="/webstore/" class="btn btn-default"> <span
			class="glyphicon glyphicon-chevron-left" /></span> Back
		</a>
	</section>
</body>
</html>
